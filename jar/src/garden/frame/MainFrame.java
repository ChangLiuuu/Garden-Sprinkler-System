package garden.frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JFrame;

import org.json.JSONObject;

import garden.common.Utils;
import garden.controller.SprinklerMapController;
import garden.controller.SprinklerSetController;
import garden.controller.WeeklyPlanController;
import garden.model.Sprinkler;
import garden.model.SprinklerBank;
import garden.model.TemperatureSensor;
import garden.model.WaterUsage;
import garden.model.WeeklyPlan;

public class MainFrame {
	private WeeklyPlan weeklyPlan = new WeeklyPlan();
	private SprinklerBank spkBank = new SprinklerBank(weeklyPlan);
	private HashMap<String, TemperatureSensor> sensorMap = new HashMap<String, TemperatureSensor>();
	
	private JFrame frame;
	private PanelTab tab;
	
	private SprinklerSetController sprinklerSetControl;
	private SprinklerMapController sprinklerMapControl;
	private WeeklyPlanController weeklyPlanControl;
	
	public MainFrame() {
		frame = new JFrame("Garden System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initSensor();
		
		tab = new PanelTab(weeklyPlan, spkBank, sensorMap);
		
		sprinklerSetControl = new SprinklerSetController(tab.getSpkSetView(), spkBank);
		sprinklerMapControl = new SprinklerMapController(tab.getSpkMapView(), spkBank);
		weeklyPlanControl = new WeeklyPlanController(tab.getWeeklyPlanView(), weeklyPlan);
		
		frame.add(tab);
		
		for (Sprinkler spk : spkBank.getAllSprinklers()) {
			spk.addObserver(tab.getSpkMapView());
			spk.setSensor(sensorMap.get(spk.getGroup()));
		}
		weeklyPlan.addObserver(tab.getWeeklyPlanView());
		
	}
	
	private void initSensor() {
		TemperatureSensor sensorNorth = new TemperatureSensor("north", 80);
		TemperatureSensor sensorSouth = new TemperatureSensor("south", 80);
		TemperatureSensor sensorEast = new TemperatureSensor("east", 80);
		TemperatureSensor sensorWest = new TemperatureSensor("west", 80);
		sensorMap.put("north", sensorNorth);
		sensorMap.put("south", sensorSouth);
		sensorMap.put("east", sensorEast);
		sensorMap.put("west", sensorWest);
	}

	public void go() {
		//start a new thread
		for(Sprinkler spk : this.spkBank.getAllSprinklers()){
			new Thread(spk).start();
		}
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					saveTotalWaterUse();
					weeklyPlan.saveToJson();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
            }
		});
		
		frame.setSize(850, 600);
		frame.setVisible(true);
	}
	
	private void saveTotalWaterUse() throws IOException{
		JSONObject totalUsage = new JSONObject();
		
		for (Sprinkler spk : spkBank.getAllSprinklers()) {
			WaterUsage water = spk.getWaterUse();
			
			totalUsage.put(spk.getID(), water.saveToJson());
		}
		
		String jstring = totalUsage.toString();
		Utils.writeFiles("resources/waterUse.json", jstring);
	}
	
	
}
