package garden.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import garden.model.Sprinkler;
import garden.model.SprinklerBank;
import garden.sprinkler.view.SprinklerLabel;
import garden.sprinkler.view.SprinklerMapView;

public class SprinklerMapController implements MouseListener {
	private SprinklerMapView sprinklerMapView;
	private SprinklerBank sprinklerBank;
	
	public SprinklerMapController(SprinklerMapView sprinklerMapView, SprinklerBank sprinklerBank) {
		this.sprinklerMapView = sprinklerMapView;
		this.sprinklerBank = sprinklerBank;
		this.sprinklerMapView.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		SprinklerLabel sprinklerLabel = (SprinklerLabel) e.getSource();
		String labelID = sprinklerLabel.getSprinklerId();
		Sprinkler sprinkler = sprinklerBank.getSingleSprinkler(labelID);
		if (sprinkler == null) return;
		
		boolean isOn = sprinkler.getStatus();
		if (isOn == true) {
			sprinkler.turningOff();
		}
		else {
			sprinkler.turningOn();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

}
