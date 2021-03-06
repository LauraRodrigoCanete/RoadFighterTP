package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public abstract class GameObject implements Collider {

	protected int x, y;

	protected Game game;

	protected String symbol;

	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	protected String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}

		return "";
	}
	
	public String SerializeToString() {
		return getSymbol() + " (" + x + ", " + y + ")";	
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return true;
	}

	public abstract void onEnter();

	public abstract void update();

	public abstract void onDelete();

	@Override
	public boolean receiveCollision(Player player) { return false;}
	
	@Override
	public boolean doCollision() { return false; }
	
	@Override
	public boolean receiveShoot() { return false; }
	
	@Override
	public boolean receiveThunder() { return false; }
	
	@Override
	public boolean receiveExplosion() { 
		return receiveThunder(); 
	}
	
	@Override
    public boolean receiveWave() {
    	x++;
    	return true;
    }
}
