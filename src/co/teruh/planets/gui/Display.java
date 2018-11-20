package co.teruh.planets.gui;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

public class Display {
	
	private long id;
	
	private String title;
	
	private int width = 800;
	private int height = 600;
	
	private boolean vsync;

	public Display(String title) {
		this.title = title;
	}

	public void init() {
		glfwInit();

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		id = glfwCreateWindow(width, height, getTitle(), NULL, NULL);

		glfwMakeContextCurrent(id);

		glfwShowWindow(id);
		
		if (isVsync()) {
			glfwSwapInterval(1);
		}
		
		GL.createCapabilities();
		
		glClearColor(0.0f, 0.0f, 0.2f, 0.0f);
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
	
	public boolean isVsync() {
		return vsync;
	}
	
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}
}
