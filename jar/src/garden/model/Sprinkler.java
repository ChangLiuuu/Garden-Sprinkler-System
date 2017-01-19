package garden.model;

import java.lang.Comparable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Observable;

public class Sprinkler extends Observable implements Runnable, Comparable<Sprinkler> {
	private String nameID;
	private String group;
	
	private boolean automation;
	
	private double waterFlux;
	private WaterUsage waterUse;
	
	private TemperatureSensor tempSensor;
	
	private boolean isTurnedOn;
	private WeeklyPlan weeklyPlan;
	
	public Sprinkler(String ID, String group, double waterFlux, WeeklyPlan weeklyPlan) {
		this.nameID = ID;
		this.group = group;
		
		this.automation = false;
		
		this.waterFlux = waterFlux;
		this.waterUse = new WaterUsage(ID);
		
		this.isTurnedOn = false;
		this.weeklyPlan = weeklyPlan;
	}
	
	public String getID() {
		return nameID;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setSensor(TemperatureSensor sensor){
		this.tempSensor = sensor;
	}
	
	public void setTemperature(double temperature) {
		tempSensor.setCurTemp(temperature);
	}
	
	public double getTemperature() {
		return tempSensor.getCurTemp();
	}
	
	public void setWaterFlow(double flow) {
		waterFlux = flow;
	}
	
	public double getWaterFlow() {
		return waterFlux;
	}
	
	public void manualControlSpk() {
		automation = false;
	}
	
	public void autoControlSpk() {
		automation = true;
	}
	
	public boolean isAutoControl() {
		return automation;
	}
	
	public WaterUsage getWaterUse() {
		return waterUse;
	}
	
	public void turningOn() {
		isTurnedOn = true;
		setChanged();
		notifyObservers();
	}
	
	public void turningOff() {
		isTurnedOn = false;
		setChanged();
		notifyObservers();
	}
	
	public boolean getStatus() {
		return isTurnedOn;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				if (automation) {
					if (tempSensor.getCurTemp() < tempSensor.getBelowTemp()) {
						turningOff();
					} else if (tempSensor.getCurTemp() > tempSensor.getAboveTemp()) {
						turningOn();
					} else {
						if (isInWeeklyPlan()) {
							turningOn();
						} else {
							turningOff();
						} 
					}
				}
				
				if (isTurnedOn) {
					waterUse.addWaterUsage(waterFlux / 3600);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int compareTo(Sprinkler o) {
		Sprinkler spk = (Sprinkler) o;
		return this.nameID.compareTo(spk.nameID);
	}
	
	private String getTimeByCalendar() {
		Calendar cal = Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);// hour
	    int minute=cal.get(Calendar.MINUTE);// minute          
	    String date = "" + hour + ":" + minute;
//	    System.out.println(date);
	    return date;
	}
	
	private static String getWeek(Date date){  
	    String[] weeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};  
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
	    if(week_index<0){  
	        week_index = 0;  
	    }   
	    return weeks[week_index];
	}
	
	private boolean isInWeeklyPlan() {
		//define the format of date
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		//get current week date;
		Date date = new Date();
		String day = getWeek(date);
		
		//get the weekly plan
		Map<String, ArrayList<String>> plan = this.weeklyPlan.getWeeklyPlan(group);
		ArrayList<String> workingTime = plan.get(day);
		
		if (workingTime.size() == 0) {
			return false;
		}
		
		for (String time : workingTime) {
//			System.out.println("time");
			String[] startAndEnd = time.split("-");
			String startTime = startAndEnd[0];
//			System.out.print(startTime + " ");
			String endTime = startAndEnd[1];
//			System.out.println(endTime);
			
			Date dateOfHourMin;
			Date startDate;
			Date endDate;
			try {
				dateOfHourMin = sdf.parse(getTimeByCalendar());
				
				startDate = sdf.parse(startTime);
				endDate = sdf.parse(endTime);
				
				if (dateOfHourMin.getTime() >= startDate.getTime() && dateOfHourMin.getTime() < endDate.getTime()) {
					return true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}