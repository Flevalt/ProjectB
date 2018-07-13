package engine.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Scene implements Observer {

	private List<Entity> gameObjects = new ArrayList<Entity>();
	
	public void addDisplayObject(Entity object) {
		if(object == null) {
			throw new IllegalArgumentException();
		}
		if(gameObjects.contains(object)) {
			System.err.println("Duplicate GameObject in Scene!");
		}else {
			gameObjects.add(object);
			object.addObserver(this);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		gameObjects.remove((Entity) arg1);
		
	}

}
