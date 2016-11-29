package encounter;

import actor.Enemy;
import actor.Player;
import game.GameFrame;

import java.util.Random;

/**
 * Created by mzwart on 29-11-2016.
 * When an encounter is created, a new enemy is generated.
 * For every enemy you create a new instance of Encounter.
 * Health changes can happen in this class, any other changes to the player must be made in Game
 */
public class Encounter {

	Player player;
	Enemy enemy;
	GameFrame frame;
	Random rng = new Random();

	public Encounter(Player player, GameFrame frame){
		this.player = player;
		this.frame = frame;
		this.enemy = new Enemy();
		enemy = enemy.generateEnemy();
	}

	public boolean play(String input){
		action(input);

		enemyAction();
		if(player.getHealth() <= 0 || enemy.getHealth() <= 0){
			if(player.getHealth() > 0)
				win();
			else lose();
			return false;
		}
		return true;
	}

	public void win(){
		player.addExperience(rng.nextInt(20));
		player.changeCoins(rng.nextInt(20));
		frame.getTextArea().append("\nYou win!");
	}

	public void lose(){
		int loseAmount = rng.nextInt(20);
		if(player.getCoins() > loseAmount)
			player.changeCoins(-loseAmount);
		else player.setCoins(0);
		frame.getTextArea().append("\nYou lose. You also lost some coins.");
	}

	public void action(String input){
		int action = Integer.valueOf(input);
		switch(action){
			case 1:
				frame.getTextArea().append("Jolly good, sir!");
				break;
			case 2:
				frame.getTextArea().append("Sarcastic prick");
				break;
			case 3: punchEnemy();
				frame.getTextArea().append("You punch 'em");
				break;
		}
	}

	public void enemyAction(){
		Random rng = new Random();
		switch(rng.nextInt(3)){
			case 0:
			case 1:
			case 2: punchPlayer();
		}
	}

	public void punchEnemy(){
		int damage = player.getStrength();
		enemy.changeHealth(-damage);
		frame.getTextArea().append("You punch the enemy for " + damage + " damage. They have " + enemy.getHealth() + " health left.\n");
	}

	public void punchPlayer(){
		int damage = enemy.getStrength();
		player.changeHealth(-damage);
		frame.getTextArea().append("The enemy punches you for " + damage + " damage. You have " + player.getHealth() + " health left.\n");

	}
}
