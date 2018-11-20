package co.teruh.planets.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Display {
	
	private long id;
	private String title;
	
	public long getWindow() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public Display(String title) {
		this.title = title;
		init();
	}
	
	public void init() {
		glfwInit();
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		id = glfwCreateWindow(300, 300, getTitle(), NULL, NULL);
		
		glfwMakeContextCurrent(id);
		
		glfwShowWindow(id);
	}
	
	public void destroy() {
		glfwTerminate();
	}
}
