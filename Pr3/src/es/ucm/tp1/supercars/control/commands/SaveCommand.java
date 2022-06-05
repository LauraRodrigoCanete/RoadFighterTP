package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GameSerializer;

public class SaveCommand extends Command{
	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save the state of the game to a file.";
	
	private static final String FAILED_MSG = "Failed to save";
	
	private static final String SUCCESS = "Game successfully saved to file ";
	
	private static final String VERSION = "3.0";
	
	private static final String WELCOME_MSG = String.format("Super cars %s%n%n", VERSION);
	
	private String filename;

	public SaveCommand() {
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
		try(BufferedWriter output = new BufferedWriter(new FileWriter(filename + ".txt"))) {
			GameSerializer printer = new GameSerializer(game);
			output.write(WELCOME_MSG + printer.toString());
			System.out.println(SUCCESS + filename + ".txt");
		}
		catch(IOException e) {
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e);
		}
		return false;
	}
}
