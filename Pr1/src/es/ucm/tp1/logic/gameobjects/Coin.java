package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Coin {
	private int x;
	private int y; 
	private Game game;
	private static int numCoins;
	private boolean cogida;//true si esta cogida, muerta
	
	public Coin(Game game, int n, int m) {
		this.x = n;
		this.y = m;
		cogida = false;
		this.game = game;
	}
	
	public int getPosx() {
		return x;
	}
	
	public int getPosy() {
		return y;
	}
	
	public int getNumCoins () {
		return numCoins;
	}
	
	public void set1menosNumCoins() {
		numCoins--;
	}
	
	public void setNumCoins(int contador) {
		numCoins = contador;
	}
	
	public boolean isInPosition(int i, int j) {
		return x == i && y == j; 
	}
	
	public String toString() {
		if(cogida)
			return " ";
		else
			return "¢";
	}
	
	public void receiveCollision() {
		cogida = true;
		game.getPlayer().setNumCoins();
	}
	
	public boolean getEstado() {
		return cogida;
	}
	

}
