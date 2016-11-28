package game;

import actor.Player;

/**
 * Created by mzwart on 28-11-2016.
 */
public class StatsPanel {
	Player player;
	private String name;
	private int level;
	private int experience;
	private int aggression;
	private int strength;
	private int charisma;

	public StatsPanel(Player player){
		this.player = player;
		this.name = player.getName();
	}

	public void update(){
		this.level = player.getLevel();
		this.experience = player.getExperience();
		this.aggression = player.getAggression();
		this.strength = player.getStrength();
		this.charisma = player.getCharisma();
	}

	public String setTopLeftPanel(){
		String statsPanel;
		statsPanel = "Name: " + name + "\nLevel: " + level + "\nExperience: " + experience
			+ "\n\nAggression: " + aggression + "\nStrength: " + strength + "\nCharisma: " + charisma;
		return statsPanel;
	}

}
