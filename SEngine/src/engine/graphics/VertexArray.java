package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.utils.BufferUtils;


/**
 * @author BpZ
 *
 * Contains data of a vertex array.
 * TODO: Add check if vertex array was deleted.
 * TODO: Add method for adding texture.
 */
public class VertexArray {
	
	/**
	 * The index in the VAO in which the vertex attribute data is stored.
	 */
	public static final int VERTEX_ATTRIBUTE_INDEX = 0;
	/**
	 * The index in the VAO in which the texture coordinates are stored.
	 */
	public static final int TEXTURE_COORDINATE_ATTRIBUTE_INDEX = 1;	
	/**
	 * Address of the Vertex Array Object.
	 */
	private int vao;
	/**
	 * Address of the Vertex Buffer Object.
	 */
	private int vbo;
	/**
	 * Address if the Index Buffer Object.
	 */
	private int ibo;
	/**
	 * Address of the Texture Buffer Object.
	 */
	private int tbo;	
	private int count;	
	
	/**
	 * Creates a Vertex Array Object from a given array of vertices, indices and texture coordinates.
	 * Each vertex has to only be contained once in the array of vertices.
	 * The indices array contains the order in which the vertices make up a given object.
	 * 
	 * @param vertices - Coordinates of all vertices in pairs of three not containing duplicates.<br>
	 * 					The vertices have to be given counter clockwise:
	 * 					
	 * 			  (-1,1) ____(-0.5,1)  		-1, 1, 0
	 * 					|   /| 				-1, 0, 0
	 * 					|  / |			=   -0.5, 1, 0
	 * 					| /	 |			    -0.5, 0, 0
	 * 			  (-1,0)|/___|(-0.5,0)
	 * 
	 * @param indices - The indices of the used vertices.
	 * 					For the example above:
	 * 					(0, 1, 2, 1, 3, 2)
	 * 
	 * 			
	 * @param textureCoordinates - The coordinates for the texture.<br>
	 * 		The texture coordinates start at the top left corner with (0,0)
	 * 
	 * 				(0,0)_______(1,0)
	 * 		   		 	|       |
	 * 				 	|		|
	 *		       (0,1)|_______|(1,1)
	 */
	public VertexArray(float [] vertices, byte[] indices, float[] textureCoordinates) {
		if(vertices == null || indices == null || textureCoordinates == null) {
			throw new IllegalArgumentException("Can't instantiate VertexArray with null!");
		}			
		count = indices.length;

		createVAO();	
		createVBO(VERTEX_ATTRIBUTE_INDEX, vertices);		
		createTBO(TEXTURE_COORDINATE_ATTRIBUTE_INDEX, textureCoordinates);
		createIBO(indices);
		unbindBuffers();
	}
	
	public VertexArray(float [] vertices, byte[] indices) {
		if(vertices == null || indices == null) {
			throw new IllegalArgumentException("Can't instantiate VertexArray with null!");
		}			
		count = indices.length;

		createVAO();	
		createVBO(VERTEX_ATTRIBUTE_INDEX, vertices);		
		createIBO(indices);
		unbindBuffers();
	}
	
	
	/**
	 * Create the Vertex Attribute Object and bind it
	 */
	private void createVAO() {
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);	
	}
	/**
	 * Create the Vertex Buffer Object and bind it
	 * @param index - Array index of the vao in which the data is to be stored.
	 * @param vertices
	 */
	private void createVBO(int index, float [] vertices) {
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, 3, GL11.GL_FLOAT,false , 0, 0);
		//GL20.glEnableVertexAttribArray(index); //TEST
	}
	/**
	 * Create the texture coordinate Buffer and bind it
	 * @param index - Array index of the vao in which the data is to be stored.
	 * @param textureCoordinates
	 */
	private void createTBO(int index, float[] textureCoordinates) {
		tbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, tbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, 2, GL11.GL_FLOAT,false , 0, 0);
		GL20.glEnableVertexAttribArray(index);
	}
	/**
	 * Create the index buffer object and bind it
	 * @param indices
	 */
	private void createIBO(byte[] indices) {
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL15.GL_STATIC_DRAW);
	}
	
	private void storeDataInAttributeList(int attributeIndex, int size, float[] data) {
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(data), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeIndex, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Unbind the buffers
	 */
	private void unbindBuffers() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);	
	}
	
	public void bind() {
		GL30.glBindVertexArray(vao);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	public void enable() {
		bind();
		GL20.glEnableVertexAttribArray(VERTEX_ATTRIBUTE_INDEX);
	}
	
	public void disable() {
		GL20.glDisableVertexAttribArray(VERTEX_ATTRIBUTE_INDEX);
		unbind();
	}
	
	public void unbind() {
		GL30.glBindVertexArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void draw() {
		GL11.glDrawElements(GL11.GL_TRIANGLES, count, GL11.GL_UNSIGNED_BYTE, 0);
	}
	
	/**
	 * Binds the VAO of the {@link VertexArray} and draws it.
	 */
	public void render() {		
		bind();
		GL20.glEnableVertexAttribArray(VERTEX_ATTRIBUTE_INDEX);
		draw();
		GL20.glDisableVertexAttribArray(VERTEX_ATTRIBUTE_INDEX);
		unbind();
	}
	
	public int getVertexArrayObjectId() {
		return vao;
	}
	public int getIndexBufferObjectId() {
		return ibo;
	}
	
	public int getCount() {
		return count;
	}
	
	/**
	 * Deletes all buffers in the current {@link VertexArray}.
	 * This frees the resources and makes the {@link VertexArray} unusable.
	 */
	public void delete() {
		GL15.glDeleteBuffers(vao);
		GL15.glDeleteBuffers(vbo);
		GL15.glDeleteBuffers(tbo);
		GL15.glDeleteBuffers(ibo);
	}
}
