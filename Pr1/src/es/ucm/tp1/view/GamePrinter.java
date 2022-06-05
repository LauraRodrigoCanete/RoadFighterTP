package es.ucm.tp1.view;

import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.Game;



public class GamePrinter {


	private static final String SPACE = " ";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";
	
	private static final String META = "¦";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;
	
	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game";

	private static final String GAME_OVER_MSG = "[GAME OVER] ";

	private static final String NEW_RECORD_MSG = "New record!: ";

	private static final String RECORD_MSG = "Record: ";

	public static final String NEW_LINE = System.lineSeparator();
	
	public String endMessage() {

		String s = GAME_OVER_MSG;
		
		if(game.enMeta()) {
			s += WIN_MSG;
			if(!game.getModoTest())
				s += NEW_RECORD_MSG + game.ellapsedTime();
		}
		else if(!game.isPlayerAlive())
			s += CRASH_MSG;
		else
			s += DO_EXIT_MSG;
		
		return s;
	}
	

	private Game game;

	private int numRows;

	private int numCols;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;

	private String[][] board;
	
	public GamePrinter(Game game, int visibility, int width) {
		this.game = game;
		this.numRows = width;
		this.numCols = visibility;

		this.margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * numCols);
		this.indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, numCols - 1) + laneDelimiter + SPACE;

		this.indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);

	}
	
	private void encodeGame(Game game) {	
		board = new String[numCols][numRows];
		for(int j = 0; j< numRows; j++) {
			for(int i = game.getPlayerPosX(); i < game.getPlayerPosX() + numCols; i++) {
				if(game.isPosEmptyCoin(i,j) && game.isPosEmptyObstacle(i,j)) {
					board[i-game.getPlayerPosX()][j] = " "; //hay que restar game.getPlayerPosX() para que la referencia sea siempre el jugador
				}
				else if(!game.isPosEmptyObstacle(i,j)) {
					board[i-game.getPlayerPosX()][j] = game.getObstacleinPos(i,j).toString();
				}
				else {
					board[i-game.getPlayerPosX()][j] = game.getCoininPos(i,j).toString();
				}	
			}
		} 
		if (game.getPlayer().distanceToGoal() >= 0 && game.getPlayer().distanceToGoal() < numCols) {
			for (int i=0;i<numRows;i++) board[game.getPlayer().distanceToGoal()][i] = META;
		}
		board[0][game.getPlayerPosY()] = game.getPlayer().toString(); //jugador siempre el x=0
	}

	@Override
	public String toString() {
		encodeGame(game);
		
		StringBuilder str = new StringBuilder();

		// Game Status
		str.append(game.getGameStatus());

		// Paint game
		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < numRows; y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < numCols; x++) {
				str.append(StringUtils.centre(board[x][y], CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y < numRows - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);
		return str.toString();
	}

	
}
















