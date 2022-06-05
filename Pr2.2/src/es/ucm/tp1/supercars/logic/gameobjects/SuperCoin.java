package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject{
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";
	
	private static boolean messagePresent;
	private boolean isAlive;
	
	public SuperCoin(Game game, int x, int y){
		super(game, x, y);
		symbol = "$"; 
		messagePresent = false; 
		isAlive = true;
	}
	
	public void onEnter() {
		messagePresent = true;
	}
	
	public void update() {
			
	}

	public void onDelete() {
		messagePresent = false; 
	}
	
	public static void reset(){
		messagePresent = false; 
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		isAlive = false;
		messagePresent = false;
		player.incrementNumCoins(1000);
		return true;
	}
	
	public static boolean hasSuperCoin() {
		return messagePresent;
	}
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}
}
