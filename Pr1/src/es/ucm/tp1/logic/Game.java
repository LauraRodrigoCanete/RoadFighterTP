package es.ucm.tp1.logic;

import java.util.Random;
import java.util.Scanner;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.CoinList;
import es.ucm.tp1.logic.gameobjects.ObstacleList;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.view.GamePrinter;
//al final de todo checkear el convenio de mayusculas y minusculas en tipos
public class Game {
	
	private static final String CYCLE_MSG = "Cycle: ";

	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";
	
	private Random rand;
	private Player jugador;
	private CoinList listaMonedas;
	private ObstacleList listaObstaculos;
	private GamePrinter gamePrinter; 
	private int ciclo;
	private Level level;
	private long startTime;
	private boolean modoTest;
	private long seed;
	
	public Game(long seed,Level level,boolean isTestMode) {
		//constructor
		this.seed = seed;
		rand = new Random();
		this.level = level;
		gamePrinter = new GamePrinter(this, level.getVisibility(), level.getWidth()); 
		modoTest = isTestMode;
		reset();
	}
	
	public void reset() {
		rand.setSeed(seed);
		jugador = new Player(this);
		listaMonedas= new CoinList(this, level.getLength()-level.getVisibility()/2);//como mucho hay una moneda por columna a partir de visibility/2
		listaObstaculos= new ObstacleList(this, level.getLength()-level.getVisibility()/2);
		colocarObjetos();
		ciclo = 0;
	}
	
	public void setModoTest() {
		modoTest = true;
	}
	
	public boolean getModoTest() {
		return modoTest;
	}
	
	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}
	
	public int getWidth() {
		return level.getWidth();
	}
	
	public int getLength(){
		return level.getLength();
	}
	
	public boolean enMeta() {
		return jugador.distanceToGoal() == -1; //para mostrar el jugador pasado la meta
	}
	
	public String getGameStatus(){
		if(ciclo == 0)
			startTime = System.currentTimeMillis();	
		String status = String.format("%s%n%s%s%n%s%n%s", jugador.Info(), CYCLE_MSG, ciclo, listaObstaculos.Info(), listaMonedas.Info());
		if(!modoTest)
			status += String.format("%n%s%s", ELAPSED_TIME_MSG, ellapsedTime());
		return status;
	}
	
	public String ellapsedTime() {
		return formatTime(System.currentTimeMillis()- startTime);
	}
	
	public static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}
	
	public void goUp() {
		getPlayer().goUp();
		update();
	}
	
	public void goDown() {
		getPlayer().goDown();
		update();
	}
	
	public void update() {
		jugador.update();
		ciclo++;
		listaMonedas.removeDead();
	}
	
	public Player getPlayer() {
		return this.jugador;
	}
	
	public int getPlayerPosY() {
		return jugador.returnPositionPlayerY();
	}
	
	public int getPlayerPosX() {
		return jugador.returnPositionPlayerX();
	}
	
	public boolean isPlayerAlive() {
		return jugador.isAlive();
	}
	
	public Coin getCoininPos(int i,int j){
		return listaMonedas.getObjectinPos(i, j);
	}
	
	public Obstacle getObstacleinPos(int i,int j){
		return listaObstaculos.getObjectinPos(i, j);
	}
	
	public boolean isPosEmptyCoin(int i,int j) {
		return listaMonedas.isPosEmpty(i, j);
	}
	
	public boolean isPosEmptyObstacle(int i,int j) {
		return listaObstaculos.isPosEmpty(i, j);
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
	
	public void colocarObjetos() {
		for (int x = level.getVisibility() / 2; x < level.getLength(); x++) {
			tryToAddObstacle(new Obstacle(this, x, getRandomLane()), level.getObstacleFrequency());
			tryToAddCoin(new Coin(this, x, getRandomLane()), level.getCoinFrequency());
		}
	}
	
	public int getRandomLane() {
		  return (int) (getRandomNumber() *  level.getWidth());
	}	

	public Double getRandomNumber() {
		 return rand.nextDouble();
	}
	
	public void tryToAddObstacle(Obstacle obstaculo, double frequency){
		if(getRandomNumber() < frequency && 
				listaMonedas.isPosEmpty(obstaculo.getPosx(), obstaculo.getPosy()) &&
				listaObstaculos.isPosEmpty(obstaculo.getPosx(), obstaculo.getPosy())) {
			listaObstaculos.add(obstaculo);
		}
	}
	
	public void tryToAddCoin(Coin coin, double frequency){
		if(getRandomNumber() < frequency && 
				listaObstaculos.isPosEmpty(coin.getPosx(), coin.getPosy()) &&
				listaMonedas.isPosEmpty(coin.getPosx(), coin.getPosy())) {
			listaMonedas.add(coin);
		}
	}
}
