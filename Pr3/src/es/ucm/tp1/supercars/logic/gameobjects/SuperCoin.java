package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject{
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";
	
	private static boolean isPresent;
	private boolean isAlive;
	
	public SuperCoin(Game game, int x, int y){
		super(game, x, y);
		symbol = "$"; 
		isPresent = false; 
		isAlive = true;
	}
	
	public void onEnter() {
		isPresent = true;
	}
	
	public void update() {
			
	}

	public void onDelete() {
		isPresent = false; 
	}
	
	public static void reset(){
		isPresent = false; 
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		isAlive = false;
		isPresent = false;
		player.incrementNumCoins(1000);
		return true;
	}
	
	public static boolean hasSuperCoin() {
		return isPresent;
	}
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}
}
