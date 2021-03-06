package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryUtil;

public class Mesh {

	private int vbo, vao; // Vertex Buffer Object and Vertex Array Objects
	private int positionVBO, indexVBO; // Position and indices of the VBO
	private int vertexCount; // number of vertices to be rendered

	/**
	 * 
	 * @param vertices
	 * @param indices
	 */
	public Mesh(float[] vertices, int[] indices) {
		FloatBuffer positionBuffer = null;
		IntBuffer indexBuffer = null;

		try {
			vertexCount = indices.length;

			vao = glGenVertexArrays();
			glBindVertexArray(vao);

			positionVBO = glGenBuffers();
			positionBuffer = MemoryUtil.memAllocFloat(vertices.length);
			positionBuffer.put(vertices).flip();
			glBindBuffer(GL_ARRAY_BUFFER, positionVBO);
			glBufferData(GL_ARRAY_BUFFER, positionBuffer, GL_STATIC_DRAW);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

			indexVBO = glGenBuffers();
			indexBuffer = MemoryUtil.memAllocInt(indices.length);
			indexBuffer.put(indices).flip();
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

			glBindBuffer(GL_ARRAY_BUFFER, 0);
			glBindVertexArray(0);
		} finally {
			if (positionBuffer != null) {
				MemoryUtil.memFree(positionBuffer);
			}

			if (indexBuffer != null) {
				MemoryUtil.memFree(indexBuffer);
			}
		}
	}

	public void draw() {
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}

	public void bind() {
		glBindVertexArray(vao);
	}

	public void unbind() {
		glBindVertexArray(0);
	}

	public int getVBO() {
		return vbo;
	}

	public int getVAO() {
		return vao;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
