package garden.weeklyplan.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import garden.model.WeeklyPlan;

public class RemovePlan extends JPanel implements ActionListener {
	private WeeklyPlan plan;
	
	private String[] objectGroup = {"north", "east", "west", "south"};
	private String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private 	String[] optionChoice = {};
	
	private JComboBox groupList;
	private JComboBox weekList;
	private JComboBox timeList;
	private JButton removeButton;
	
	public RemovePlan(WeeklyPlan plan) {
		this.plan = plan;
		
		setLayout(new GridLayout(4, 2));
		setBorder(new TitledBorder("Romove a plan"));
		
		JLabel group = new JLabel("Group");
		add(group);
		
		groupList = new JComboBox(objectGroup);
		add(groupList);
		
		JLabel weekDay = new JLabel("Week");
		add(weekDay);
		
		weekList = new JComboBox(week);
		weekList.addActionListener(this);
		add(weekList);
		
		JLabel workTime = new JLabel("Time");
		add(workTime);
		
		timeList = new JComboBox(optionChoice);
		add(timeList);
		
		add(new JLabel());
				
		removeButton = new JButton("Remove");
		removeButton.setActionCommand("rmv");
		add(removeButton);
	}
	
	private void updateTimeList(WeeklyPlan plan) {
		String selectedGroup = getGroup();
		String day = getDay();
		
		List<String> planTime = plan.getWeeklyPlan(selectedGroup).get(day);
		
		timeList.removeAllItems();
		for (String time: planTime) {
			timeList.addItem(time);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		removeButton.addActionListener(listener);
	}

	public void actionPerformed(ActionEvent e) {
		updateTimeList(plan);
	}
	
	public String getGroup() {
		return groupList.getSelectedItem().toString();
	}
	
	public String getDay() {
		return weekList.getSelectedItem().toString();
	}
	
	public String getTime() {
		if (timeList.getSelectedItem() == null) return null;
		return timeList.getSelectedItem().toString();
	}
}
