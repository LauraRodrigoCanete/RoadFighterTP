package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable{

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String COOR_IS_NUMBER_MSNG = "the coordenates must be integers";
	
	private int x;//relativa al jugador
	private int y;//absoluta
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	protected Command parse(String[] words) {
		//simplificar tantos returns. pensar: "una variable para gestionar el valor de retorno es más fácil y se simplifica el código."
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} 
			else{
				try {
					x = Integer.valueOf(words[1]);
					y = Integer.valueOf(words[2]);
				} catch (NumberFormatException nfe) {
					System.out.println(COOR_IS_NUMBER_MSNG);
					return null;
				}
				return this;
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		if(game.isInVisibleBoard(x + game.posJugadorX(), y, game.getVisibility()) && game.isPosEmpty(x + game.posJugadorX(), y)) {
			if(buy(game)) {
				Grenade grenade = new Grenade(game, x+game.posJugadorX(), y);
				game.forceAddObject(grenade);
				game.update();
				return true;
			}
			else {
				System.out.println("[ERROR]: Failed to add grenade");
				System.out.println("");
			}
		}
		else {
			System.out.println("Invalid position.");
			System.out.println("[ERROR]: Failed to add grenade");
			System.out.println("");
		}
		return false;
	}
	
	@Override
	public int cost() {
		return 3;
	}
	
}
