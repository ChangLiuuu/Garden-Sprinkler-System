package garden.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import garden.model.WeeklyPlan;
import garden.weeklyplan.view.AddPlan;
import garden.weeklyplan.view.PlanSetViewer;
import garden.weeklyplan.view.PlanShowingViewer;
import garden.weeklyplan.view.RemovePlan;

public class WeeklyPlanViewer extends JPanel implements Observer {
	private PlanSetViewer settingView;
	private PlanShowingViewer showingView;
	
	public WeeklyPlanViewer(WeeklyPlan plan) {
		setLayout(new BorderLayout());
		
		settingView = new PlanSetViewer(plan);
		settingView.setPreferredSize(new Dimension(230, 500));
		add(settingView, BorderLayout.EAST);
		
		showingView = new PlanShowingViewer();
		add(showingView, BorderLayout.CENTER);
		showingView.display(plan);
	}
	
	public void update(Observable o, Object arg) {
		WeeklyPlan plan = (WeeklyPlan) o;
		showingView.display(plan);
	}
	
	public AddPlan getAddPlanView() {
		return settingView.getAddPlanView();
	}
	
	public RemovePlan getRemovePlanView() {
		return settingView.getRemovePlanView();
	}
}
