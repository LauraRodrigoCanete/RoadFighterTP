package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject{
	
	private int numCoins;
	/*private int velocidad; 
	private int resistencia;*/
	private boolean isAlive;
	
	public Player(Game game) {
		super(game, 0, (game.getWidth()-1)/2); //el jugador empieza en la fila del medio
		numCoins = 5;
		isAlive = true;
		/*velocidad = 1;
		resistencia = 1;*/
		symbol = ">";
		this.x = 0;
		this.y = (game.getWidth() -1)/2;
	}
	
	
	@Override
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x,y);
		if(other != null) {
			return other.receiveCollision(this);
		}
		return false;
	}
	

	public void incrementPositionBy3() {
		this.x += 3;
	}
	
	public void incrementNumCoins(int coins){
		numCoins += coins;
	}
	
	public void muerto() {
		this.isAlive = false;
	}
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}
	
	@Override
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}

		return "@";
	}
	

	public int distanceToGoal() {
		return game.getRoadLength() - this.x;
	}
	
	public int getPlayerCoins() {
		return numCoins;
	}
	
	@Override
	public void onEnter() {}

	@Override
	public void onDelete() {}
	
	@Override
	public void update() {}
	
	public void goUp() {
		doCollision();
		if (isAlive) {
			x++;//avanzar una columna
		}
		if(y > 0)//si ya está en la fila 0 no dejamos que siga subiendo
			this.y--;
		doCollision();
	}
	
	public void goDown() {
		doCollision();
		if (isAlive) {
			x++;//avanzar una columna
		}
		if(y < game.getWidth()-1)
			this.y++;
		doCollision();
	}
	
	public void goStraight() {
		doCollision();
		if (isAlive) {
			x++;//avanzar una columna
		}
		doCollision();
	}
	
	public void resetNumCoins() {
		this.numCoins = 0;
	}
}
