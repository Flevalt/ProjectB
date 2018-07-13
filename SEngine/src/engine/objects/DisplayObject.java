package engine.objects;

import java.util.Observable;

import engine.graphics.VertexArray;
import engine.textures.Texture;

public class DisplayObject extends Observable {
	
	private Texture texture;
	private VertexArray vertexArray;
		
	public DisplayObject(Texture texture, VertexArray vertexArray) {
		super();
		this.texture = texture;
		this.vertexArray = vertexArray;
	}
	
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public VertexArray getVertexArray() {
		return vertexArray;
	}
	public void setVertexArray(VertexArray vertexArray) {
		this.vertexArray = vertexArray;
	}
	
	public void destroy() {
		this.getVertexArray().delete();
		this.texture.delete();
		notifyObservers();
	}
	
}
