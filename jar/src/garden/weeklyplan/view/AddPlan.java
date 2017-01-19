package garden.weeklyplan.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AddPlan extends JPanel {
	private JComboBox groupList;
	private JComboBox weekList;
	private JTextField startTime;
	private JTextField endTime;
	private JButton addButton;
	
	public AddPlan() {
		setLayout(new GridLayout(5, 2));
		setBorder(new TitledBorder("Add a weekly plan"));
		
		JLabel group = new JLabel("Group");
		add(group);
		
		String[] objectGroup = {"all", "north", "east", "west", "south"};
		groupList = new JComboBox(objectGroup);
		add(groupList);
		
		JLabel day = new JLabel("Week");
		add(day);
		
		String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		weekList = new JComboBox(week);
		add(weekList);
		
		JLabel start = new JLabel("Start");
		add(start);
		
		startTime = new JTextField();
		add(startTime);
		
		JLabel end = new JLabel("End");
		add(end);
		
		endTime = new JTextField();
		add(endTime);
		
		add(new JLabel());
		
		addButton = new JButton("add");
		addButton.setActionCommand("add");
		add(addButton);
	}
	
	public void addActionListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}
	
	public String getGroup() {
		return groupList.getSelectedItem().toString();
	}
	
	public String getDay() {
		return weekList.getSelectedItem().toString();
	}
	
	public String getTime() {
		String addTime = startTime.getText() + "-" + endTime.getText();
		return addTime;
	}
}
