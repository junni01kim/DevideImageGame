import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DevideGamePanel extends JPanel{
	private Image images[] = new Image[CropImage.cols*CropImage.rows];
	private int index;
	private int myImageIndex;
	
	public void setIndex(int index) {this.index = index;}
	public int getIndex() {return index;}
	
	public void setMyImageIndex(int myImageIndex) {this.myImageIndex = myImageIndex;}
	public int getMyImageIndex() {return myImageIndex;}
	
	public void printIndex() {System.out.println(index);} 
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(images[myImageIndex], 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	DevideGamePanel(int index, BufferedImage[] images, int answerIndex) {
		this.images = images;
		this.index = index;
		myImageIndex = answerIndex;
		setVisible(true);
	}
	
	DevideGamePanel(int index, BufferedImage[] images) {
		this.images = images;
		this.index = index;
		myImageIndex = index;
		setVisible(true);
	}
}
