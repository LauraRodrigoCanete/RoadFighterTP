package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Player {

	private static final String COINS_MSG = "Coins: ";
	
	private static final String DISTANCE_MSG = "Distance: ";
	
	private int x;
	private int y;
	
	private Game game;
	private static int numCoins;
	private int velocidad = 1; 
	private int resistencia = 1;
	private boolean muerto = false; 
	
	public boolean isAlive() {
		return !muerto;
	}
	
	public void setMuerto() {
		this.muerto = true;
	}
	
	public Player(Game game) {
		this.game = game;
		this.x = 0;
		this.y = (game.getWidth()-1)/2; //el jugador empieza en la fila del medio
		numCoins = 5;
	}
	
	public void setNumCoins() {
		this.numCoins++;
	}
	
	
	public void goUp() {
		if(y > 0)//si ya está en la fila 0 no dejamos que siga subiendo
			this.y--;
		
	}
	
	public void goDown() {
		if(y < game.getWidth()-1)
			this.y++;
	}
	
	public boolean isInPosition(int i, int j) {
		return x == i && y == j; 
	}
	
	public int returnPositionPlayerY() {
		return this.y;
	}
	
	public int returnPositionPlayerX() {
		return this.x;
	}
	
	public void update() {
		x++;//avanzar una columna
		doCollision();
	}
	
	public void doCollision() {
		if(!game.isPosEmptyObstacle(x, y)) {
			game.getObstacleinPos(x, y).receiveCollision();
		}
		else if(!game.isPosEmptyCoin(x, y))
			game.getCoininPos(x, y).receiveCollision();
	}

	
	public int distanceToGoal() {
		return game.getLength() - this.x;
	}
	
	public String Info() {
		return String.format("%s%s%n%s%s", DISTANCE_MSG, distanceToGoal(), COINS_MSG, numCoins);
	}
	
	public String toString() {
		if(muerto)
			return "@";
		else
			return ">";
		
	}

}
