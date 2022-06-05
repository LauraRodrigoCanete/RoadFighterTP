package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;

public class ResetCommand extends Command {

	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	
	private static final String LEVEL_INFO_MSG = "Level must be one of: " + Level.all(", ");
	private static final String SEED_IS_NUMBER_MSNG = "the seed must be a number";
	private static final String SEED_RESET_INFO_MSG = "Random generator initialized with seed: ";
	private static final String LEVEL_RESET_INFO_MSG = "Level: ";
	private long seed;
	private Level level;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(seed == -1)
			game.reset();
		else {
			System.out.println(String.format("%s%s%n%s%s", LEVEL_RESET_INFO_MSG, level.name(), SEED_RESET_INFO_MSG, seed ));
			game.reset(seed, level);
		}
		return true;//se pinta el próximo tablero
	}
	
	@Override
	protected Command parse(String[] words) {
		//simplificar tantos returns. pensar: "una variable para gestionar el valor de retorno es más fácil y se simplifica el código."
		if (matchCommandName(words[0])) {
			if (words.length != 1 && words.length != 3) {
				System.out.format("[ERROR]: Command %s: %s%n%n", words[0], INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else if(words.length == 3){
				level = Level.valueOfIgnoreCase(words[1]);
				if (level == null) {
					System.out.format("[ERROR]: Command %s: %s%n%n", words[0], LEVEL_INFO_MSG);
					return null;
				}
				try {
					seed = Long.parseLong(words[2]);
				} catch (NumberFormatException nfe) {
					System.out.format("[ERROR]: Command %s: %s%n%n", words[0], SEED_IS_NUMBER_MSNG);
					return null;
				}
				return this;
			}
			else {
				seed = -1;
				return this;
			}
		}
		return null;
	}
	
}
