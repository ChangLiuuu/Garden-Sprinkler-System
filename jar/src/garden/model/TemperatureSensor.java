package garden.model;

public class TemperatureSensor {
	private String group;
	private double belowTemp;
	private double aboveTemp;
	private double curTemperature;
	
	public TemperatureSensor(String group, double curTemperature) {
		belowTemp = 75.00;
		aboveTemp = 95.00;
		this.group = group;
		this.curTemperature = curTemperature;
	}
	
	public void setBelowTemp(double temperature) {
		belowTemp = temperature;
	}
	
	public double getBelowTemp() {
		return this.belowTemp;
	}
	
	public void setAboveTemp(double temperature) {
		aboveTemp = temperature;
	}
	
	public double getAboveTemp() {
		return this.aboveTemp;
	}
	
	public void setCurTemp(double temperature) {
		curTemperature = temperature;
	}
	
	public double getCurTemp() {
		return this.curTemperature;
	}
}
