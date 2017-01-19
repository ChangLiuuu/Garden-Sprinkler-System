package garden.frame;

import java.util.HashMap;

import javax.swing.JTabbedPane;

import garden.model.SprinklerBank;
import garden.model.TemperatureSensor;
import garden.model.WeeklyPlan;
import garden.sprinkler.view.*;
import garden.view.SprinklerViewer;
import garden.view.WaterUseView;
import garden.view.WeeklyPlanViewer;

public class PanelTab extends JTabbedPane {
	private SprinklerViewer sprinklerView;
	private WeeklyPlanViewer weeklyPlanView;
	private WaterUseView waterUseView;
	
	public PanelTab(WeeklyPlan weeklyPlan, SprinklerBank spkBank, HashMap<String, TemperatureSensor> sensorMap) {
		sprinklerView = new SprinklerViewer(sensorMap);
		weeklyPlanView = new WeeklyPlanViewer(weeklyPlan);
		waterUseView = new WaterUseView(spkBank);

		addTab("Sprinkler Map", sprinklerView);
		addTab("Set/View Weekly Plan", weeklyPlanView);
		addTab("Daily Water Usage", waterUseView);
		
		Thread waterShowing = new Thread(waterUseView);
		waterShowing.start();
	}
	
	public SprinklerSetView getSpkSetView() {
		return sprinklerView.getSettingView();
	}
	
	public SprinklerMapView getSpkMapView() {
		return sprinklerView.getMapView();
	}
	
	public WeeklyPlanViewer getWeeklyPlanView() {
		return weeklyPlanView;
	}
	
}
