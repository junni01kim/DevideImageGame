import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GamePanel extends JPanel{
	private GamePanel gamePanel = this;
	private GameFrame gameFrame;
	private CropImage cropImage;
	// �̸� �����Ͽ� �ʺ� ���� �� �������� ������ �� �� �ִ�.
	private DevideGamePanel devideGamePanel[] = null;
	private GridLayout gridLayout = null;
	
	public DevideGamePanel [] getDevideGamePanel() {return devideGamePanel;}
	
	GamePanel(GameFrame gameFrame, CropImage cropImage) {
		this.gameFrame = gameFrame;
		this.cropImage = cropImage;
		setSize(900,900);
		setLocation(30,(getHeight()-900)/CropImage.heightLength);
		setBackground(Color.DARK_GRAY);
		// ���ϴ� �԰�		
		setLayout(gridLayout);
		
		devideGamePanel = new DevideGamePanel[CropImage.widthLength*CropImage.heightLength];
		gridLayout = new GridLayout(CropImage.heightLength,CropImage.widthLength);
		
		for(int i=0;i<devideGamePanel.length;i++) {
			devideGamePanel[i] = new DevideGamePanel(i, cropImage.getCrop());
			add(devideGamePanel[i]);
		}
		
		for(int i=0;i<devideGamePanel.length;i++) {
			int rand1 = (int)(Math.random()*devideGamePanel.length);
			int rand2 = (int)(Math.random()*devideGamePanel.length);
				
			int tempIndex = devideGamePanel[rand1].getMyImageIndex();
			devideGamePanel[rand1].setMyImageIndex(devideGamePanel[rand2].getMyImageIndex());
			devideGamePanel[rand2].setMyImageIndex(tempIndex);
		}
		
		addMouseListener(new ChangeDevideGamePanel());
	}
	
	class ChangeDevideGamePanel extends MouseAdapter{
		private Point startPoint = new Point(0,0);
		private Point endPoint = new Point(0,0);
		
		//getter �Լ�
		public Point getStartPoint() {return startPoint;}
		public Point getEndPoint() {return endPoint;}
		
		public void mousePressed(MouseEvent e) {
			startPoint.setLocation(e.getPoint());
		}
		
		public void mouseReleased(MouseEvent e) {
			endPoint.setLocation(e.getPoint());
			
			// �������� �� ����Ʈ�� ���콺�� ��� ������Ʈ (�迭 �ε����� ����Ű���� �˰��ϴ� �Լ�)
			int startIndex = (int) ((int)(startPoint.getY()/(gamePanel.getHeight()/CropImage.heightLength))*CropImage.widthLength+(int)(startPoint.getX()/(gamePanel.getHeight()/CropImage.widthLength)));
			int endIndex = (int) ((int)(endPoint.getY()/(gamePanel.getHeight()/CropImage.heightLength))*CropImage.widthLength+(int)(endPoint.getX()/(gamePanel.getHeight()/CropImage.widthLength)));
			
			int tempIndex = devideGamePanel[startIndex].getMyImageIndex();
			devideGamePanel[startIndex].setMyImageIndex(devideGamePanel[endIndex].getMyImageIndex());
			devideGamePanel[endIndex].setMyImageIndex(tempIndex);
			
			gamePanel.repaint();
			
			gameFrame.getScorePanel().reprintPerfectionLabel();
		}
	}
}