import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private CropImage cropImage = null;
	private GamePanel gamePanel = null;
	private ScorePanel scorePanel = null;
	
	public void setGamePanel(GamePanel gamePanel) {this.gamePanel = gamePanel;}
	
	public GamePanel getGamePanel() {return gamePanel;}
	public ScorePanel getScorePanel() {return scorePanel;}
	
	public GameFrame() {
		setTitle("이미지 분할 게임");
		setSize(1280, 1024);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cropImage = new CropImage(this);
		//gamePanel = new GamePanel(this);
		//add(gamePanel);
		
		scorePanel = new ScorePanel(this);
		scorePanel.setSize(getWidth()-900-110, 900);
		scorePanel.setLocation(960,(getHeight()-900)/CropImage.rows);
		add(scorePanel);
		
		setVisible(true);
	}
}
