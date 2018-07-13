package engine.objects;

import java.util.ArrayList;
import java.util.List;

public class Scene {

	private List<Entity> gameObjects = new ArrayList<>();
	
	public void addDisplayObject(Entity object) {
		if(object == null) {
			throw new IllegalArgumentException();
		}
		if(gameObjects.contains(object)) {
			System.err.println("Duplicate GameObject in Scene!");
		}else {
			gameObjects.add(object);
		}
	}

	public void removeDisplayObject(Entity object) {
		if(object == null) {
			throw new IllegalArgumentException();
		}
		gameObjects.remove(object);
	}
	
	public List<Entity> getDisplayObjects(){
		return gameObjects;
	}

}
