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
		if(bajando) {
			if(y == game.getWidth() -1){
				bajando = false;
				y--;
			}
			else y++;
		}
		else {
			if(y==0) {
				bajando = true;
				y++;
			}
			else y--;
		}
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		super.receiveCollision(player);
		game.resetNumCoins();
		this.vidas = 0;
		return true;
	}
	
	@Override
	 public boolean receiveShoot() { 
		game.resetNumCoins();
		vidas--;
		return true;
	}
	
	public String direccionToString() {
		if (bajando) return "down";
		else return "up";
	}
	
	
	@Override
	public String SerializeToString() {
		return getSymbol() + " (" + x + ", " + y + ") " + direccionToString();	
	}
	
}
