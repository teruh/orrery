package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import co.teruh.planets.utils.Buffer;

public class Mesh {

	private int vbo, vao, ibo, tbo; // Vertex Buffer Object and Vertex Array Objects
	private int vertexCount; // number of vertices to be rendered
	
	/**
	 * 
	 * @param vertices
	 * @param indices
	 */
	public Mesh(float[] vertices, int[] indices, float[] textureCoords, Texture texture) {
		vertexCount = indices.length;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.createFloatBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Buffer.createIntBuffer(indices), GL_STATIC_DRAW);
		
		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.createFloatBuffer(textureCoords), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public void draw() {
		if (ibo > 0) {
			glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
		} else {
			glDrawArrays(GL_TRIANGLES, 0, vertexCount);
		}
	}

	public void bind() {
		glBindVertexArray(vao);
		
		if (ibo > 0) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		}
	}

	public void unbind() {
		glBindVertexArray(0);
		
		if (ibo > 0) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		}
	}
	
	public void render() {
		bind();
		draw();
	}
}