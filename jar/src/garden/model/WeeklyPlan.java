package garden.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONObject;

import garden.common.Utils;

public class WeeklyPlan extends Observable {
	private final String[] groups = {"north", "west", "east", "south"};
	
	private Map<String, Map<String, ArrayList<String>>> allGroupPlan;
	
	private Map<String, ArrayList<String>> northGroupPlan;
	private Map<String, ArrayList<String>> eastGroupPlan;
	private Map<String, ArrayList<String>> westGroupPlan;
	private Map<String, ArrayList<String>> southGroupPlan;
	
	public WeeklyPlan() {
		allGroupPlan = this.loadFiles("resources/weeklyPlan.json");
		
		northGroupPlan = allGroupPlan.get("north");
		eastGroupPlan = allGroupPlan.get("east");
		westGroupPlan = allGroupPlan.get("west");
		southGroupPlan = allGroupPlan.get("south");
	}
	
	public Map<String, ArrayList<String>> getWeeklyPlan(String group) {
		return allGroupPlan.get(group);
	}
	
	public void setWeeklyPlan(String group, String day, String time) {
		if (group.equals("all")) {
			setWeeklyPlan("north", day, time);
			setWeeklyPlan("east", day, time);
			setWeeklyPlan("west", day, time);
			setWeeklyPlan("south", day, time);
		} else {
			Map<String, ArrayList<String>> weeklyplan = getWeeklyPlan(group);
			
			if (weeklyplan.containsKey(day)) {
				weeklyplan.get(day).add(time);
				
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public void removeWeeklyPlan(String group, String day, String time) {
		if (group.equals("all")) {
			removeWeeklyPlan("north", day, time);
			removeWeeklyPlan("east", day, time);
			removeWeeklyPlan("west", day, time);
			removeWeeklyPlan("south", day, time);
		} else {
			Map<String, ArrayList<String>> weeklyplan = getWeeklyPlan(group);
			
			if (weeklyplan.containsKey(day)) {
				weeklyplan.get(day).remove(time);
				
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public String[] getWorkingTime(String group, String day) {
		ArrayList<String> workingTime = getWeeklyPlan(group).get(day);
		
		String[] working = (String[]) workingTime.toArray();
		return working;
	}
	
	public void saveToJson() throws IOException {
		JSONObject planToJson = new JSONObject();
		
		for (String group : groups) {
			JSONObject groupJson = toJsonObj(group);
			planToJson.put(group, groupJson);
		}
		
		String planToString = planToJson.toString();
		Utils.writeFiles("resources/weeklyPlan.json", planToString);
	}
	
	private JSONObject toJsonObj(String group) throws IOException {
		JSONObject groupJson = new JSONObject();
		
		Map<String, ArrayList<String>> groupPlan = getWeeklyPlan(group);
		
		for (String day : groupPlan.keySet()) {
			JSONArray timeArray = new JSONArray();
			
			for (String time : groupPlan.get(day)) {
				timeArray.put(time);
			}
			groupJson.put(day, timeArray);
		}
		
		return groupJson;
	}
	
	private Map<String, Map<String, ArrayList<String>>> loadFiles(String path) {
		String jsonContents = Utils.readFile(path);
		
		JSONObject jsonObject = new JSONObject(jsonContents);
		Map<String, Map<String, ArrayList<String>>> allGroupPlan = new HashMap<String, Map<String, ArrayList<String>>>();
		
		for (String group : groups) {
			JSONObject groupPlanJson = jsonObject.getJSONObject(group);
			Map<String, ArrayList<String>> groupWorkingTime = new HashMap<String, ArrayList<String>>();
			
			Iterator iterator = groupPlanJson.keys();
			while(iterator.hasNext()){
				String day = (String) iterator.next();
				ArrayList<String> times = new ArrayList<String>();
				JSONArray timeArray = groupPlanJson.getJSONArray(day);
				for(int i = 0; i < timeArray.length(); i++){
					times.add(timeArray.getString(i));
				}
				groupWorkingTime.put(day, times);
			}
			allGroupPlan.put(group, groupWorkingTime);
		}
		
		return allGroupPlan;
	}
}
