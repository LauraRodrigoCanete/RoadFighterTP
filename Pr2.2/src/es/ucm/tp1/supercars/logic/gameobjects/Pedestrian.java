package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle{
	
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private boolean bajando;
	
	public Pedestrian(Game game, int x, int y){
		super(game, x, y, 1);
		symbol = "☺";
		bajando = true;
	}
	
	public void update() {
		if(y > 0 && bajando) 
			y--;
		else if (y == 0) {
			bajando = false;
			y++;
		}
		else if(y< game.getWidth()-1 && !bajando)
			y++;
		else /*if (y == game.getWidth()-1)*/ {
			bajando = true;
			y--;
		}
		
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		game.resetNumCoins();
		this.vidas = 0;
		player.muerto();//cuando el jugador se choca muere
		return true;
	}
	
	@Override
	 public boolean receiveShoot() { 
		game.resetNumCoins();
		vidas--;
		return true;
	}
}
