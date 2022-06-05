package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject{
	
	public static final String INFO = "[Coin] gives 1 coin to the player";
	
	private static int numCoins;
	private boolean isAlive; //true si is alive
	

	public Coin(Game game, int x, int y){
		super(game, x, y);
		symbol = "¢"; 
		isAlive = true; 
	}
	
	public void onEnter() {
		numCoins++;
	}
	
	public void update() {
		
	}

	public void onDelete() {
		numCoins--;
	}
	
	public static void reset(){
		numCoins = 0;
	}
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		isAlive = false;
		player.incrementNumCoins(1);
		return true;
	}
	
	public static int getNumCoins() {
		return numCoins;
	}
}
