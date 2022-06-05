package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;

public class HelpCommand extends Command {

	private static final String NAME = "help";

	private static final String DETAILS = "[h]elp";

	private static final String SHORTCUT = "h";

	private static final String HELP = "show this help";

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); //llamar al super tiene q ser la primera linea del constructor
	}

	@Override
	public boolean execute(Game game) {
		StringBuilder buffer = new StringBuilder("Available commands:");
		for(Command c : AVAILABLE_COMMANDS) {
			buffer
				.append(StringUtils.LINE_SEPARATOR)
				.append(c.infoForHelp());
		}

		System.out.println(buffer.toString());

		return false;
	}

}
