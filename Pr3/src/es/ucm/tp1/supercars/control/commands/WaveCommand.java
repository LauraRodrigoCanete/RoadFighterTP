package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.actions.WaveAction;

public class WaveCommand extends Command implements Buyable{

	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "do wave";
	
	private static final String FAILED_MSG = "Failed to do wave";
	

	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			buy(game);
			InstantAction action = new WaveAction();
			game.execute(action);
			game.update();
			return true; //como tenemos un try en controller y la variable a la que se iguala la función está inicializada a false, si se lanza la excepción, nos vamos al catch de controller y nunca se pone a true, luego de lo único que no stenemos que preocupar es de que si no se lanza la excepcion pues que se ponga a true 
		}
		catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e); 
		}
	}

	@Override
	public int cost() {
		return 5;
	}
}
