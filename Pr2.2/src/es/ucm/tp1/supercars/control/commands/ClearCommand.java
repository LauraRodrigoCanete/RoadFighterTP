package es.ucm.tp1.supercars.control.commands;

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
	protected Command parse(String[] words) {
		if (SHORTCUT.equals(words[0])) { //he quitado el ignore case pq no hay un 0 mayúscula
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		game.clear();
		return true; //pinta otra vez el tablero que lo miré en los test
	}

}
