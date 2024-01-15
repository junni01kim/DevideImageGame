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
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private GamePanel gamePanel = this;
	private GameFrame gameFrame;
	private CropImage cropImage;
	private DevideGamePanel devideGamePanel[] = new DevideGamePanel[9];
	private GridLayout gridLayout = new GridLayout(3,3,5,5);
	
	public DevideGamePanel [] getDevideGamePanel() {return devideGamePanel;}
	
	GamePanel(GameFrame gameFrame) {
		cropImage = new CropImage();
		this.gameFrame = gameFrame;
		setBackground(Color.DARK_GRAY);
		// 원하는 규격
		setLayout(gridLayout);
		
		for(int i=0;i<9;i++) {
			devideGamePanel[i] = new DevideGamePanel(i, cropImage.getCrop());
			add(devideGamePanel[i]);
		}
		
		for(int i=0;i<9;i++) {
			int rand1 = (int)(Math.random()*9);
			int rand2 = (int)(Math.random()*9);
				
			int tempIndex = devideGamePanel[rand1].getMyImageIndex();
			devideGamePanel[rand1].setMyImageIndex(devideGamePanel[rand2].getMyImageIndex());
			devideGamePanel[rand2].setMyImageIndex(tempIndex);
		}
		
		addMouseListener(new ChangeDevideGamePanel());
	}
	
	class ChangeDevideGamePanel extends MouseAdapter{
		private Point startPoint = new Point(0,0);
		private Point endPoint = new Point(0,0);
		
		//getter 함수
		public Point getStartPoint() {return startPoint;}
		public Point getEndPoint() {return endPoint;}
		
		public void mousePressed(MouseEvent e) {
			startPoint.setLocation(e.getPoint());
		}
		
		public void mouseReleased(MouseEvent e) {
			endPoint.setLocation(e.getPoint());
			
			// 시작점과 끝 포인트의 마우스가 어느 컴포넌트 (배열 인덱스를 가르키는지 알게하는 함수)
			int startIndex = (int) ((int)(startPoint.getY()/300)*3+(int)(startPoint.getX()/300));
			int endIndex = (int) ((int)(endPoint.getY()/300)*3+(int)(endPoint.getX()/300));
			
			int tempIndex = devideGamePanel[startIndex].getMyImageIndex();
			devideGamePanel[startIndex].setMyImageIndex(devideGamePanel[endIndex].getMyImageIndex());
			devideGamePanel[endIndex].setMyImageIndex(tempIndex);
			
			gamePanel.repaint();
			
			gameFrame.getScorePanel().reprintPerfectionLabel();
		}
	}
	
	class CropImage {
		private BufferedImage crop[] = new BufferedImage[9];
		
		public BufferedImage[] getCrop() {return crop;}
		
		CropImage() {
			try {
				BufferedImage image = ImageIO.read(new File("DevideImageGame.png"));
				for(int i=0;i<crop.length;i++) {
					crop[i] = image.getSubimage((i%3)*300,(i/3)*300, 300, 300);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}