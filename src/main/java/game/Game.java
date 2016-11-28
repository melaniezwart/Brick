package game;

import actor.Player;

/**
 * Created by mzwart on 28-11-2016.
 */
public class Game {

	GameFrame frame;
	StatsPanel statsPanel;
	Player player;

	public Game(){
	}

	public void init(Game game){
		player = new Player("Brick");
		statsPanel = new StatsPanel(player);
		frame = new GameFrame(game);
		frame.getLeftTop().setText(statsPanel.setTopLeftPanel());
		frame.setVisible(true);
	}

	public void action(String input){
		frame.getTextArea().append("\n");
	}
}
