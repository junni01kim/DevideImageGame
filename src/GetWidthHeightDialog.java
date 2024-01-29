import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GetWidthHeightDialog extends JDialog{
	private GameFrame gameFrame = null;
	private CropImage cropImage = null; 
	private JTextField widthTextField = null;
	private JTextField heightTextField = null;
	private JButton setOptionButton = null;
	
	public GetWidthHeightDialog(CropImage cropImage, GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.cropImage = cropImage;
		setTitle("행열 지정하기");
		setSize(250,150);
		setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		
		add(new JLabel("     행 수      "));
		widthTextField = new JTextField(13);
		add(widthTextField);
		
		add(new JLabel("     열 수      "));
		heightTextField = new JTextField(13);
		add(heightTextField);
		
		JButton setUpButton = new JButton("Set Option");
		setUpButton.addActionListener(new setOptionButtonActionListener());
		add(setUpButton);
		
		setVisible(true);
	}
	
	private class setOptionButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GamePanel gamePanel = gameFrame.getGamePanel();
			CropImage.setOption(Integer.parseInt(widthTextField.getText().trim()), Integer.parseInt(heightTextField.getText().trim()));

			setVisible(false);

			cropImage.setCrop(new BufferedImage[CropImage.cols*CropImage.rows]);
			
			
			CropImage.cropWidth = (int)(cropImage.getImage().getWidth()/CropImage.cols);
			CropImage.cropHeight = (int)(cropImage.getImage().getHeight()/CropImage.rows);
			for(int i=0;i<CropImage.cols*CropImage.rows;i++) {
				cropImage.getCrop()[i] = cropImage.getImage().getSubimage((i%CropImage.cols)*CropImage.cropWidth,(i/CropImage.cols)*CropImage.cropHeight, CropImage.cropWidth, CropImage.cropHeight);
			}
			
			gameFrame.remove(gamePanel);
			gameFrame.repaint();
			gamePanel = new GamePanel(gameFrame, cropImage);
			cropImage.setGamePanel(gamePanel);
			gameFrame.setGamePanel(gamePanel);
			gameFrame.add(gamePanel);
			gameFrame.revalidate();
			gameFrame.repaint();
		}	
	}
}
