package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command{
	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";
	
	private static final String FAILED_MSG = "An error ocurred on reading a file";
	
	private String filename;

	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 2) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME , INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			filename = words[1];
			return this;
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try(BufferedReader input = new BufferedReader(new FileReader(filename + ".txt"))) {
			String l;
			while((l = input.readLine()) != null ){ 
				System.out.println(l);
			}
		}
		catch(IOException e) {
			throw new CommandExecuteException(FAILED_MSG, e);
		}
		return false;
	}
}

