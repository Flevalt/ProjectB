package engine.graphics;

import engine.display.Camera;
import engine.math.Matrix4f;

public class Shader3D extends Shader {

	private static final String FRAGMENT_FILE = "res/shader/FragmentShader.frag";
	private static final String VERTEX_FILE = "res/shader/3DVertexShader.vert";

	public Shader3D(){
		super(Shader3D.load(VERTEX_FILE, FRAGMENT_FILE));
	}
	
	public Shader3D(int programID) {
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
