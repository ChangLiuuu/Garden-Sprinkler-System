package garden.sprinkler.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import garden.model.TemperatureSensor;

public class TemperatureView extends JPanel implements ActionListener{
private HashMap<String, TemperatureSensor> tempSensorMap;
	
	private JComboBox groupChoice;
	private JTextField tempInput;
	
	private JButton confirm;
	private JButton set;
	
	private JTextField below;
	private JTextField above;
	
	public TemperatureView(HashMap<String, TemperatureSensor> tempSensorMap) {
		this.tempSensorMap = tempSensorMap;
		initiatePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String group = groupChoice.getSelectedItem().toString().toLowerCase();
		TemperatureSensor tempSensor = tempSensorMap.get(group);
		
		JButton button = (JButton) e.getSource();
		
		if (button == confirm) {
			double curTemp = Double.parseDouble(tempInput.getText());
			tempSensor.setCurTemp(curTemp);
		} else {
			double belowTemp = Double.parseDouble(below.getText());
			tempSensor.setBelowTemp(belowTemp);
			
			double aboveTemp = Double.parseDouble(above.getText());
			tempSensor.setAboveTemp(aboveTemp);
		}
	}
	
	private void initiatePanel() {
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("Temperature Sensor"));
		
		JLabel groupName = new JLabel("group");
		add(groupName);
		
		String[] directions = {"North", "South", "East", "West"};
		groupChoice = new JComboBox(directions);
		add(groupChoice);
		
		tempInput = new JTextField(5);
		tempInput.setText("80.00");
		add(tempInput);
		
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		add(confirm);
		
		JLabel belowLabel = new JLabel("Below");
		add(belowLabel);
		
		below = new JTextField(5);
		below.setText("75.00");
		add(below);
		
		JLabel aboveLabel = new JLabel("Above");
		add(aboveLabel);
		
		above = new JTextField(5);
		above.setText("95.00");
		add(above);
		
		set = new JButton("set");
		set.addActionListener(this);
		add(set);
	}

}
