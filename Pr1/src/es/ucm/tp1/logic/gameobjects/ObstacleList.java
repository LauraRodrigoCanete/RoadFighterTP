package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class ObstacleList {

	private static final String TOTAL_OBSTACLES_MSG = "Total obstacles: ";
	
	private Obstacle listaObstaculos[];
	private int contador;
	private int capacidad;
	private Game game;
	
	public ObstacleList(Game game, int n) {
		this.game = game;
		contador = 0;
		capacidad = n;
		listaObstaculos = new Obstacle[n];
	}
	
	public int getnumObstacles() {
		if(contador==0)
			return 0;
		else
			return listaObstaculos[0].getNumObstacles();
	}
	
	public String Info() {
		return TOTAL_OBSTACLES_MSG + getnumObstacles();
	}
	
	public boolean isPosEmpty(int i,int j) {
		boolean b = true;
		
		int k = 0;
		
		while(k < contador && b) {
			if (listaObstaculos[k].isInPosition(i, j)) b = false;
			else k++;
		}
		
		return b;
	}
	
	
	public void add(Obstacle obstaculo) {
		if(contador < capacidad) {
			listaObstaculos[contador] = obstaculo;
			contador++;
			listaObstaculos[0].setNumObstacles(contador);
		}
	}
	
	public Obstacle getObjectinPos(int i,int j){
		boolean found = false;
		int k = 0;
		
		while(k < contador && !found) {
			if (listaObstaculos[k].isInPosition(i, j)) found = true;
			else k++;
		}
		
		return listaObstaculos[k];
	}
}
