package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

public class Display {

	private long id; // GLFW-required window handle

	private String title; // Window caption

	private int width = 1280; // Width of the window. TODO: make automatic (half the screen)
	private int height = 720; // Height of the window. TODO: make automatic (half the screen)

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
		if (!glfwInit()) {
			throw new RuntimeException("Failed to initialize GLFW.");
		}

		// Set window attributes
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// Create the new window
		id = glfwCreateWindow(width, height, getTitle(), NULL, NULL);
		if (id == NULL) {
			throw new RuntimeException("Failed to initialize GLFW window.");
		}

		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer width = stack.mallocInt(1);
			IntBuffer height = stack.mallocInt(1);

			glfwGetWindowSize(id, width, height);

			// Get primary monitor resolution and center window on screen
			GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(id, ((vidMode.width() - width.get(0)) / 2), ((vidMode.height() - height.get(0)) / 2));
		}

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
	 * @return current window height
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
