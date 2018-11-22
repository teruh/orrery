package co.teruh.planets.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {
	
	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer intBuffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		intBuffer.put(array).flip();
		return intBuffer;
	}
	
	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer floatBuffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		floatBuffer.put(array).flip();
		return floatBuffer;
	}

}
