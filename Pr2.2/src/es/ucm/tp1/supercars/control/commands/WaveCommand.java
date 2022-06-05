package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.actions.ShootAction;
import es.ucm.tp1.supercars.logic.actions.WaveAction;

public class WaveCommand extends Command implements Buyable{

	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "do wave";

	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(buy(game)) {
			InstantAction action = new WaveAction();
			game.execute(action);
			game.update();
			return true;
		}
		else {
			System.out.println("[ERROR]: Failed to do wave");
			System.out.println("");
		}
		return false;
	}

	@Override
	public int cost() {
		return 5;
	}
}
