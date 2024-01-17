import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CropImage {
		// 3x3은 기본 규격
		public static int cols = 2;
		public static int rows = 3;
		public static int cropWidth;
		public static int cropHeight;
		
		private GameFrame gameFrame = null;
		private GamePanel gamePanel = null;
		private JFileChooser fileChooser = new JFileChooser();
		private GetWidthHeightDialog getWidthHeightDialog = null;
		public BufferedImage crop[] = null;
		
		private String imageLink = "DevideImageGame.png";
		private BufferedImage image = null;
		
		private boolean repaintFlag = false;
		
		public void setCrop(BufferedImage crop[]) {
			this.crop = crop;
		}
		public BufferedImage[] getCrop() {return crop;}
		public BufferedImage getImage() {return image;} 
		
		public static void setOption(int row, int col) {
			rows = row;
			cols = col;
		}
		
		public void toggleRepaintFlag() {
			if(repaintFlag == true)
				repaintFlag = false;
			else
				repaintFlag = true;
		}
		
		public void onNotify() {
			notify();
		}
		
		synchronized private void checkWait() {
			if(repaintFlag == true) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
				notify();
		}
		
		public void getNewGrid() {
			getWidthHeightDialog = new GetWidthHeightDialog(this, gameFrame);
		}
		
		public void getNewImage() {
			fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "JPG", "JPEG", "png", "PNG"));
			fileChooser.setMultiSelectionEnabled(false);
			
			fileChooser.showOpenDialog(new JDialog());
			if(fileChooser.getSelectedFile() == null) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			
			String imageLink = fileChooser.getSelectedFile().toString();
			try {
				image = ImageIO.read(new File(imageLink));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			cropWidth = (int)(image.getWidth()/cols);
			cropHeight = (int)(image.getHeight()/rows);
			for(int i=0;i<crop.length;i++) {
				crop[i] = image.getSubimage((i%cols)*cropWidth,(i/cols)*cropHeight, cropWidth, cropHeight);
			}
			
			gameFrame.remove(gamePanel);
			gamePanel = new GamePanel(gameFrame, this);
			gameFrame.add(gamePanel);
			gameFrame.setGamePanel(gamePanel);
			gameFrame.revalidate();
			gameFrame.repaint();
		}
		
		CropImage(GameFrame gameFrame) {
			try {
				this.gameFrame = gameFrame;
				
				image = ImageIO.read(new File(imageLink));
				
				crop = new BufferedImage[cols*rows];
				cropWidth = (int)(image.getWidth()/cols);
				cropHeight = (int)(image.getHeight()/rows);
				
				for(int i=0;i<crop.length;i++) {
					crop[i] = image.getSubimage((i%cols)*cropWidth,(i/cols)*cropHeight, cropWidth, cropHeight);
				}
				
				gamePanel = new GamePanel(gameFrame, this);
				gameFrame.add(gamePanel);
				gameFrame.setGamePanel(gamePanel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}