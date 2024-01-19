import java.awt.Graphics2D;
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
		public static int cols = 3;
		public static int rows = 3;
		public static int cropWidth;
		public static int cropHeight;
		private static int numberOfSaveImage=0;
		
		private GameFrame gameFrame = null;
		private GamePanel gamePanel = null;
		private JFileChooser fileChooser = new JFileChooser();
		private GetWidthHeightDialog getWidthHeightDialog = null;
		public BufferedImage crop[] = null;
		
		private String imageLink = "DevideImageGame.png";
		private BufferedImage image = null;
		
		private String duplicateNameInSaveImage = "SaveImage";
		
		private boolean repaintFlag = false;
		
		public void setCrop(BufferedImage crop[]) {
			this.crop = crop;
		}
		public BufferedImage[] getCrop() {return crop;}
		public BufferedImage getImage() {return image;} 
		
		public void setGamePanel(GamePanel gamePanel) {this.gamePanel = gamePanel;} 
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
		
		private void getSavingImage(String imageLink) {
			int colsStartIndex = imageLink.indexOf(duplicateNameInSaveImage)+duplicateNameInSaveImage.length();
			cols = Integer.parseInt(imageLink.substring(colsStartIndex,imageLink.indexOf('x', colsStartIndex)));
			
			int rowsStartIndex = imageLink.indexOf('x', colsStartIndex)+1;
			rows = Integer.parseInt(imageLink.substring(rowsStartIndex,imageLink.indexOf('_', rowsStartIndex)));
			
			int underBarIndex = imageLink.indexOf("_")+1;
			String answer = imageLink.substring(underBarIndex,underBarIndex+cols*rows*2);
			
			crop = new BufferedImage[CropImage.cols*CropImage.rows];
			CropImage.cropWidth = (int)(image.getWidth()/CropImage.cols);
			CropImage.cropHeight = (int)(image.getHeight()/CropImage.rows);
			
			for(int i=0;i<CropImage.cols*CropImage.rows;i++) {
				crop[i] = image.getSubimage((i%CropImage.cols)*CropImage.cropWidth,(i/CropImage.cols)*CropImage.cropHeight, CropImage.cropWidth, CropImage.cropHeight);
			}
			
			gameFrame.remove(gamePanel);
			gameFrame.repaint();
			gamePanel = new GamePanel(gameFrame, this, answer);
			gameFrame.add(gamePanel);
			gameFrame.setGamePanel(gamePanel);
			gameFrame.revalidate();
			gameFrame.repaint();
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
			
			if(imageLink.contains(duplicateNameInSaveImage)) {
				getSavingImage(imageLink);
				return;
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
		
		public void getNewGrid() {
			getWidthHeightDialog = new GetWidthHeightDialog(this, gameFrame);
		}
		
		public void saveImage() {
			try {
				BufferedImage saveImage = new BufferedImage(cropWidth*cols, cropHeight*rows, BufferedImage.TYPE_INT_RGB);
				Graphics2D saveImageGraphics = (Graphics2D)saveImage.getGraphics();
				
				String correct = "";
				
				for(int i=0;i<gamePanel.getDevideGamePanel().length;i++) {
					saveImageGraphics.drawImage(crop[gamePanel.getDevideGamePanel()[i].getMyImageIndex()],(i%cols)*cropWidth,(i/cols)*cropHeight, null);
					correct += String.format("%02d",gamePanel.getDevideGamePanel()[i].getMyImageIndex());
				}
				
				ImageIO.write(saveImage, "png", new File(duplicateNameInSaveImage+cols+"x"+rows+"_"+correct+"_"+Integer.toString(++numberOfSaveImage)+".png"));
				JOptionPane.showMessageDialog(null, "저장되었습니다", "저장", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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