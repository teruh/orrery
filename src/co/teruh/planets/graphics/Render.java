package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Render {

	private Shader shader; // Shader program

	/**
	 * Initialize core rendering pipeline
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		// Create shader program and load GLSL shaders
		shader = new Shader();
		shader.createVertex("vertex.vs");
		shader.createFragment("fragment.fs");
		shader.link();
	}

	/**
	 * Draw/render stuff
	 * 
	 * @param mesh Mesh object to render
	 */
	public void render(Mesh mesh) {
		// Clear the frame buffer
		clear();

		// Bind the shader program
		shader.bind();

		glBindVertexArray(mesh.getVAO());
		glEnableVertexAttribArray(0);

		// Draw mesh
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glBindVertexArray(0);

		// Unbind shader program after drawing
		shader.unbind();
	}

	/**
	 * Clear frame buffers so we can update every frame
	 */
	private void clear() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
	}
}
