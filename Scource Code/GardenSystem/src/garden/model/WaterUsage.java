package garden.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import garden.common.Utils;

public class WaterUsage {
	private String nameID;
	private Map<String, Double> dayOfUse;
	
	public WaterUsage(String ID) {
		nameID = ID;
		dayOfUse = loadFile("resources/waterUse.json");
	}
	
	public Map<String, Double> getDayOfUse() {
		return dayOfUse;
	}
	
	public double getDayUsage(String date){
		if (! dayOfUse.containsKey(date)) {
			return 0.0;
		}
		return dayOfUse.get(date);
	}
	
	public void addWaterUsage(double inc) {
		String currentDay = getCurrentDate();
		if (dayOfUse.containsKey(currentDay)) {
			double cuttentUsage = dayOfUse.get(currentDay) + inc;
			dayOfUse.put(currentDay, cuttentUsage);
		} else {
			dayOfUse.put(currentDay, inc);
		}
	}
	
	public JSONObject saveToJson() throws IOException {
		JSONObject dailyWaterUse = new JSONObject();
		for (Map.Entry<String, Double> entry : dayOfUse.entrySet()) {
			dailyWaterUse.put(entry.getKey(), entry.getValue());
		}
		
		return dailyWaterUse;
	}
	
	private Map<String, Double> loadFile(String path) {
		String jsonContents = Utils.readFile(path);
		
		JSONObject jsonObject = new JSONObject(jsonContents);
		Map<String, Double> innerMap = new HashMap<String, Double>();
		
		Iterator iterator = jsonObject.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			
			if (!nameID.equals(key)) continue;
			
			JSONObject innerJsonObj = jsonObject.getJSONObject(key);
			Iterator innerIterator = innerJsonObj.keys();
			
			while(innerIterator.hasNext()) {
				String innerKey = (String) innerIterator.next();
				innerMap.put(innerKey, innerJsonObj.getDouble(innerKey));
			}
		}
		return innerMap;
	}
	
	private String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
		return sdf.format(date);
	}
}
