package actor;

import java.util.Random;

/**
 * Created by mzwart on 28-11-2016.
 */
public class Enemy extends Actor {

	private String[] nameList = {"John", "Trump", "Bush", "Mr. Snugglepuff", "Ivan the Terrible", "Ivan the Mediocre", "Vlad"};

	public Enemy generateEnemy(){
		Random rng = new Random();
		Enemy enemy = new Enemy();
		enemy.setName(nameList[rng.nextInt(nameList.length - 1)]);
		enemy.setAggression(rng.nextInt(100));
		enemy.setStrength(rng.nextInt(100));
		enemy.setCharisma(rng.nextInt(100));
		enemy.setHealth(rng.nextInt(1000)+500);
		return enemy;
	}
}
