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
	int previousAction = 0; //the number of the previous action
	int currentAction = 0;
	boolean lastReaction; //if the last reaction was positive

	public Encounter(Player player, GameFrame frame){
		this.player = player;
		this.frame = frame;
		this.enemy = new Enemy();
		enemy = enemy.generateEnemy();
	}

	public boolean play(String input){
		action(input); //Start with your own input
		enemyAction(); //Then enemy action follows
		previousAction = Integer.valueOf(input); //Sets this action for next round
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
		currentAction = action;
		switch(action){
			case 1: playerKindAction();
				frame.getTextArea().append("Jolly good, sir!");
				break;
			case 2: playerWittyAction();
				frame.getTextArea().append("Sarcastic prick");
				break;
			case 3: punchEnemy();
				frame.getTextArea().append("You punch 'em");
				break;
		}
	}

	public void enemyAction(){
		Random rng = new Random();
		if(currentAction == 3){
			if(enemy.getAggression() > 20) //Enemy is very likely to punch back, unless they're non-aggressive
				punchPlayer();
			else if(previousAction == 3) //Two punches in a row is always a fight
				punchPlayer();
			if(enemy.getFriendship() < 20 && enemy.getAggression() > 49) //Non-aggressive enemies will come up with a witty remark instead
				enemyWittyAction();
			else enemyKindAction();
		} else if (currentAction == 2){
			if(enemy.isAsshole()){

			}
		}


		if(enemy.getAggression() + rng.nextInt(50) > 90){
			punchPlayer();
		}
		if(enemy.getFriendship() > 50) {
			//more likely to get a kind or witty action.
			if(enemy.isAsshole()){
				//more likely to get a witty action
			} else {
				//more likely to get a kind action
			}
		}
		switch(rng.nextInt(3)){
			case 0: enemyKindAction();
			case 1: enemyWittyAction();
			case 2: punchPlayer();
		}
	}

	public void punchEnemy(){
		int damage = player.getStrength();
		enemy.changeHealth(-damage);
		frame.getTextArea().append("You punch the enemy for " + damage + " damage. They have " + enemy.getHealth() + " health left.\n");
		lastReaction = false;
		player.punch();
	}

	public void punchPlayer(){
		int damage = enemy.getStrength();
		player.changeHealth(-damage);
		frame.getTextArea().append("The enemy punches you for " + damage + " damage. You have " + player.getHealth() + " health left.\n");
	}

	public void playerKindAction(){
		if(enemy.isAsshole()) {
			enemy.changeFriendship(-rng.nextInt(20));
			lastReaction = false;
		}
		else {
			enemy.changeFriendship(rng.nextInt(20));
			lastReaction = true;
		}
	}

	public void enemyKindAction(){
		enemy.changeFriendship(rng.nextInt(20));
	}

	public void playerWittyAction(){
		if(enemy.isAsshole()) {
			enemy.changeFriendship(rng.nextInt(20));
			lastReaction = true;
		}
		else {
			enemy.changeFriendship(-rng.nextInt(20));
			lastReaction = false;
		}
	}

	public void enemyWittyAction(){ //If the enemy is an asshole, he enjoys making fun of you
		if(enemy.isAsshole()) enemy.changeFriendship(rng.nextInt(20));
		else enemy.changeFriendship(-rng.nextInt(20));
	}
}
