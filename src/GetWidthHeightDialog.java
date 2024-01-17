import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GetWidthHeightDialog extends JDialog{
	private JTextField widthTextField = null;
	private JTextField heightTextField = null;
	private JButton setOptionButton = null;
	
	public GetWidthHeightDialog() {
		setTitle("행열 지정하기");
		setSize(250,150);
		setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		
		add(new JLabel("     행 수      "));
		heightTextField = new JTextField(13);
		add(heightTextField);
		
		add(new JLabel("     열 수      "));
		widthTextField = new JTextField(13);
		add(widthTextField);
		
		JButton setUpButton = new JButton("Set Option");
		setUpButton.addActionListener(new setOptionButtonActionListener());
		add(setUpButton);
		
		setVisible(true);
	}
	
	private class setOptionButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CropImage.setOption(Integer.parseInt(heightTextField.getText().trim()),Integer.parseInt(widthTextField.getText().trim()));
			setVisible(false);
		}	
	}
}
