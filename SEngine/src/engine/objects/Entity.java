package engine.objects;

import java.util.Observable;

import engine.graphics.VertexArray;
import engine.math.Vector3f;
import engine.textures.Texture;

public class Entity extends Observable{

	private DisplayObject displayObject;
	private Vector3f position;
	private float rotX;
	private float rotY;
	private float rotZ;
	private float scale;
	private boolean hidden;
	
	public Entity(Texture texture, VertexArray vertexArray, Vector3f position, float rotX, float rotY,
			float rotZ, float scale) {
		super();
		this.displayObject = new DisplayObject(texture, vertexArray);
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}	
	
	public Entity(DisplayObject displayObject, Vector3f position, float rotX, float rotY,
			float rotZ, float scale) {
		super();
		this.displayObject = displayObject;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}		
	
	/**
	 * Adds the values to the position of the {@link Entity}.
	 * @param x - Position on the x axis.
	 * @param y - Position on the y axis.
	 * @param z - Position on the z axis.
	 */
	public void changePosition(float x, float y, float z) {
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}
	
	
	/**
	 * Adds the {@link Vector3f} to the position of the Object.
	 * @param vector to be added.
	 */
	public void changePosition(Vector3f vector) {
		this.position.x += vector.x;
		this.position.y += vector.y;
		this.position.z += vector.z;
	}
	
	/**
	 * Adds the values to the rotation of the {@link Entity}.
	 * @param x - Rotation on the x axis.
	 * @param y - Rotation on the y axis.
	 * @param z - Rotation on the z axis.
	 */
	public void changeRotation(float x, float y, float z) {
		this.rotX += x;
		this.rotY += y;
		this.rotZ += z;
	}
	
	/**
	 * Adds the {@link Vector3f} to the rotation of the Object.
	 * @param vector to be added.
	 */
	public void changeRotation(Vector3f vector) {
		this.rotX += vector.x;
		this.rotY += vector.y;
		this.rotZ += vector.z;
	}

	public DisplayObject getDisplayObject() {
		return displayObject;
	}

	public void setDisplayObject(DisplayObject displayObject) {
		this.displayObject = displayObject;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void hide() {
		hidden = true;
	}
	
	public void show() {
		hidden = false;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public Texture getTexture() {
		if(displayObject != null) {
			return displayObject.getTexture();			
		}else {
			return null;
		}
	}

	public VertexArray getVertexArray() {
		if(displayObject != null) {
			return displayObject.getVertexArray();		
		}else {
			return null;
		}
	}
}
