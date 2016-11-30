package actor;

import java.util.Random;

/**
 * Created by mzwart on 28-11-2016.
 */
public class Player extends Actor{

	private int experience;
	private static int[] expArray = {0, 50, 60, 75, 90, 110, 140, 170, 200, 240, 285, 330, 385, 450, 510, 580, 660, 750, 850};
	private int coins;
	private Skill wit;
	private Skill fisticuffs;
	private Skill kindness;
	private Random rng = new Random();

	public Player(String name){
		this.name = name;
	}

	public Player generatePlayer(){
		Player player = new Player("Brick");
		player.setLevel(1);
		player.setStrength(100);
		return player;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void levelUp(){
		while(this.experience > expArray[this.level]) {
			this.experience -= expArray[this.level];
			this.level++;
		}
	}

	public void addExperience(int gainedExp){
		this.experience = this.experience + gainedExp;
	}

	public void changeCoins(int change){
		this.coins = this.coins + change;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void punch(){
		this.aggression++;
		this.fisticuffs.addExperience(rng.nextInt(10));
	}

	public void kindAction(){
		this.aggression--;
		this.kindness.addExperience(rng.nextInt(10));
	}

	public void sarcasticAction(){
		this.wit.addExperience(rng.nextInt(10));
	}
}
