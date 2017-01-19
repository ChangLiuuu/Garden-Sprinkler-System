package garden.sprinkler.view;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class SprinklerSetView extends JPanel {
	private JPanel panelNorth = new JPanel();
	private JPanel panelSouth = new JPanel();
	private JPanel panelEast = new JPanel();
	private JPanel panelWest = new JPanel();
	private JPanel panelFlow = new JPanel();
	private JPanel panelAutoCon = new JPanel();
	
	private JButton buttonNor;
	private JButton buttonSou;
	private JButton buttonEast;
	private JButton buttonWest;
	private JButton buttonFlow;
	private JButton autoControl;
	private JButton buttonDetail;
	
	private JTextField flow = new JTextField(4);
	
	public SprinklerSetView() {
		setBorder(new TitledBorder("Setting Part"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel labelN = new JLabel("north");
		buttonNor = new JButton("on");
		buttonNor.setActionCommand("north");
		panelNorth.add(labelN);
		panelNorth.add(buttonNor);
		
		add(panelNorth);
		
		JLabel labelE = new JLabel("east");
		buttonEast = new JButton("on");
		panelEast.add(labelE);
		panelEast.add(buttonEast);
		buttonEast.setActionCommand("east");
		
		add(panelEast);
		
		JLabel labelW = new JLabel("west");
		buttonWest = new JButton("on");
		panelWest.add(labelW);
		panelWest.add(buttonWest);
		buttonWest.setActionCommand("west");
		
		add(panelWest);
		
		JLabel labelS = new JLabel("south");
		buttonSou = new JButton("on");
		panelSouth.add(labelS);
		panelSouth.add(buttonSou);
		buttonSou.setActionCommand("south");
		
		add(panelSouth);
		
		JLabel labelFlow = new JLabel("Flow");
		flow.setText("2.0");
		panelFlow.add(labelFlow);
		panelFlow.add(flow);
		buttonFlow = new JButton("set");
		panelFlow.add(buttonFlow);
		buttonFlow.setActionCommand("flow");
		
		add(panelFlow);
		
		JLabel labelC = new JLabel("Auto Control");
		autoControl = new JButton("on");
		panelAutoCon.add(labelC);
		panelAutoCon.add(autoControl);
		autoControl.setActionCommand("auto");
		
		add(panelAutoCon);
		
		buttonDetail = new JButton("get details");
		buttonDetail.setActionCommand("dtl");
		add(buttonDetail);
	}
	
	public void addActionListener(ActionListener listener) {
		buttonNor.addActionListener(listener);
		buttonSou.addActionListener(listener);
		buttonEast.addActionListener(listener);
		buttonWest.addActionListener(listener);
		autoControl.addActionListener(listener);
		buttonDetail.addActionListener(listener);
	}
	
	public double getFlowNum() {
		try{
			return Double.parseDouble(flow.getText());
		} catch(Exception e) {
			return 0;
		}
	}
}
