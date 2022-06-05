package es.ucm.tp1.supercars.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;


public class GameObjectContainer {//solo maneja gameobjects
	
	private List<GameObject> gameobjects;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
	}
	
	public void update() {
		removeDead();
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
			}
		}	
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
	
}
