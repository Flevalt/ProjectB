package engine.objects;

import engine.graphics.VertexArray;
import engine.math.Vector3f;
import engine.textures.Texture;

/**
 * @author BpZ
 * 
 * The {@link GameObject} class extends an {@link Entity} to
 * provide more functionality for an object represented in the game.
 *
 * TODO: Notify the scene if this object was deleted.
 */
public class GameObject extends Entity {
	
	/**
	 * The level determines if a collision can occur between two objects.
	 */
	public int level;
	public String name;
	
	public GameObject(String name, DisplayObject displayObject) {
		super(displayObject, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		this.name = name;
	}
	
	public GameObject(String name, String texturePath, VertexArray vertexArray) {
		super(new Texture(texturePath), vertexArray, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		this.name = name;
	}	
	
	public GameObject(String name, Texture texture, VertexArray vertexArray,
			Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(texture, vertexArray, position, rotX, rotY, rotZ, scale);
		this.name = name;
	}	
	
	public GameObject(String name, String texturePath,
			VertexArray vertexArray, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(new Texture(texturePath), vertexArray, position, rotX, rotY, rotZ, scale);
		this.name = name;
	}	
	
	public GameObject(Texture texture, VertexArray vertexArray, Vector3f position, float rotX, float rotY,
			float rotZ, float scale) {
		super(texture, vertexArray, position, rotX, rotY, rotZ, scale);
	}	
	
	public GameObject(String texturePath, VertexArray vertexArray, Vector3f position, float rotX, float rotY,
			float rotZ, float scale) {
		super(new Texture(texturePath), vertexArray, position, rotX, rotY, rotZ, scale);
	}	
	
	public void delete() {
		//TODO: Implement
	}

}
