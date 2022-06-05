package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class CoinList {
	

	private static final String TOTAL_COINS_MSG = "Total coins: ";
	
	private Game game;
	private Coin listaMonedas[];
	private int contador;
	private int capacidad;
	
	public CoinList(Game game, int n) {
		this.game = game;
		contador = 0;
		capacidad = n;
		listaMonedas = new Coin[n]; 
	}
	
	
	public int getnumCoins() {
		if(contador==0)
			return 0;
		else
			return listaMonedas[0].getNumCoins();
	}
	
	public String Info() {
		return TOTAL_COINS_MSG + getnumCoins();
	}
	
	public boolean isPosEmpty(int i,int j) {
		boolean b = true;
		
		int k = 0;
		
		while(k < contador && b) {
			if (listaMonedas[k].isInPosition(i, j)) b = false;
			else k++;
		}
		
		return b;
	}
	
	
	public Coin getObjectinPos(int i,int j){
		boolean found = false;
		int k = 0;
		
		while(k < contador && !found) {
			if (listaMonedas[k].isInPosition(i, j)) found = true;
			else k++;
		}
		
		return listaMonedas[k];
	}
	
	public void add(Coin moneda) {
		if(contador < capacidad) {
			listaMonedas[contador] = moneda;
			contador++;
			listaMonedas[0].setNumCoins(contador);
		}
	}
	
	public void removeDead() {
		for(int i = 0; i < contador; i++) {
			if(listaMonedas[i].getEstado()) {
				listaMonedas[0].set1menosNumCoins();
				for (int j = i; j < (contador - 1); j++) {
					listaMonedas[j] = listaMonedas[j + 1];
				}
				listaMonedas[contador-1] = null;
				contador--;
			} 
		}
	}
	

}
