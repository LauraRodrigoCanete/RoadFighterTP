package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable{

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String COOR_IS_NUMBER_MSNG = "the coordenates must be integers";
	
	private static final String INCORRECT_NUMBER_OF_ARGS_MSNG = "Incorrect number of arguments for grenade command";
	
	private static final String FAILED_MSG = "Failed to add grenade";
	
	private int x;//relativa al jugador
	private int y;//absoluta
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: %s: %s",INCORRECT_NUMBER_OF_ARGS_MSNG, DETAILS));
			} 				
			try {
				x = Integer.valueOf(words[1]);
				y = Integer.valueOf(words[2]);
			} catch (NumberFormatException nfe) {
				throw new CommandParseException(COOR_IS_NUMBER_MSNG, nfe);
			}
			return this;
			
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try{
			if(!(game.isInVisibleBoard(x + game.posJugadorX(), y, game.getVisibility()) && game.isPosEmpty(x + game.posJugadorX(), y))) 
				throw new InvalidPositionException("Invalid position.");
			buy(game);
			Grenade grenade = new Grenade(game, x+game.posJugadorX(), y);
			game.forceAddObject(grenade);
			game.update();
			return true;
		}
		catch (CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException("[ERROR]: " + FAILED_MSG, e);
		}
	}
	
	@Override
	public int cost() {
		return 3;
	}
	
}
