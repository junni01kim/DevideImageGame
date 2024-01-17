import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CropImage {
		public static int cols;
		public static int rows;
		public static int cropWidth;
		public static int cropHeight;
		
		private GameFrame gameFrame = null;
		private GamePanel gamePanel = null;
		private JFileChooser fileChooser = new JFileChooser();
		private GetWidthHeightDialog getWidthHeightDialog = null;
		private BufferedImage crop[] = null;
		
		public BufferedImage[] getCrop() {return crop;}
		
		public static void setOption(int row, int col) {
			rows = row;
			cols = col;
		}
		
		CropImage(GameFrame gameFrame) {
			try {
				this.gameFrame = gameFrame;
				fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "JPG", "JPEG", "png", "PNG"));
				fileChooser.setMultiSelectionEnabled(false);
				
				fileChooser.showOpenDialog(new JDialog());
				if(fileChooser.getSelectedFile() == null) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				String imageLink = fileChooser.getSelectedFile().toString();
				BufferedImage image = ImageIO.read(new File(imageLink));
				
				getWidthHeightDialog = new GetWidthHeightDialog(); 
				while(getWidthHeightDialog.isVisible()) {
					
					System.out.println(imageLink);
				}
				
				crop = new BufferedImage[cols*rows];
				cropWidth = (int)(image.getWidth()/cols);
				cropHeight = (int)(image.getHeight()/rows);
				
				for(int i=0;i<crop.length;i++) {
					crop[i] = image.getSubimage((i%cols)*cropWidth,(i/cols)*cropHeight, cropWidth, cropHeight);
					System.out.println(crop[i]);
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