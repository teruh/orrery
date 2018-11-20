package co.teruh.planets.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Display {
	private long id;
	private String title;
	private int width = 800;
	private int height = 600;

	public Display(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void init() {
		glfwInit();

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		id = glfwCreateWindow(width, height, getTitle(), NULL, NULL);

		glfwMakeContextCurrent(id);

		glfwShowWindow(id);
	}

	public void update() {
		glfwSwapBuffers(id);
		glfwPollEvents();
	}

	public void destroy() {
		glfwTerminate();
	}

	public boolean isRequestedClose() {
		return glfwWindowShouldClose(id);
	}
}
