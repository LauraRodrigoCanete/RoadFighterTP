package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.actions.ShootAction;
import es.ucm.tp1.supercars.utils.StringUtils;

public class ShootCommand extends Command implements Buyable{

	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); //llamar al super tiene q ser la primera linea del constructor
	}

	@Override
	public boolean execute(Game game) {
		if(buy(game)) {
			InstantAction action = new ShootAction();
			game.execute(action);
			game.update();//los otros objetos del juego son actualizados, los ciclos avanzan y se eliminan objetos
			return true;
		}
		else {
			System.out.println("[ERROR]: Failed to shoot");
			System.out.println("");
			return false;
		}
	}
	
	@Override
	public int cost() {
		return 1; //cuesta 1 coin
	}	
}