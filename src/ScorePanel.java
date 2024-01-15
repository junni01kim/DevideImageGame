import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	GameFrame gameFrame;
	
	// 그림의 완성도를 출력하는 함수
	public void reprintPerfectionLabel() {
		int count = 0;
		for(int i=0; i<gameFrame.getGamePanel().getDevideGamePanel().length; i++) {
			if(i == gameFrame.getGamePanel().getDevideGamePanel()[i].getIndex()) {
				System.out.print(gameFrame.getGamePanel().getDevideGamePanel()[i].getIndex()+" ");
				count++;
			}
		}
		System.out.println("count:"+count);
		perfectionLabel.setText(String.valueOf((int)((double)(count)/gameFrame.getGamePanel().getDevideGamePanel().length*100)));
	}
	
	private JLabel perfectionLabel = new JLabel("");
	
	ScorePanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		setBackground(Color.DARK_GRAY);
		
		perfectionLabel.setForeground(Color.WHITE);
		add(perfectionLabel);
	}
}
