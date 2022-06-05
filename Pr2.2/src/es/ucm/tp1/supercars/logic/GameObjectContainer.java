package es.ucm.tp1.supercars.logic;

import java.util.ArrayList;
import java.util.List;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class GameObjectContainer {//solo maneja gameobjects
	
	private List<GameObject> gameobjects;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
	}
	
	public void update() {
		for (GameObject g : gameobjects) {
			g.update();
		}
	}
	
	public void add(GameObject gameobject) {
		gameobjects.add(gameobject);
		gameobject.onEnter();
	}
	
	public void removeDead() {
		//una forma: gameobjects.removeIf(gameObject -> !gameObject.isAlive());
		for(int i = 0; i < gameobjects.size(); i++) {
			if(!gameobjects.get(i).isAlive()) {
				gameobjects.get(i).onDelete();
				gameobjects.remove(i);
				i--;
			}
		}	
	}
	
	public String getPositionToString(int x, int y) {
		String s = "";
		for (GameObject g : gameobjects) {
			if (g.isInPosition(x, y)) {
				s += g.toString() + " "; 
			}
		}
		return s;
	}
	
	public GameObject getObjectinPos(int i,int j){
		boolean found = false;
		int k = 0;
		
		while(k < gameobjects.size() && !found) {
			if (gameobjects.get(k).isInPosition(i, j)) found = true;
			else k++;
		}
		if (!found) return null;
		
		return gameobjects.get(k);
	}
	
	public boolean isPosEmpty(int i,int j) {
		
		if(getObjectinPos(i,j) == null)
			return true;
		return false;
	}
	
	public void eliminarObjetosColumna (int x) {
		for (int i = 0; i < gameobjects.size(); ++i) {
			if (gameobjects.get(i).getX() == x) {
				gameobjects.get(i).onDelete();
				gameobjects.remove(i);
			}
		}
	}
	
	public void clear() {
		for (int i = 0; i < gameobjects.size(); ++i) {
				gameobjects.get(i).onDelete();		
		}
		gameobjects.clear();
	}

	
}
