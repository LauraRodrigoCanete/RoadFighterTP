package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.View;

public class ShowRecordCommand extends Command{
	private static final String NAME = "record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	private static final String HELP = "show level record";

	
	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game){
		System.out.println(game.getLevel() + " record is " + View.formatTime(game.getRecord()));
		return false;
	}
}
