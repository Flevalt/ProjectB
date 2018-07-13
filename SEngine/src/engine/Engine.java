package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import org.lwjgl.opengl.GL11;

import engine.graphics.Shader;
import engine.input.InputKeyCallback;
import engine.input.InputMouseCallback;
import engine.objects.Scene;

public abstract class Engine {
	
	private long window;
	private int width = 800;
	private int height = 600;
	private Renderer renderer;
	private String title = "";
	
	private void initialise() {
		GLFWErrorCallback.createPrint(System.err).set();
		if(GLFW.glfwInit() == false) {
			throw new RuntimeException("Could not initialise GLFW!");
		}
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0); //Creating the window
		GLFW.glfwMakeContextCurrent(window); 
		
		GLFW.glfwSetKeyCallback(window, new InputKeyCallback());
		GLFW.glfwSetMouseButtonCallback(window, new InputMouseCallback());
					
		GLFW.glfwSwapInterval(1); //FPS
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		renderer = new Renderer(0, 0, 0, 1);		
		renderer.setShader(new Shader());
		
		init();
	}
	
	/**
	 * Sets the resolution of the game window.
	 * default is 800 x 600.
	 * @param width of the window.
	 * @param height of the window.
	 */
	public void setResolution(int width, int height) {
		if(width < 0 || height < 0) {
			throw new IllegalArgumentException("Dimentsions of the window mustn't be negative");
		}
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the title of the game window.
	 * @param title of the window.
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title mustn't be null");
		}
		this.title = title;
	}
	
	/**
	 * Closes the game window.
	 */
	public void close() {
		GLFW.glfwDestroyWindow(window);
	}
	
	/**
	 *  Starts the engine.
	 */
	public void run() {
		initialise();			

		while(!GLFW.glfwWindowShouldClose(window)) {			
			update();
			renderer.render();			
			GLFW.glfwSwapBuffers(window);			
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
