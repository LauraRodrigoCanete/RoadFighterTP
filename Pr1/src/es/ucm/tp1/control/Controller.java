package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private static final String DEBUG_MSG = "[DEBUG] Executing: ";
	
	private static final String ERROR_MSG = "[ERROR]: ";

	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobject info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	/* @formatter:off */
	
	private static final String[] INFO = new String[] {
			"Available objects:",
			"[Car] the racing car",
			"[Coin] gives 1 coin to the player",
			"[Obstacle] hits car",
	};

	private Game game;

	private Scanner scanner;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	public void printGame() {
		System.out.println(game);
	}

	public boolean esAlgunComando(String command){
		return ("TEST".equalsIgnoreCase(command) || "T".equalsIgnoreCase(command)||
				"EXIT".equalsIgnoreCase(command) || "E".equalsIgnoreCase(command)||
				"RESET".equalsIgnoreCase(command) || "R".equalsIgnoreCase(command)||
				"Q".equalsIgnoreCase(command) || "A".equalsIgnoreCase(command) || 
				"".equals(command) || "n".equalsIgnoreCase(command));
	}
	
	public String printSequence() {
		System.out.println(PROMPT);
		String command = scanner.nextLine();
		System.out.println(DEBUG_MSG + command);
		return command;
	}
	
	public void run() {
		boolean exit = false;
		System.out.println(game.toString());
		while(game.isPlayerAlive() && !game.enMeta() && !exit){					
			String originalCommand = printSequence();
			String command = originalCommand.toLowerCase();
			
			while(!esAlgunComando(command)) {
				if("HELP".equalsIgnoreCase(command) || "H".equalsIgnoreCase(command)){
					for (String s : HELP) System.out.println(s);
				}
				else if("INFO".equalsIgnoreCase(command) || "I".equalsIgnoreCase(command)) {
					for (String k : INFO) System.out.println(k);
				}
				else //si es error
					System.out.println(String.format("%s%s%n", ERROR_MSG, UNKNOWN_COMMAND_MSG));
				command = printSequence();
			}
			

			if("TEST".equalsIgnoreCase(command) || "T".equalsIgnoreCase(command)) {
				game.setModoTest();	
			}
			else if("EXIT".equalsIgnoreCase(command) || "E".equalsIgnoreCase(command)) {
				exit = true;
			}
			else if("RESET".equalsIgnoreCase(command) || "R".equalsIgnoreCase(command)) {
				game.reset();
			}
			else if("Q".equalsIgnoreCase(command)) {//go up
				game.goUp();
			}
			else if("A".equalsIgnoreCase(command)) {//go down
				game.goDown();
			}
			else if ("".equals(command) || "n".equalsIgnoreCase(command)) {
				game.update();
			}

			if(!"EXIT".equalsIgnoreCase(command) && !"E".equalsIgnoreCase(command))
				System.out.println(game.toString());
		}
		System.out.println(game.getGamePrinter().endMessage());
	}

}








