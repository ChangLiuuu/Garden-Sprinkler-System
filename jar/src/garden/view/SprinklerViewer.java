package garden.view;

import javax.swing.JPanel;

import garden.model.TemperatureSensor;
import garden.sprinkler.view.SprinklerMapView;
import garden.sprinkler.view.SprinklerSetView;
import garden.sprinkler.view.TemperatureView;

import java.awt.BorderLayout;
import java.util.HashMap;

public class SprinklerViewer extends JPanel{
	private SprinklerMapView mapView;
	private SprinklerSetView settingView;
	private TemperatureView temperatureView;
	
	public SprinklerViewer(HashMap<String, TemperatureSensor> sensorMap) {
		setLayout(new BorderLayout());
		
		mapView = new SprinklerMapView();
		add(mapView, BorderLayout.CENTER);
		
		settingView = new SprinklerSetView();
		add(settingView, BorderLayout.EAST);
		
		temperatureView = new TemperatureView(sensorMap);
		add(temperatureView, BorderLayout.SOUTH);
	}
	
	public SprinklerMapView getMapView() {
		return mapView;
	}
	
	public SprinklerSetView getSettingView() {
		return settingView;
	}
	
}
