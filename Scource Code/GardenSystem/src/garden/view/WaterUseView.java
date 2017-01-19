package garden.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import garden.model.Sprinkler;
import garden.model.SprinklerBank;


public class WaterUseView extends JPanel implements Runnable {
	private SprinklerBank spkBank;
	
	private CategoryPlot categoryPlot = null;
	
	public WaterUseView(SprinklerBank spkBank) {
		this.spkBank = spkBank;
		
		setLayout(new BorderLayout());
		drawBarGraph();
	}
	
	private DefaultCategoryDataset createDataset(){
	    	DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
	    	
	    	HashSet<String> set = new HashSet<String>();
	    	
	    	for (Sprinkler spk : spkBank.getAllSprinklers()) {
	    		for (String date : spk.getWaterUse().getDayOfUse().keySet()) {
	    			set.add(date);
	    		}
	    	}
	    	
	    	ArrayList<String> dates = sevenDaysOfUsage(set);
	    	
	    	for (int i = dates.size() - 1; i >= 0; i--) {
	    		String date = dates.get(i);
	    		double totalWaterUseOfOneDay = spkBank.getTotalWaterUse(date);
	    		dataSet.setValue(totalWaterUseOfOneDay, "Water Usage", date);
	    	}
	    	
	    	return dataSet;
	}
	
	private ArrayList<String> sevenDaysOfUsage(HashSet<String> dates) {
		ArrayList<String> oriString = new ArrayList<String>();
		for (String date : dates) {
			oriString.add(date);
		}
		
		Collections.sort(oriString);
		ArrayList<String> ansString = new ArrayList<String>();
		int cnt = 0;
		for (int i = oriString.size() - 1; i >= 0 && cnt++ < 10; i--) {
			ansString.add(oriString.get(i));
		}
		
		return ansString;
	}

	private JFreeChart createChart(DefaultCategoryDataset dataSet){
		String chartTitle = "Daily Water Usage Statistics";
		JFreeChart barChart = ChartFactory.createBarChart3D("Day of Water Use", "", "Daily Water Usage", dataSet);
		categoryPlot = barChart.getCategoryPlot();
		categoryPlot.setRangeGridlinePaint(Color.blue);
		barChart.setTitle(new TextTitle(chartTitle));
		
		return barChart;
	}
	
	private void drawBarGraph() {
		DefaultCategoryDataset dataSet = createDataset();
		JFreeChart chart = createChart(dataSet);
		ChartPanel chartPanel = new ChartPanel(chart);
	    add(chartPanel, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		while (true) {
			try{
				Thread.sleep(1000);  //update bar graph every one second
				categoryPlot.setDataset(createDataset());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
