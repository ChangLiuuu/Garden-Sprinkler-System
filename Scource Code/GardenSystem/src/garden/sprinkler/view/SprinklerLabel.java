package garden.sprinkler.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SprinklerLabel extends JLabel {
	private static final int LABEL_WIDTH = 65;
	private static final int LABEL_HEIGHT = 65;
	
	private ImageIcon sprinklerOff = getResizeImageIcon("sprinklerOff.png", LABEL_HEIGHT-25, LABEL_WIDTH-25);
	private ImageIcon sprinklerOn = getResizeImageIcon("sprinklerOn.png", LABEL_HEIGHT-25, LABEL_WIDTH-25);
	
	private String sprinklerId;
	private String sprinklerGroup;
	
	public SprinklerLabel(String sprinklerId, String text, String group, int locationX, int locationY){
		super();
		this.setText(text);
		this.setIcon(sprinklerOff);
		this.setSize(LABEL_HEIGHT, LABEL_WIDTH);
		this.setLocation(locationX, locationY);
		
		this.sprinklerId = sprinklerId;
		this.sprinklerGroup = group;
	}
	
	public String getSprinklerId(){
		return sprinklerId;
	}
	
	public void onSprinklerOff(){
		setIcon(sprinklerOff);
	}
	
	public void onSpriklerOn(){
		setIcon(sprinklerOn);
	}
	
	public String getSprinklerGroup() {
		return sprinklerGroup;
	}
	
	private ImageIcon getResizeImageIcon(String imagePath, int width, int length) {
		ImageIcon oriImageIcon = new ImageIcon(imagePath);
		Image oriImage = oriImageIcon.getImage();
		Image resizedImage = oriImage.getScaledInstance(length, width, Image.SCALE_SMOOTH);
		ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
		return resizedImageIcon;
	}

}
