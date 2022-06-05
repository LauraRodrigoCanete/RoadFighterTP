package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject; //se neecsita par la función que nos dio hecha él
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;

public class CheatCommand extends Command {

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";

	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	private int id;
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		switch (id) {
		case 1:
			o = new Wall(game, x, game.getRandomLane());
			break;
		case 2:
			o = new Turbo(game, x, game.getRandomLane());
			break;
		case 3:
			o = new SuperCoin(game, x, game.getRandomLane());
			break;
		case 4:
			o = new Truck(game, x, game.getRandomLane());
			break;
		case 5:
			o = new Pedestrian(game, x, 0);
			break;
		}
		game.forceAddObject(o);
	}
	
	@Override 
	protected Command parse(String[] words) {
		try {
			if (1 <= Integer.valueOf(words[0]) && Integer.valueOf(words[0]) <= 5 ) { //he quitado el ignore case pq no hay un 0 mayúscula
				if (words.length != 1) {
					System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
					return null;
				} else {
					id = Integer.valueOf(words[0]);
					return this;
				}
			}
			return null;
		}
		catch(NumberFormatException nfe) {
			return null;
		}
	}
	
	@Override
	public boolean execute(Game game) {
		game.eliminarColumna(game.posJugadorX() + game.getVisibility() - 1);
		forceAdvanceObject(game, id, (game.posJugadorX() + game.getVisibility() - 1)); //creo que está bien pero not sure
		return true; //pinta otra vez el tablero que lo miré en los test
	}

}
