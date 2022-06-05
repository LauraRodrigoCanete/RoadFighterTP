package es.ucm.tp1.supercars.logic;

import java.util.Random;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.Player;

public class Game {

	private static final String META = "¦";
	private GameObjectContainer container;
	private Level level;
	private Player jugador;
	private int cycle;
	private boolean modoTest;
	private long startTime;
	private long seed;
	private Random rand;
	private boolean exit;
	private long time;
	private Record record;
	
	public Game(long seed,Level level,boolean isTestMode) throws InputOutputRecordException{
		modoTest = isTestMode;
		exit = false;
		reset(seed, level);
	}
	
	public void reset(long seed, Level level) throws InputOutputRecordException{
		this.seed = seed;
		this.level = level;
		reset();
	}
	
	public void reset() throws InputOutputRecordException{
		rand = new Random(seed);
		jugador = new Player(this);
		container = new GameObjectContainer();
		GameObjectGenerator.reset(level);
		GameObjectGenerator.generateGameObjects(this, level);
		cycle = 0;
		startTime = System.currentTimeMillis();
		time = 0;
		record = new Record(this); //el constructor carga el record
		
	}
	
	public int posJugadorX() {
		return jugador.getX();
	}
	
	public int posJugadorY() {
		return jugador.getY();
	}
	
	public boolean isFinished() {
		return !jugador.isAlive() || hasArrived() || exit;
	}
	
	public long getRecord() {
		return record.getRecord();
	}

	public boolean isNewRecord() {
		return record.isNewRecord(time);
	}

	public void activateUserExit(){
		exit = true;
	}
	
	public boolean isUserExit(){
		return exit;
	}
	
	public void activarModoTest() {
		modoTest = true;
	}
	
	public boolean isTestMode() {
		return modoTest;
	}
	
	public void update() {
		container.update();
		GameObjectGenerator.generateRuntimeObjects(this);
		cycle++;
		time = System.currentTimeMillis()- startTime;
		container.removeDead();
	}
	
	public long elapsedTime() {
		return time;
	}
	
	public int getRandomLane() {
		  return (int) (getRandomNumber() *  level.getWidth());
	}	

	public Double getRandomNumber() {
		 return rand.nextDouble();
	}
	
	public int getRandomVisibleColumn() {
		return (int) (getRandomNumber() * getVisibility());
	}
	
	public void tryToAddObject(GameObject gameobject, double frequency){
		if(getRandomNumber() < frequency && 
				container.isPosEmpty(gameobject.getX(), gameobject.getY())) {
			container.add(gameobject);
		}
	}
	
	public boolean isPosEmpty (int x, int y) {
		return container.isPosEmpty(x, y);
	}
	
	public Collider getObjectInPosition(int x, int y) {
		Collider collider = container.getObjectinPos(x, y);
		return collider;
	}
	
	public String positionToString(int x, int y) {
		String s = "";
		if (jugador.isInPosition(x, y)) {
			 s = jugador.toString() + " ";
		}
		s += container.getPositionToString(x, y);
		if (x == getRoadLength()) {
			s += META;
		}
		return s.trim();
	}
	
	public String serializePositionToString(int x, int y) {
		return container.serializePositionToString(x,y);
	}
	
	public String serializePlayerToString() {
		return jugador.SerializeToString();
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public int getWidth() {
		return level.getWidth();
	}

	public int getRoadLength() {
		return level.getLength();
	}
	
	public boolean hasArrived() {
		return jugador.distanceToGoal() == -1; //para mostrar el jugador pasado la meta
	}
	
	public int distanceToGoal() {
		return jugador.distanceToGoal();
	}
	
	public int playerCoins() {
		return jugador.getPlayerCoins();
	}
	
	public void resetNumCoins() {
		jugador.resetNumCoins();
	}
	
	public int getCycle() {
		return this.cycle;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void goDown() {
		jugador.goDown();
	}
	
	public void goUp() {
		jugador.goUp();
	}
	
	public void goStraight() {
		jugador.goStraight();
	}

	public void incrementNumCoins(int coins){
		jugador.incrementNumCoins(coins);
	}
	
	public void eliminarColumna(int x) {
		container.eliminarObjetosColumna(x);
	}
	
	public void clear() {
		container.clear();
	}
	
	public void execute(InstantAction action) {
		action.execute(this);
	}

	public void forceAddObject(GameObject o) {
		container.add(o);
	}
	
	public boolean isInVisibleBoard(int x, int y, int visibility) {//le pasamos la pos absoluta y la visibility pq a veces queremos q compruebe hasta una más y otras no
		return (x >= this.posJugadorX() && x - posJugadorX() < visibility && y >= 0 && y < this.getWidth());
	}
	
	public boolean isInBoard(int x, int y, int visibility) {//en el tablero recorrido!
		return (x >= 0 && x - posJugadorX() < visibility && y >= 0 && y < this.getWidth());
	}

	public void guardarRecord() throws InputOutputRecordException {
		record.guardarRecord();
		
	}
	
}
