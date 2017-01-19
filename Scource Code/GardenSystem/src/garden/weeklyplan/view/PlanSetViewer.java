package garden.weeklyplan.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import garden.model.WeeklyPlan;

public class PlanSetViewer extends JPanel {
	private AddPlan addingView;
	private RemovePlan removingView;
	
	public PlanSetViewer(WeeklyPlan plan) {
		setLayout(new GridLayout(2, 1));
		
		addingView = new AddPlan();
		add(addingView);
		
		removingView = new RemovePlan(plan);
		add(removingView);
	}
	
	public AddPlan getAddPlanView() {
		return addingView;
	}
	
	public RemovePlan getRemovePlanView() {
		return removingView;
	}
}
