package garden.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import garden.model.WeeklyPlan;
import garden.view.WeeklyPlanViewer;
import garden.weeklyplan.view.AddPlan;
import garden.weeklyplan.view.RemovePlan;

public class WeeklyPlanController implements ActionListener {
	private WeeklyPlan plan;
	
	private AddPlan addPlan;
	private RemovePlan removePlan;
	
	public WeeklyPlanController(WeeklyPlanViewer planView, WeeklyPlan plan) {
		this.plan = plan;
		
		addPlan = planView.getAddPlanView();
		addPlan.addActionListener(this);
		
		removePlan = planView.getRemovePlanView();
		removePlan.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("add")) {
			plan.setWeeklyPlan(addPlan.getGroup(), addPlan.getDay(), addPlan.getTime());
		} else {
			plan.removeWeeklyPlan(removePlan.getGroup(), removePlan.getDay(), removePlan.getTime());
		}
	}
}
