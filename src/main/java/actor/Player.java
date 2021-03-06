package actor;

/**
 * Created by mzwart on 28-11-2016.
 */
public class Player extends Actor{

	private int experience;
	private static int[] expArray = {0, 50, 60, 75, 90, 110, 140, 170, 200, 240, 285, 330, 385, 450, 510, 580, 660, 750, 850};
	private int coins;

	public Player(String name){
		this.name = name;
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
}
