import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameFrame extends JFrame{
	private CropImage cropImage = null;
	private GamePanel gamePanel = null;
	private ScorePanel scorePanel = null;
	
	public void setGamePanel(GamePanel gamePanel) {this.gamePanel = gamePanel;}
	
	public GamePanel getGamePanel() {return gamePanel;}
	public ScorePanel getScorePanel() {return scorePanel;}
	
	public GameFrame() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("이미지 분할 게임");
		setSize(1280, 1024);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem [] menuItem = new JMenuItem [3];
		String [] itemTitle = {"Image","Grid","Exit"};
		JMenu fileMenu = new JMenu("File");
		
		MenuActionListener menuActionListener = new MenuActionListener();
		for(int i=0; i<menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(menuActionListener);
			fileMenu.add(menuItem[i]);
		}
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		scorePanel = new ScorePanel(this);
		scorePanel.setSize(getWidth()-900-110, 900);
		scorePanel.setLocation(960,(getHeight()-900)/CropImage.cols-20);
		add(scorePanel);
		
		cropImage = new CropImage(this);
		
		setVisible(true);
	}
	
	class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String menuItemName = e.getActionCommand();
			switch(menuItemName) {
			case "Image":
				cropImage.getNewImage();
				break;
			case "Grid":
				cropImage.getNewGrid();
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
		
	}
}
