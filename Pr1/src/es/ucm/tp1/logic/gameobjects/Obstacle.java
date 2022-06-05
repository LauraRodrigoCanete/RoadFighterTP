package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Obstacle {
	private int x;
	private int y;
	private Game game;
	private static int resistencia = 1;
	private static int numObstacles;
	private boolean chocado;
	
	public Obstacle(Game game, int n, int m) {
		chocado = false;
		this.game = game;
		this.x = n;
		this.y = m;
	}
	
	public int getPosx() {
		return x;
	}
	
	public int getPosy() {
		return y;
	}
	
	public int getNumObstacles () {
		return numObstacles;
	}
	
	public void setNumObstacles(int contador) {
		numObstacles = contador;
	}
	
	public String toString() {
		if(chocado)
			return " ";
		else
			return "░";
	}
	
	public boolean isInPosition(int i, int j) {
		return x == i && y == j; 
	}
	
	public void receiveCollision() {
		chocado = true;
		game.getPlayer().setMuerto();
	}
}
