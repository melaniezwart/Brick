package game;

import actor.Player;
import encounter.Encounter;

/**
 * Created by mzwart on 28-11-2016.
 * In this version you're always in some kind of encounter
 * 1 is the kind way of talking
 * 2 is the sarcastic, funny or intimidating kind of talking
 * 3 you punch 'em
 * Any changes made to the player's skills and stats should be made in this class.
 * Health changes can happen in Encounter
 */
public class Game {

	GameFrame frame;
	StatsPanel statsPanel;
	Player player;
	Encounter encounter;

	public Game(){
	}

	public void init(Game game){
		player = new Player("Brick");
		player = player.generatePlayer();
		statsPanel = new StatsPanel(player);
		statsPanel.update();
		frame = new GameFrame(game);
		frame.getLeftTop().setText(statsPanel.setTopLeftPanel());
		frame.setVisible(true);

		startEncounter();
	}

	public void startEncounter(){
		encounter = new Encounter(player, frame);
		System.out.println("Starting encounter");
	}

	public void action(String input){
		try {
			int num = Integer.valueOf(input);
			if (num == 1 || num == 2 || num == 3){
				if (!encounter.play(input)) {
					System.out.println("Going to start new encounter");
					frame.getTextArea().append("Try again");
					player.setHealth(1000);
					statsPanel.update();
					startEncounter();
				}
			}else throw new Exception();
		} catch(Exception e){
			frame.getTextArea().append("Invalid input. Please type 1, 2 or 3.");
		} finally {
			frame.getTextArea().append("\n");
		}
	}
}
