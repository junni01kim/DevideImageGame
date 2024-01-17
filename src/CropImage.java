import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CropImage {
		public static int widthLength;
		public static int heightLength;
		public static int cropWidth;
		public static int cropHeight;
		
		private GameFrame gameFrame;
		
		private JFileChooser fileChooser = new JFileChooser();
		private BufferedImage crop[] = new BufferedImage[widthLength*heightLength];
		
		public BufferedImage[] getCrop() {return crop;}
		
		public static void setOption(int width, int height) {
			widthLength = width;
			heightLength = height;
		}
		
		CropImage(GameFrame gameFrame) {
			try {
				this.gameFrame = gameFrame;
				GetWidthHeightDialog getWidthHeightDialog = new GetWidthHeightDialog();
				while(getWidthHeightDialog.isVisible()) { 
					System.out.println("¹Ýº¹¹®");
				}
				
				fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "JPG", "JPEG", "png", "PNG"));
				fileChooser.setMultiSelectionEnabled(false);
				
				fileChooser.showOpenDialog(new JDialog());
				String imageLink = fileChooser.getSelectedFile().toString();
				
				BufferedImage image = ImageIO.read(new File(imageLink));
				cropWidth = (int)(image.getWidth()/widthLength);
				cropHeight = (int)(image.getHeight()/heightLength);
				
				for(int i=0;i<crop.length;i++) {
					crop[i] = image.getSubimage((i%widthLength)*cropWidth,(i/widthLength)*cropHeight, cropWidth, cropHeight);
				}
				
				GamePanel gamePanel = new GamePanel(gameFrame, this);
				gameFrame.add(gamePanel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}