package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";

	/* @formatter:off */
	protected static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new MoveUpCommand(),
		new MoveDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new DumpCommand(),
		new ShowRecordCommand(),
		new ClearCommand(),
		new CheatCommand()		
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords) throws CommandParseException{
		Command d;
		for(Command c : AVAILABLE_COMMANDS) {
			d = c.parse(commandWords);
			if(d != null) 
				return d;
		}
		throw new CommandParseException(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG));
	}

	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", name , INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			return this;
			
		}
		return null;
	}

	
	public String infoForHelp() {
		return details + ": " + help;
	}
	

}
