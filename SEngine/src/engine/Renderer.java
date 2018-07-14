package engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.graphics.Color;
import engine.graphics.DisplayManager;
import engine.graphics.Shader;
import engine.graphics.VertexArray;
import engine.math.Matrix4f;
import engine.objects.Entity;
import engine.objects.Scene;

/**
 * @author BpZ
 * TODO: Change renderable to Abstract class and add observer
 * 		to notify the renderer is it was destroyed
 * 
 * TODO: Add fps functionality.
 */
public class Renderer {
	
	private Scene currentScene;
	private int fps;
	private Shader shader;
	private DisplayManager display;
	private boolean isInitialised;
	
	private static final float FIELD_OF_VIEW = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	/**
	 * @param display - The {@link DisplayManager} on which the {@link Renderer} is rendering.
	 * @param shader - Using the standard shader if it set to {@link Null}.
	 */
	public Renderer(DisplayManager display, Shader shader) {
		if(shader == null) {
			this.shader = new Shader();
		}else {
			this.shader = shader;
		}
		initialise(display);	
	}
	
	/**
	 * @param display - The {@link DisplayManager} on which the {@link Renderer} is rendering.
	 * @param color - {@link Color} of the Background.
	 * @param shader - Using the standard shader if it set to {@link Null}.
	 */
	public Renderer(DisplayManager display, Color color, Shader shader) {
		if(shader == null) {
			this.shader = new Shader();
		}else {
			this.shader = shader;
		}		
		initialise(display);
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha);
	}
	
	private void initialise(DisplayManager display) {
		this.display = display;
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);	
		//Setting the projection matrix
		shader.enable();
		float aspect = (float) display.getWindowWidth() / (float) display.getWindowheight();
		shader.loadprojectionMatrix(Matrix4f.perspective(FIELD_OF_VIEW, aspect, NEAR_PLANE, FAR_PLANE));
		shader.disable();	
		isInitialised = true;
	}
	
	public boolean isInitialised() {
		if(shader == null || !isInitialised) {
			return false;
		}
		return true;
	}
	
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				
		shader.enable();
		
		//Render every element in the scene
		if(currentScene != null) {
			for(Entity obj : currentScene.getDisplayObjects()) {
				if(!obj.isHidden()) {
					renderDisplayObject(obj);	
				}
			}
		}
		
		shader.disable();
	}
	
	private void renderDisplayObject(Entity obj) {
		//Bind the resources
		GL30.glBindVertexArray(obj.getVertexArray().getVertexArrayObjectId());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, obj.getVertexArray().getIndexBufferObjectId());
		GL20.glEnableVertexAttribArray(VertexArray.VERTEX_ATTRIBUTE_INDEX);
		GL20.glEnableVertexAttribArray(VertexArray.TEXTURE_COORDINATE_ATTRIBUTE_INDEX);
		
		//set the transformation matrix
		Matrix4f transformationMatrix = Matrix4f.createTransformationMatrix(
				obj.getPosition(), obj.getRotX(), obj.getRotY(), obj.getRotZ(), obj.getScale());
		shader.loadTransformationMatrix(transformationMatrix);

		//Activate and bind the textures
		GL13.glActiveTexture(obj.getTexture().getTextureId());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getTexture().getTextureId());
		GL11.glDrawElements(GL11.GL_TRIANGLES, obj.getVertexArray().getCount(), GL11.GL_UNSIGNED_BYTE, 0); //Render elements
		
		//Unbind the resources
		GL20.glDisableVertexAttribArray(VertexArray.VERTEX_ATTRIBUTE_INDEX);
		GL20.glDisableVertexAttribArray(VertexArray.TEXTURE_COORDINATE_ATTRIBUTE_INDEX);
		GL30.glBindVertexArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void setScene(Scene scene) {
		this.currentScene = scene;
	}
	
	public int getFps() {
		return fps;
	}
	
	public void setShader(Shader shader) {
		this.shader = shader;	
	}
	
	public Shader getShader() {
		return shader;
	}
	
}
