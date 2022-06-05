package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ExplosionAction implements InstantAction{
	private int x;//posiciones absolutas
	private int y;
	
	public ExplosionAction(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void execute(Game game) {
		
		for (int dx = -1; dx <= 1; ++dx) {
		    for (int dy = -1; dy <= 1; ++dy) {
		    	if(game.isInBoard(x + dx, y + dy, game.getVisibility() + 1)  &&  !game.isPosEmpty(dx + x, dy + y)) {
		    		game.getObjectInPosition(dx + x, dy + y).receiveExplosion();
		    	}
		    }
		}
		
	}
}
