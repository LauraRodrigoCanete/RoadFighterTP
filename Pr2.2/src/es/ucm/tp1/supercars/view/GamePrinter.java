package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
/*import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
*/
import es.ucm.tp1.supercars.utils.StringUtils;

public class GamePrinter {

	private static final String SPACE = " ";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game";

	private static final String GAME_OVER_MSG = "[GAME OVER] ";

	private static final String SUPERCOIN_PRESENT = "Supercoin is present";

	private static final String DISTANCE_MSG = "Distance: ";

	private static final String COINS_MSG = "Coins: ";

	private static final String CYCLE_MSG = "Cycle: ";

	private static final String TOTAL_OBSTACLES_MSG = "Total obstacles: ";

	private static final String TOTAL_COINS_MSG = "Total coins: ";

	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";

	private static final String NEW_RECORD_MSG = "New record!: ";

	private static final String RECORD_MSG = "Record: ";
	

	private static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}

	private Game game;
	
	private String indentedRoadBorder;

	private String indentedLanesSeparator;

	private String margin;
	
	private int visibility;

	public GamePrinter(Game game) {
		this.game = game;
		initialize();
		visibility = game.getVisibility();
	}
	
	public void initialize() {
		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN
				+ StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, game.getVisibility() - 1)
				+ laneDelimiter + SPACE;

		indentedLanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
	}

	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		/* @formatter:off */
		buffer
		.append(DISTANCE_MSG).append(game.distanceToGoal()).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_OBSTACLES_MSG).append(Obstacle.getNumObstacles()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_COINS_MSG).append(Coin.getNumCoins());
		/* @formatter:on */
		if (game.getLevel().hasAdvancedObjects()) {
			if (SuperCoin.hasSuperCoin()) {
				buffer.append(StringUtils.LINE_SEPARATOR).append(SUPERCOIN_PRESENT);
			}
		}

		if (!game.isTestMode()) {
			/* @formatter:off */
			buffer
			.append(StringUtils.LINE_SEPARATOR)
			.append(ELAPSED_TIME_MSG).append(formatTime(game.elapsedTime()));
			/* @formatter:on */
		}

		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());

		// Paint game
		if (visibility != game.getVisibility()) {
			initialize();
			visibility = game.getVisibility();
		}
		
		str.append(indentedRoadBorder);
		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = game.posJugadorX(); x < game.getVisibility() + game.posJugadorX(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE)).append(verticalDelimiter);
			}
			if (y < game.getWidth() - 1) {
				str.append(this.indentedLanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);
		
		return str.toString();
	}

	public String endMessage() {

		StringBuilder buffer = new StringBuilder(GAME_OVER_MSG);

		if (game.isUserExit()) {
			return buffer.append(DO_EXIT_MSG).toString();
		}

		if (game.hasArrived()) {
			buffer.append(WIN_MSG);
			if (!game.isTestMode()) {
				if (game.isNewRecord()) {
					buffer.append(NEW_RECORD_MSG).append(formatTime(game.elapsedTime()));
				} else {
					buffer.append(RECORD_MSG).append(formatTime(game.getRecord()));
				}
			}
		} else {
			buffer.append(CRASH_MSG);
		}

		return buffer.toString();
	}
	

public static String description(Level level) {
		StringBuilder buffer = new StringBuilder("Available objects:");
		/* @formatter:off */
		buffer
		.append(StringUtils.LINE_SEPARATOR).append("[Car] the racing car")
		.append(StringUtils.LINE_SEPARATOR).append(Coin.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Obstacle.INFO);
		
		/* @formatter:on */
		
	
		/* @formatter:off */
		buffer
		.append(StringUtils.LINE_SEPARATOR).append(Grenade.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Wall.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Turbo.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(SuperCoin.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Truck.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Pedestrian.INFO);
		/* @formatter:on */
		
		
		return buffer.toString();
	}
}
