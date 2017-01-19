package garden.sprinkler.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import garden.model.Sprinkler;


public class SprinklerMapView extends JPanel implements Observer{
	private Map<String, SprinklerLabel> spkLabels = new HashMap<String, SprinklerLabel>();
	
	SprinklerLabel northOne;
	SprinklerLabel northTwo;
	SprinklerLabel northThr;
	
	SprinklerLabel westOne;
	SprinklerLabel westTwo;
	
	SprinklerLabel eastOne;
	SprinklerLabel eastTwo;
	
	SprinklerLabel southOne;
	SprinklerLabel southTwo;
	SprinklerLabel southThr;
	
	public SprinklerMapView() {
		setBorder(new TitledBorder("Sprinkler Map"));
		setLayout(null);
		
		initiate();
	}
	
//	public void paintComponent(Graphics g) {
//		int width = getWidth();
//		
//		g.setColor(Color.green);
//        
//        int x1 = 0;
//        for (int i = 0; i < width; i++) {
//        		g.drawLine(0, 130, x1, 130);
//        		x1 += 1;
//        }
//        
//        int x2 = 0;
//        for (int i = 0; i < width; i++) {
//	    		g.drawLine(0, 300, x2, 300);
//	    		x2 += 1;
//        }
//        
//        int y = 130;
//        for (int i = 130; i < 300; i++) {
//	    		g.drawLine(280, y, 280, 300);
//	    		y += 1;
//        }
//	}
	
	public void addMouseListener(MouseListener listener) {
		northOne.addMouseListener(listener);
		northTwo.addMouseListener(listener);
		northThr.addMouseListener(listener);
		
		westOne.addMouseListener(listener);
		westTwo.addMouseListener(listener);
		
		eastOne.addMouseListener(listener);
		eastTwo.addMouseListener(listener);
		
		southOne.addMouseListener(listener);
		southTwo.addMouseListener(listener);
		southThr.addMouseListener(listener);
	}
	
	
	public SprinklerLabel getSprinklerLabel(String id) {
		return spkLabels.get(id);
	}
	
	public void update(Observable o, Object arg) {
		Sprinkler spk = (Sprinkler) o;
		boolean isOn = spk.getStatus();
		SprinklerLabel spkLabel = getSprinklerLabel(spk.getID());
		
		if (spkLabel == null) return;
		
		if (isOn == true) spkLabel.onSpriklerOn();
		else spkLabel.onSprinklerOff();
	}
	
	private void initiate() {
		northOne = new SprinklerLabel("N1", "N1", "north", 30, 60);
		northTwo = new SprinklerLabel("N2", "N2", "north", 245, 30);
		northThr = new SprinklerLabel("N3", "N3", "north", 460, 60);
		
		westOne = new SprinklerLabel("W1", "W1", "west", 140, 135);
		westTwo = new SprinklerLabel("W2", "W2", "west", 90, 220);
		
		eastOne = new SprinklerLabel("E1", "E1", "east", 350, 135);
		eastTwo = new SprinklerLabel("E2", "E2", "east", 400, 220);
		
		southOne = new SprinklerLabel("S1", "S1", "south", 30, 340);
		southTwo = new SprinklerLabel("S2", "S2", "south", 245, 300);
		southThr = new SprinklerLabel("S3", "S3", "south", 460, 340);
		
		add(northOne);
		add(northTwo);
		add(northThr);
		add(westOne);
		add(westTwo);
		add(eastOne);
		add(eastTwo);
		add(southOne);
		add(southTwo);
		add(southThr);
		
		spkLabels.put("N1", northOne);
		spkLabels.put("N2", northTwo);
		spkLabels.put("N3", northThr);
		spkLabels.put("W1", westOne);
		spkLabels.put("W2", westTwo);
		spkLabels.put("E1", eastOne);
		spkLabels.put("E2", eastTwo);
		spkLabels.put("S1", southOne);
		spkLabels.put("S2", southTwo);
		spkLabels.put("S3", southThr);
		
//		add(new JLabel());
	}
}
