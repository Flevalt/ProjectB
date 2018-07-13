package engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class InputMouseCallback extends GLFWMouseButtonCallback {


	@Override
	public void invoke(long window, int button, int action, int mods) {
		Input.keys[button] = action != GLFW.GLFW_RELEASE;		
	}
}
