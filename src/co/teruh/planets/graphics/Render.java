package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import co.teruh.planets.utils.ResourceLoader;

public class Render {
	
	private Shader shader;;
	
	public void init() throws Exception {
		shader = new Shader();
		shader.createVertex("vertex.vs");
		shader.createFragment("fragment.fs");
		shader.link();
	}
	
	public void render(Mesh mesh) {
		clear();
		
		shader.bind();
		
		glBindVertexArray(mesh.getVAO());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
	    shader.unbind();
	}

	private void clear() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
	}
}
