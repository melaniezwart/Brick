package actor;

/**
 * Created by mzwart on 29-11-2016.
 */
public class Skill {
	private int level;
	private int experience;

	private static int[] expArray = {0, 50, 60, 75, 90, 110, 140, 170, 200, 240, 285, 330, 385, 450, 510, 580, 660, 750, 850};

	public int getExperience() {
		return experience;
	}

	public void addExperience(int gainedExp){
		this.experience = this.experience + gainedExp;
	}

	public void levelUp(){
		while(this.experience > expArray[this.level]) {
			this.experience -= expArray[this.level];
			this.level++;
		}
	}
}
