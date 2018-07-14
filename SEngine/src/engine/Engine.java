package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import org.lwjgl.opengl.GL11;

import Exceptions.OpenGLException;
import engine.display.Camera;
import engine.display.DisplayManager;
import engine.display.Resolution;
import engine.graphics.Color;
import engine.objects.Scene;

public abstract class Engine {
	
	private DisplayManager display = new DisplayManager();;
	private Renderer renderer;
	private Camera camera = new Camera();

	
	private void initialise() {
		GLFWErrorCallback.createPrint(System.err).set();
		if(GLFW.glfwInit() == false) {
			throw new RuntimeException("Could not initialise GLFW!");
		}
			
		display.createWindow();			
									
		GLFW.glfwSwapInterval(1); //FPS
		GL.createCapabilities();		
		renderer = new Renderer(display, camera, Color.white(), null);		
		init();
		
	}
	
	public void createWindow(Resolution resolution) {
		display.setResolution(resolution);
		display.createWindow();
	}

	
	public Camera getCamera() {
		return camera;
	}
	
	/**
	 * Closes the game window.
	 */
	public void close() {
		GLFW.glfwDestroyWindow(display.getWindowId());
	}
	
	/**
	 *  Starts the engine.
	 */
	public void run() {
		initialise();			

		if(!renderer.isInitialised()) {
			throw new OpenGLException("Shader is not set for the current renderer!");
		}
		while(!GLFW.glfwWindowShouldClose(display.getWindowId())) {			
			update();
			renderer.render();			
			GLFW.glfwSwapBuffers(display.getWindowId());			
			GLFW.glfwPollEvents();
		}
		
		//Free ressources	
		release();
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	
	/**
	 * Returns the version number of the currently used open GL version.
	 * @return - Version number of the used open GL version.
	 */
	public String getOpenGLVersion() {
		return GL11.glGetString(GL11.GL_VERSION);
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public DisplayManager getDisplay() {
		return display;
	}
	
	public void setScene(Scene scene) {
		renderer.setScene(scene);
	}
	
	public int getFps() {
		return renderer.getFps();
	}
	
	/**
	 * Gets called every frame before rendering.
	 */
	public abstract void update();
	
	/**
	 * Gets called at the start of the engine.
	 */
	public abstract void init();
			
	/**
	 * Gets called at the end of the engine.
	 */
	public abstract void release();
}
