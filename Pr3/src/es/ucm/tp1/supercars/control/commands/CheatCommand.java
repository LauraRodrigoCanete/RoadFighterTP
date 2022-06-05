package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class CheatCommand extends Command {

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";

	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	private int id;
	
	@Override 
	protected Command parse(String[] words) throws CommandParseException {
		try {
			if (1 <= Integer.valueOf(words[0]) && Integer.valueOf(words[0]) <= 5 ) { //he quitado el ignore case pq no hay un 0 mayúscula
				if (words.length != 1) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME , INCORRECT_NUMBER_OF_ARGS_MSG));
				} 
				id = Integer.valueOf(words[0]);
				return this;
			}
			return null;
		}
		catch(NumberFormatException nfe) {
			return null;
		}
	}
	
	@Override
	public boolean execute(Game game) {
		game.eliminarColumna(game.posJugadorX() + game.getVisibility() - 1);
		GameObjectGenerator.forceAdvanceObject(game, id, (game.posJugadorX() + game.getVisibility() - 1));
		return true;
	}

}
