import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CropImage {
		public final static int widthLength = 2;
		public final static int heightLength = 1;
		public static int cropWidth;
		public static int cropHeight;
		
		private JFileChooser fileChooser = new JFileChooser();
		private BufferedImage crop[] = new BufferedImage[widthLength*heightLength];
		
		public BufferedImage[] getCrop() {return crop;}
		
		CropImage() {
			try {
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}