package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

public class Display {

	private long id; // GLFW-required window handle

	private String title; // Window caption

	private int width = 800; // Width of the window. TODO: make automatic
	private int height = 600; // Height of the window. TODO: make automatic

	private boolean vsync; // Vsync functionality

	/**
	 *
	 * @param title window title
	 */
	public Display(String title) {
		this.title = title;
	}

	/**
	 * Initialize GLFW window and create OpenGL capabilities
	 */
	public void init() {
		// Initialize all GLFW systems
		glfwInit();

		// Set window attributes
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// Create the new window
		id = glfwCreateWindow(width, height, getTitle(), NULL, NULL);

		glfwMakeContextCurrent(id);

		glfwShowWindow(id);

		if (isVsync()) {
			glfwSwapInterval(1);
		}

		// Create OpenGL capabilities in current thread
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	/**
	 * GLFW event updates
	 */
	public void update() {
		glfwSwapBuffers(id);
		glfwPollEvents();
	}

	/**
	 * Stop all GLFW systems
	 */
	public void destroy() {
		glfwTerminate();
	}

	/**
	 * 
	 * @return if user has requested to close window
	 */
	public boolean isRequestedClose() {
		return glfwWindowShouldClose(id);
	}

	/**
	 * 
	 * @return current title of the window
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return current window width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @param width new window width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return height of the window
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param height new window height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @return if vsync is enabled
	 */
	public boolean isVsync() {
		return vsync;
	}

	/**
	 * 
	 * @param vsync if vsync should be enabled
	 */
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}
}
