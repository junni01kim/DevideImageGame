import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private GamePanel gamePanel = null;
	private ScorePanel scorePanel = null;
	
	public GamePanel getGamePanel() {return gamePanel;}
	public ScorePanel getScorePanel() {return scorePanel;}
	
	public GameFrame() {
		setTitle("�̹��� ���� ����");
		setSize(1280, 1024);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new GamePanel(this);
		gamePanel.setSize(900,900);
		gamePanel.setLocation(30,(getHeight()-900)/CropImage.heightLength);
		add(gamePanel);
		
		scorePanel = new ScorePanel(this);
		scorePanel.setSize(getWidth()-900-110, 900);
		scorePanel.setLocation(960,(getHeight()-900)/CropImage.heightLength);
		add(scorePanel);
		
		setVisible(true);
	}
}
