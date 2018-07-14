package engine.graphics;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import engine.math.Matrix4f;
import engine.math.Vector3f;
import engine.utils.FileUtils;

/**
 * @author BpZ
 * 
 * The shader consists of a fragment and a vertex shader.
 * The vertex shader's input is the VAO data and its output
 * are the values for each vertex. The vertex shader is therefore
 * executed once for each vertex.
 * 
 * The fragment shader's input are the output values of the vertex shader
 * and the distances of the vertices. Its output is a value for every pixel in
 * a given triangle. The fragment shader is therefore calculated for every pixel
 * in a triangle.
 */
public abstract class ShaderProgram {
	
	private boolean enabled;
	
	private final int ID;
	
	public ShaderProgram(int id) {
		this.ID = id;
	}
	
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	protected abstract void getAllUniformLocations();
	
	public int getUniformLocation(String name) {
		if(locationCache.containsKey(name)) {
			return locationCache.get(name);
		}	
		int result = GL20.glGetUniformLocation(ID, name);
		if(result == -1) {
			System.err.println("Could not find uniform variable '" + name + "' !");
		}else {
			locationCache.put(name, result);
		}
		return result;
	}
	
	public void loadMatrix(int location, Matrix4f matrix) {
		GL20.glUniform4fv(location, matrix.toFloatBuffer());
	}
	
	public void setUniform1i(String name, int value) {
		GL20.glUniform1i(getUniformLocation(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		GL20.glUniform1f(getUniformLocation(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		GL20.glUniform2f(getUniformLocation(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		GL20.glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniformMatrix4f(String name, Matrix4f matrix) {
		GL20.glUniformMatrix4fv(getUniformLocation(name), false, matrix.toFloatBuffer());
	}
	
	public void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(ID, attribute, variableName);
	}
	
	protected abstract void bindAttributes();
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void enable() {
		GL20.glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		GL20.glUseProgram(0);
		enabled = false;
	}
	
	public void delete() {
		disable();
		GL20.glDeleteProgram(ID);
	}
	
	/**
	 * Creates an {@link Shader} from the paths to vertex and fragment shader files.
	 * @param vertPath - Path to the vertex shader.
	 * @param fragPath - Path to the fragment shader.
	 * @return handle to the shader program.
	 */
	public static int load(String vertPath, String fragPath) {
		String vert = FileUtils.loadAsString(vertPath);
		String frag = FileUtils.loadAsString(fragPath);
		return create(vert,frag);
	}
	
	/**
	 * Creates an {@link Shader} from a given vertex and fragment shader.
	 * @param vert - Vertex shader as {@link String}.
	 * @param frag - Fragment shader as {@link String}.
	 * @return handle to the shader program.
	 */
	public static int create(String vert, String frag) {
		int program = GL20.glCreateProgram();
		int vertID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		int fragID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(vertID, vert);
		GL20.glShaderSource(fragID, frag);
		GL20.glCompileShader(vertID);
		//test if shader is compiled
		if(GL20.glGetShaderi(vertID, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE) {
			System.err.println("Failed to compile vertex shader!");
			System.err.println(GL20.glGetShaderInfoLog(vertID));
			return -1;
		}
		//test if shader is compiled
		GL20.glCompileShader(fragID);
		if(GL20.glGetShaderi(fragID, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE) {
			System.err.println("Failed to compile fragmented shader!");
			System.err.println(GL20.glGetShaderInfoLog(fragID));
			return -1;
		}
		GL20.glAttachShader(program, vertID);
		GL20.glAttachShader(program, fragID);
		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);
		
		//Shader can be deleted after they are part of the program
		GL20.glDeleteShader(vertID);
		GL20.glDeleteShader(fragID);
		return program;
	}
}
