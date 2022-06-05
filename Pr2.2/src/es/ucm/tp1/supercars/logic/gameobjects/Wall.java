package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle{
	public static final String INFO = "[WALL] hard obstacle";
	private static final String A[] = new String[] {"░", "▒", "█"};
	
	public Wall(Game game, int x, int y){
		super(game, x, y, 3);
		symbol = "█";
	}
	
	@Override
	protected String getSymbol() {
		return A[vidas-1];
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		game.incrementNumCoins(5); 
	}
}
