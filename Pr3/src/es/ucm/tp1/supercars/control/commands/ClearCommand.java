package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class ClearCommand extends Command {

	private static final String NAME = "clear";

	private static final String DETAILS = "Clear [0]";

	private static final String SHORTCUT = "0";

	private static final String HELP = "Clears the road";

	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override //lo sobreescribo porque sino al escribir el NAME (clear), funcionaría, y en el pdf pone que solo funciona con el 0
	protected Command parse(String[] words) throws CommandParseException {
		if (SHORTCUT.equals(words[0])) { //he quitado el ignore case pq no hay un 0 mayúscula
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME , INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			return this;
			
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		game.clear();
		return true;
	}

}
