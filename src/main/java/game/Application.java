package game;

/**
 * Created by mzwart on 28-11-2016.
 */
public class Application {
	public static void main(String[] args){
		Game game = new Game();
		game.init(game);
	}
}
