package engine.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Matrix4fTest {

	private float[] testArray;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void transformationMatrixScale() {
		testArray =new float[] {0f, 0f, 0f, 2f,
								0f, 0f, 0f, 2f,
								0f, 0f, 0f, 2f,
								0f, 0, 0f, 0f};	
		
		Matrix4f matrix = Matrix4f.createTransformationMatrix(new Vector3f(0, 0, 0), 0, 0, 0, 2);
		float[] result = matrix.getElements();
		assertEquals(2.0f, result[0]);
		assertEquals(2.0f, result[5]);
		assertEquals(2.0f, result[10]);
	}
	
	@Test
	void transformationMatrixRotate() {
		
	}
	
	@Test
	void transformationMatrixScaleAndRotate() {
		
	}
}
