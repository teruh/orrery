package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * THIS IS A TEMPORARY CLASS. 
 * RENDERING WILL BE DONE IN THE RESPECTIVE "WORLD" CLASS.
 * 3D SIMULATION -> SOLARSYSTEM.RENDER3D & SOLARSYSTEM.RENDER2D.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Render {

	private Shader shader; // Shader program

	/**
	 * Initialize core rendering pipeline
	 * 
	 * @throws Exception
	 */
	public void init() {
		// Create shader program and load GLSL shaders
		shader = new Shader("vertex.vert", "fragment.frag");
	}

	/**
	 * Draw/render stuff
	 * 
	 * @param mesh Mesh object to render
	 */
	public void render(Mesh mesh) {
		// Bind the shader program
		shader.enable();

		glBindVertexArray(mesh.getVAO());
		glEnableVertexAttribArray(0);

		// Draw mesh
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glBindVertexArray(0);

		// Unbind shader program after drawing
		shader.disable();
	}
}
