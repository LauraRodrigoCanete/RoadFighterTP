package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.actions.ThunderAction;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;


public class GameObjectGenerator {
	
	public static void generateGameObjects(Game game, Level level) {
		for(int x = game.getVisibility() /2; x < game.getRoadLength(); x ++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
					
			if (level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
					if (!SuperCoin.hasSuperCoin()) {
						game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
					}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.advancedObjectsFrequency());
			}
		}
		
	}

	/* notas para nosotras(teoría):
	 * metodos estaticos solo pueden manejar otros metodos estaticos (los resets de los objetitos) y atributos estaticos (numCoins, numObstacles... dentro de los objetitos); habría que llamarlos desde una instancia.
	 * a diferencia de los instance methods (normales) que "pertenecen" a un objeto/instancia de una clase, hay que crear ese objeto, y estos métodos solo conocel al objeto al que pertenecen
	 * los static están asociados a la clase en sí (por como se crean en memoria), están diseñados para ser compartidos por los objetos de una misma clase;
	 * no se pueden sobreescribir (si declaras 2 iguales se ocultaría el de la superclase); no pueden usar this porque no estan referenciando a ninguna instancia */
	public static void reset(Level level) {
		Obstacle.reset(); //reset static; Obstacle es la clase Obstacle no una instancia de un objeto (no es ninguno de los news de arriba) 
		Coin.reset(); //para llamar a un método static no hace falta (se puede) tener una instancia del objeto donde esté el método, se puede llamar con el nombre la clase
	}
	
	public static int getCoinsCount(){ 
		return Coin.getNumCoins();
	}
	
	public static int getObstaclesCount(){ 
		return Obstacle.getNumObstacles();
	}
	
	public static void generateRuntimeObjects(Game game) {
		// Note we use this method to create and inject new objects or actions on runtime.
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		}
	}
		
}
