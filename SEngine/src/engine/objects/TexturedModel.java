package engine.objects;

import java.util.Observable;

import engine.graphics.Model;
import engine.textures.Texture;

public class TexturedModel extends Observable {
	
	private Texture texture;
	private Model model;
		
	public TexturedModel(Texture texture, Model model) {
		super();
		this.texture = texture;
		this.model = model;
	}
	
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Model getVertexArray() {
		return model;
	}
	public void setVertexArray(Model vertexArray) {
		this.model = vertexArray;
	}
	
	public void destroy() {
		this.getVertexArray().delete();
		this.texture.delete();
		notifyObservers();
	}
	
}
