package engine.objects;

import java.util.Observable;

import engine.graphics.Model;
import engine.textures.Texture;

/**
 * @author BpZ1
 * 
 * Contains a {@link Model} and a {@link Texture}.<br>
 * Can be used for multiple {@link Entity}s.
 */
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
	/**
	 * Replaces the {@link Texture} with the given {@link Texture}.
	 * @param texture - New {@link Texture} for the {@link TexturedModel}.
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setVertexArray(Model vertexArray) {
		this.model = vertexArray;
	}
	
	/**
	 * Deletes all resources.
	 */
	public void delete() {
		this.model.delete();
		this.texture.delete();
		notifyObservers();
	}
	
}
