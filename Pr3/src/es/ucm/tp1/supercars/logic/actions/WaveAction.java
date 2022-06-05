package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class WaveAction implements InstantAction{
	public void execute(Game game) {
		int x = game.getVisibility() + game.posJugadorX();
		for (int y = 0; y < game.getWidth(); y++) {
			for (int j = x-1; j >= game.posJugadorX(); j--) {
				if(j+1 <= game.getRoadLength() && game.isPosEmpty(j+1, y) && !game.isPosEmpty(j, y)) {
					game.getObjectInPosition(j, y).receiveWave();
				}
			}
		}
	};
}

