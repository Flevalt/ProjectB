package engine.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BufferUtilsTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void byteBufferSizeTest() {
		byte[] array = {12, 33, 41, 41, 22, 13, 13, 41, 11};
		ByteBuffer buffer = BufferUtils.createByteBuffer(array);
		assertEquals(array.length, buffer.remaining());
	}
	
	@Test
	void floatBufferSizeTest() {
		float[] array = {12.0f, 33.0f, 41.4f, 41,2f, 22.1f, 13.0f, 13.1f, 41.0f, 11.1f};
		FloatBuffer buffer = BufferUtils.createFloatBuffer(array);
		assertEquals(array.length, buffer.remaining());
	}
	
	@Test
	void intBufferSizeTest() {
		int[] array = {12, 33, 41, 41, 22, 13, 13, 41, 11};
		IntBuffer buffer = BufferUtils.createIntBuffer(array);
		assertEquals(array.length, buffer.remaining());
	}

}
