package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ThunderAction implements InstantAction{
	public void execute(Game game) {
		int x = game.getRandomVisibleColumn() + game.posJugadorX(); //para imprimirlo sobra lo de sumarle la pos del jugador
		int y = game.getRandomLane();
		System.out.print("Thunder hit position: (" + (x - game.posJugadorX()) + " , " + y + ")");
		if(!game.isPosEmpty(x, y)) {
			String s = " -> " + game.positionToString(x, y);
			if(game.getObjectInPosition(x, y).receiveThunder()) {
				System.out.print(s);
			}
		}
		System.out.print(String.format("%n"));
	}
}

