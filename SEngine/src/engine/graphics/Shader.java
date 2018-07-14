package engine.graphics;

import engine.math.Matrix4f;

public class Shader extends ShaderProgram {

	private static final String FRAGMENT_FILE = "res/shader/object1.frag";
	private static final String VERTEX_FILE = "res/shader/object1.vert";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;

	public Shader(){
		super(Shader.load(VERTEX_FILE, FRAGMENT_FILE));
	}
	
	public Shader(int programID) {
		super(programID);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(VertexArray.VERTEX_ATTRIBUTE_INDEX, "position");
		super.bindAttribute(VertexArray.TEXTURE_COORDINATE_ATTRIBUTE_INDEX, "textureCoords");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadprojectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
