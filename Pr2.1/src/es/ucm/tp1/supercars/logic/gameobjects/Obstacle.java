package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject{
	
	public static final String INFO = "[Obstacle] hits car";
	
	private static int numObstacles;
	private boolean isAlive;
	
	public Obstacle(Game game, int x, int y){
		super(game, x, y);
		symbol = "░";
		isAlive = true;
	}
	
	public void onEnter() {
		numObstacles++;
	}
	
	public void update() {
		
	}

	public void onDelete() {
		numObstacles--;
	}
	
	public static void reset(){
		numObstacles = 0;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean receiveCollision(Player player) {
		isAlive = false;
		player.muerto();
		return true;
	}
	
	public static int getNumObstacles() {
		return numObstacles;
	}
}
