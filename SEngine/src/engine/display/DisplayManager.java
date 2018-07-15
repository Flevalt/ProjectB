package engine.display;

import org.lwjgl.glfw.GLFW;

import engine.input.InputKeyCallback;
import engine.input.InputMouseCallback;

public class DisplayManager {
	
	private long window;
	private boolean windowCreated;
	public static Resolution resolution = Resolution.SVGA;
	private String title = "";
	private boolean fullscreen;
	
	public DisplayManager() {
		
	}
	
	public DisplayManager(Resolution resolution) {
		if(resolution == null) {
			throw new IllegalArgumentException("Resolution mustn't be null!");
		}
		this.resolution = resolution;
	}
	
	public void setResolution(Resolution resolution) {
		if(resolution == null) {
			throw new IllegalArgumentException("Resolution mustn't be null!");
		}
		this.resolution = resolution;
	}
	
	public int getWindowWidth() {
		return resolution.width();
	}
	
	public int getWindowheight() {
		return resolution.height();
	}
	
	public void activateFullscreen() {
		this.fullscreen = true;
	}
	
	public void deactivateFullscreen() {
		this.fullscreen = false;
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
	
	public void createWindow() {
		if(fullscreen) {
			window = GLFW.glfwCreateWindow(resolution.width(), resolution.height(), title, GLFW.glfwGetPrimaryMonitor(), 0); //Creating the window			
		}else {
			window = GLFW.glfwCreateWindow(resolution.width(), resolution.height(), title, 0, 0); //Creating the window			
		}
		
		GLFW.glfwMakeContextCurrent(window); 
		GLFW.glfwSetKeyCallback(window, new InputKeyCallback());
		GLFW.glfwSetMouseButtonCallback(window, new InputMouseCallback());
		windowCreated = true;
	}
	
	public boolean windowCreated() {
		return windowCreated;
	}
	
	public long getWindowId() {
		return window;
	}
	
}
