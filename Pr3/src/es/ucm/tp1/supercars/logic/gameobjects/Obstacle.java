package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject{
	
	public static final String INFO = "[Obstacle] hits car";
	
	private static int numObstacles;
	protected int vidas;
	
	public Obstacle(Game game, int x, int y){
		
		this(game, x, y, 1);
		symbol = "░";
	}
	
	protected Obstacle(Game game, int x, int y, int vidas) {
		super(game, x, y);
		this.vidas = vidas;
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
		return vidas != 0;//todavía no ha muerto
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.muerto();//cuando el jugador se choca muere
		return true;
	}
	
	public static int getNumObstacles() {
		return numObstacles;
	}
	
	@Override
	 public boolean receiveShoot() { 
		vidas--;
		return true;
	}
	
	@Override
	 public boolean receiveThunder() { 
		vidas = 0;
		return true;
	}
}
