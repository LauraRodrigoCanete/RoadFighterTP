package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;

public class ResetCommand extends Command {

	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	
	private static final String LEVEL_INFO_MSG = "Level must be one of: " + Level.all(", ");
	private static final String SEED_IS_NUMBER_MSNG = "the seed is not a proper long number";
	private static final String SEED_RESET_INFO_MSG = "Random generator initialized with seed: ";
	private static final String LEVEL_RESET_INFO_MSG = "Level: ";
	private Long seed;
	private Level level;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
		if(seed == null && level == null)
			game.reset();
		else {
			System.out.println(String.format("%s%s%n%s%s", LEVEL_RESET_INFO_MSG, level.name(), SEED_RESET_INFO_MSG, seed ));
			game.reset(seed, level);
		}
		return true;//se pinta el próximo tablero
		}
		catch(InputOutputRecordException e) {
			throw new CommandExecuteException(e);
		}
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		seed = null; level = null;
		if (matchCommandName(words[0])) {
			if (words.length != 1 && words.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", words[0] , INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			else if(words.length == 3){
				level = Level.valueOfIgnoreCase(words[1]);
				if (level == null) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s", words[0] , LEVEL_INFO_MSG));
				}
				try {
					this.seed = Long.parseLong(words[2]);
				}
				catch (NumberFormatException nfe) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s", words[0] , SEED_IS_NUMBER_MSNG), nfe);
				}
			}
			return this;
		}
		return null;
	}
}
