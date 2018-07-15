package engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.display.Camera;
import engine.display.DisplayManager;
import engine.graphics.Color;
import engine.graphics.Model;
import engine.graphics.RenderMode;
import engine.graphics.Shader;
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
	private Camera camera;
	private RenderMode renderMode;
	private boolean isInitialised;
	private float FIELD_OF_VIEW = 70;//Default = 70
	private float NEAR_PLANE = 0.1f;//Default = 0.1f
	private float FAR_PLANE = 1000;//Default = 1000
	
	
	/**
	 * @param display - The {@link DisplayManager} on which the {@link Renderer} is rendering.
	 * @param color - {@link Color} of the Background.
	 * @param shader - Using the standard shader if it set to {@link Null}.
	 */
	public Renderer(DisplayManager display, Camera camera, Color color, Shader shader) {
		if(shader == null) {
			this.shader = new Shader();
		}else {
			this.shader = shader;
		}
		initialise(display, camera);
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha);
	}
	
	/**
	 * @param display - The {@link DisplayManager} on which the {@link Renderer} is rendering.
	 * @param color - {@link Color} of the Background.
	 * @param shader - Using the standard shader if it set to {@link Null}.
	 */
	public Renderer(DisplayManager display, Camera camera, Color color, Shader shader, RenderMode mode) {
		if(shader == null) {
			this.shader = new Shader();
		}else {
			this.shader = shader;
		}
		this.renderMode = mode;
		initialise(display, camera);
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha);
	}
	
	/**
	 * @param display - The {@link DisplayManager} on which the {@link Renderer} is rendering.
	 * @param color - {@link Color} of the Background.
	 * @param shader - Using the standard shader if it set to {@link Null}.
	 * @param fov - Field of view for the perspective.
	 * @param nearPlane - Nearest rendered plane.
	 * @param farPlane - Most distant rendered plane. 
	 */
	public Renderer(DisplayManager display, Camera camera, Color color, Shader shader,
			float fov, float nearPlane, float farPlane) {
		if(shader == null) {
			this.shader = new Shader();
		}else {
			this.shader = shader;
		}
		FIELD_OF_VIEW = fov;
		NEAR_PLANE = nearPlane;
		FAR_PLANE = farPlane;
		initialise(display, camera);
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha);
	}
	
	private void initialise(DisplayManager display, Camera camera) {
		this.display = display;
		this.camera = camera;
		GL11.glEnable(GL11.GL_DEPTH_TEST);	
		loadProjectionMatrix();
		isInitialised = true;
	}
	
	private void loadProjectionMatrix() {
		shader.enable();
		if(renderMode == RenderMode.MODE_3D) {
			float aspect = (float) display.getWindowWidth() / (float) display.getWindowheight();
			shader.loadProjectionMatrix(Matrix4f.perspective(FIELD_OF_VIEW, aspect, NEAR_PLANE, FAR_PLANE));
		}else {
			shader.loadProjectionMatrix(Matrix4f.orthographic(-1, 1, -1, 1, NEAR_PLANE, FAR_PLANE));
		}
		shader.disable();
	}
	
	
	public boolean isInitialised() {
		if(shader == null || !isInitialised) {
			return false;
		}
		return true;
	}
	
	/**
	 * Start rendering all objects within the current {@link Scene}.
	 */
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
		GL20.glEnableVertexAttribArray(Model.VERTEX_ATTRIBUTE_INDEX);
		GL20.glEnableVertexAttribArray(Model.TEXTURE_COORDINATE_ATTRIBUTE_INDEX);
		
		shader.loadViewMatrix(camera);
		
		//set the transformation matrix
		Matrix4f transformationMatrix = Matrix4f.createTransformationMatrix(
				obj.getPosition(), obj.getRotX(), obj.getRotY(), obj.getRotZ(), obj.getScale());
		shader.loadTransformationMatrix(transformationMatrix);

		//Activate and bind the textures
		GL13.glActiveTexture(obj.getTexture().getTextureId());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getTexture().getTextureId());
		GL11.glDrawElements(GL11.GL_TRIANGLES, obj.getVertexArray().getCount(), GL11.GL_UNSIGNED_BYTE, 0); //Render elements
		
		//Unbind the resources
		GL20.glDisableVertexAttribArray(Model.VERTEX_ATTRIBUTE_INDEX);
		GL20.glDisableVertexAttribArray(Model.TEXTURE_COORDINATE_ATTRIBUTE_INDEX);
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
