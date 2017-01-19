package garden.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import garden.model.SprinklerBank;
import garden.sprinkler.view.DetailsView;
import garden.sprinkler.view.SprinklerSetView;

public class SprinklerSetController implements ActionListener {
	private SprinklerSetView sprinklerSetView;
	private SprinklerBank sprinklerBank;
	
	public SprinklerSetController(SprinklerSetView sprinklerSetView, SprinklerBank sprinklerBank) {
		this.sprinklerSetView = sprinklerSetView;
		this.sprinklerBank = sprinklerBank;
		this.sprinklerSetView.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String label = e.getActionCommand();
		
		if (label.equals("dtl")){
			showDetails();
		} else if (label.equals("flow")) {
			double flow = sprinklerSetView.getFlowNum();
			sprinklerBank.changeWaterFlow(flow);
		}
		else if (label.equals("auto")) {
			autoController(button);
		} else {
			perform(button, label);
		}
	}
	
	private void showDetails() {
		JPanel details = new DetailsView(sprinklerBank);
		
		final JOptionPane pane = new JOptionPane(details);
	    final JDialog d = pane.createDialog(null, "Sprinkler Status");
//	    d.setLocation(700,10);
	    d.setSize(new Dimension(400, 350));
	    d.setVisible(true);
	}
	
	private void autoController(JButton button) {
		if (button.getText().equals("on")) {
			sprinklerBank.turnOnAutoControl();
			button.setText("off");
		} else {
			sprinklerBank.turnOffAutoControl();
			button.setText("on");
		}
	}
	
	private void perform(JButton button, String label) {
		int dialogButton = JOptionPane.OK_CANCEL_OPTION;
		int dialogRes = JOptionPane.showConfirmDialog(null, "Make sure to change the status?", "", dialogButton);
		
		if (dialogRes == JOptionPane.OK_OPTION) {
			if (button.getText().equals("on")) {
				sprinklerBank.groupTurningOn(label);
//				System.out.println("Turn on group " + label);
				button.setText("off");
			} else if (button.getText().equals("off")) {
				sprinklerBank.groupTurningOff(label);
//				System.out.println("Turn off group " + label);
				button.setText("on");
			}
		}
	}
}
