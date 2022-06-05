package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;
import es.ucm.tp1.supercars.logic.actions.InstantAction;

public class Grenade extends GameObject{
	
	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	
	private int contador;//de 3 a 0 (en el 0 no se muestra, ya explota)
	
	public Grenade(Game game, int x, int y){
		super(game, x, y);
		symbol = "ð"; 
		contador = 4; //lo inicializamos a 4 porque antes de que se imprima el update le resta ya uno
	}
	
	public void onEnter() {}

	public void update() {
		contador--;
	}

	public void onDelete() {
		InstantAction action = new ExplosionAction(x, y);
		game.execute(action);
	}
	
	@Override
	public boolean isAlive() {
		return contador != 0;
	}
	
	@Override
	public String toString() {
		if (contador > 0) {
			return getSymbol() + "[" + contador + "]";
		}

		return "";
	}
}
