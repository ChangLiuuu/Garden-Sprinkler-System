package garden.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprinklerBank {
	private final double WATER_FLOW = 2.0;
	private WeeklyPlan weeklyPlan;
	
	Map<String, Sprinkler> idSprinklerMap = new HashMap<String, Sprinkler>();
	Map<String, List<Sprinkler>> groupSprinklerMap = new HashMap<String, List<Sprinkler>>();
	
	List<Sprinkler> northGroup = new ArrayList<Sprinkler>();
	List<Sprinkler> westGroup = new ArrayList<Sprinkler>();
	List<Sprinkler> eastGroup = new ArrayList<Sprinkler>();
	List<Sprinkler> southGroup = new ArrayList<Sprinkler>();
	
	public SprinklerBank(WeeklyPlan weeklyPlan) {
		this.weeklyPlan = weeklyPlan;
		
		Sprinkler spkN1 = new Sprinkler("N1", "north", WATER_FLOW, weeklyPlan);
		Sprinkler spkN2 = new Sprinkler("N2", "north", WATER_FLOW, weeklyPlan);
		Sprinkler spkN3 = new Sprinkler("N3", "north", WATER_FLOW, weeklyPlan);
		northGroup.add(spkN1);
		northGroup.add(spkN2);
		northGroup.add(spkN3);
		groupSprinklerMap.put("north", northGroup);
		
		Sprinkler spkW1 = new Sprinkler("W1", "west", WATER_FLOW, weeklyPlan);
		Sprinkler spkW2 = new Sprinkler("W2", "west", WATER_FLOW, weeklyPlan);
		westGroup.add(spkW1);
		westGroup.add(spkW2);
		groupSprinklerMap.put("west", westGroup);
		
		Sprinkler spkE1 = new Sprinkler("E1", "east", WATER_FLOW, weeklyPlan);
		Sprinkler spkE2 = new Sprinkler("E2", "east", WATER_FLOW, weeklyPlan);
		eastGroup.add(spkE1);
		eastGroup.add(spkE2);
		groupSprinklerMap.put("east", eastGroup);
		
		Sprinkler spkS1 = new Sprinkler("S1", "south", WATER_FLOW, weeklyPlan);
		Sprinkler spkS2 = new Sprinkler("S2", "south", WATER_FLOW, weeklyPlan);
		Sprinkler spkS3 = new Sprinkler("S3", "south", WATER_FLOW, weeklyPlan);
		southGroup.add(spkS1);
		southGroup.add(spkS2);
		southGroup.add(spkS3);
		groupSprinklerMap.put("south", southGroup);
		
		idSprinklerMap.put("N1", spkN1);
		idSprinklerMap.put("N2", spkN2);
		idSprinklerMap.put("N3", spkN3);
		idSprinklerMap.put("W1", spkW1);
		idSprinklerMap.put("W2", spkW2);
		idSprinklerMap.put("E1", spkE1);
		idSprinklerMap.put("E2", spkE2);
		idSprinklerMap.put("S1", spkS1);
		idSprinklerMap.put("S2", spkS2);
		idSprinklerMap.put("S3", spkS3);
	}
	
	public void groupTurningOn(String group) {
		List<Sprinkler> spkGroup = getSprinklersGroup(group);
		for (Sprinkler spk : spkGroup) {
			spk.turningOn();
		}
	}
	
	public void groupTurningOff(String group) {
		List<Sprinkler> spkGroup = getSprinklersGroup(group);
		for (Sprinkler spk : spkGroup) {
			spk.turningOff();
		}
	}
	
	public List<Sprinkler> getSprinklersGroup(String group) {
		return groupSprinklerMap.get(group);
	}
	
	public Sprinkler getSingleSprinkler(String id) {
		return idSprinklerMap.get(id);
	}
	
	public void turnOnAutoControl() {
		for (Sprinkler spk : getAllSprinklers()) {
			spk.autoControlSpk();
		}
	}
	
	public void turnOffAutoControl() {
		for (Sprinkler spk : getAllSprinklers()) {
			spk.manualControlSpk();
		}
	}
	
	public void changeWaterFlow(double flow) {
		for (Sprinkler spk : getAllSprinklers()) {
			spk.setWaterFlow(flow);
		}
	}
	
	public double getTotalWaterUse(String date) {
		double total = 0.0;
		for (Sprinkler spk : getAllSprinklers()) {
			total += spk.getWaterUse().getDayUsage(date);
		}
		return total;
	}
	
	public Collection<Sprinkler> getAllSprinklers() {
		return idSprinklerMap.values();
	}
}
