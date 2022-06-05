package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ShootAction implements InstantAction{
	public void execute(Game game) {
		boolean b = false;//hemos chocado con algo
		int x = game.posJugadorX();
		while (!b && x < game.posJugadorX() + game.getVisibility()) {//no ponemos -1 pq es menor estricto
			if(!game.isPosEmpty(x, game.posJugadorY())) {
				b = game.getObjectInPosition(x, game.posJugadorY()).receiveShoot();
			}
			x++;
		}
	}
}
