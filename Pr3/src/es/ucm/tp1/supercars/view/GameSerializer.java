package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;

public class GameSerializer extends View{
	
	
	private static final String START = "---- ROAD FIGHTER SERIALIZED ----";
	
	private static final String COINS_MSG = "Coins: ";

	private static final String CYCLE_MSG = "Cycles: ";
	
	private static final String LEVEL_MSG = "Level: ";
	
	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";
	
	private static final String OBJECTS_MSG = "Game Objects: ";
	
	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	@Override
	protected String getInfo() {
		StringBuilder buffer = new StringBuilder(START).append(StringUtils.LINE_SEPARATOR);
		/* @formatter:off */
		buffer
		.append(LEVEL_MSG).append(game.getLevel()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR);
		/* @formatter:on */

		if (!game.isTestMode()) {
			/* @formatter:off */
			buffer
			.append(ELAPSED_TIME_MSG).append(game.elapsedTime()).append(StringUtils.LINE_SEPARATOR);
			/* @formatter:on */
		}

		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(getInfo()).append(OBJECTS_MSG).append(StringUtils.LINE_SEPARATOR);
		
		for (int x = 0; x < game.getRoadLength(); x++) {
			for (int y = 0; y < game.getWidth(); y++) {
				str.append(game.serializePositionToString(x, y));
				if(game.posJugadorX() == x && game.posJugadorY() == y)
					str.append(game.serializePlayerToString()).append(StringUtils.LINE_SEPARATOR);
			}
			
		}
		
		return str.toString();
	}
}
