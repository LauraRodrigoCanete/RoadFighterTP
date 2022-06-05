package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	
	public int cost();
	
	public default boolean buy(Game game){
		if (game.playerCoins() >= cost()) {
			game.incrementNumCoins(-cost());
			return true;
		}
		else {
			System.out.println("Not enough coins");
			return false;
		}
	};
}
