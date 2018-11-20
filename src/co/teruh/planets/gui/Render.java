package co.teruh.planets.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

public class Render {
	public void init() {
		GL.createCapabilities();

		glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
	}

	public void update() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
}
