package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject{
	public static final String INFO = "[TURBO] pushes the car: 3 columns";
	private boolean isAlive;
	
	public Turbo(Game game, int x, int y){
		super(game, x, y);
		symbol = ">>>"; 
		isAlive = true;
	}
	
	public void onEnter() {}

	public void update() {}

	public void onDelete() {}
	
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public boolean receiveCollision(Player player) {
		isAlive = false;
		player.incrementPositionBy3();
		return true;
	}
	
}
