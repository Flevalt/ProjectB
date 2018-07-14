package engine.graphics;

import engine.display.Camera;
import engine.math.Matrix4f;

public class Shader extends ShaderProgram {

	private static final String FRAGMENT_FILE = "res/shader/object1.frag";
	private static final String VERTEX_FILE = "res/shader/object1.vert";

	public Shader(){
		super(Shader.load(VERTEX_FILE, FRAGMENT_FILE));
	}
	
	public Shader(int programID) {
		super(programID);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(Model.VERTEX_ATTRIBUTE_INDEX, "position");
		super.bindAttribute(Model.TEXTURE_COORDINATE_ATTRIBUTE_INDEX, "textureCoords");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.setUniformMatrix4f("transformationMatrix", matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.setUniformMatrix4f("projectionMatrix", projection);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.setUniformMatrix4f("viewMatrix", Matrix4f.createViewMatrix(camera));
	}
}
