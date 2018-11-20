package co.teruh.planets.gui;

import static org.lwjgl.opengl.GL11.*;

public class Render {
	
	private Shader shader;
	
	public void init() {
		shader = new Shader();
		shader.createVertex("res/vertex.vs");
		shader.createFragment("res/fragment.vs");
		shader.link();
	}

	public void update() {
		glClear(GL_COLOR_BUFFER_BIT);
	}
}
