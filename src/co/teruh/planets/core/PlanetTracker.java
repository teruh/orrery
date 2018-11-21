package co.teruh.planets.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Render;
import co.teruh.planets.utils.Timer;

public class PlanetTracker implements Runnable {

	private final int TARGET_UPS = 30; // Ideal updates per second

	private boolean isRunning = false; // Flag to track is program is/should be running

	private Display display; // Used to create GLFW window
	private Render render; // Used to handle graphical rendering
	private Mesh mesh; // Used to handle meshes
	private Timer timer; // Used for program's timestep
	private Thread thread; // Handle programming threading

	/**
	 * Creates a new instance of our GLFW window and renderer, starts a new thread
	 * 
	 * @param name name of the program
	 */
	public PlanetTracker(String name) {
		display = new Display(name);
		render = new Render();
		timer = new Timer();

		thread = new Thread(this, "simulation");
		thread.start();
	}

	/**
	 * Initialize game systems
	 */
	@Override
	public void run() {
		init();

		// TEMPORARY: SEE JAVADOC IN RENDER.JAVA
		float[] position = new float[] { -0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f, 0.0f, };
		int[] indices = new int[] { 0, 1, 2, 0, 2, 3 };
		mesh = new Mesh(position, indices);

		loop();

		destroy();
	}

	/**
	 * Main program logic run until user clicks the exit button
	 */
	private void loop() {
		float delta; // Delta time (see Timer.java)
		float accumulator = 0f; // Unsimulated/excess time
		float interval = 1f / TARGET_UPS; // How often we are updating

		// We've made it this far, program is running
		isRunning = true;
		
		while (isRunning) {
			delta = timer.getDelta();
			accumulator += delta;

			while (accumulator >= interval) {
				update();
				timer.updateUPS();
				accumulator -= interval;
			}

			// Render graphics
			render();
			timer.updateFPS();

			// Reset timer
			timer.update();

			// Check if user has sent a window close event
			if (display.isRequestedClose()) {
				isRunning = false;
			}
		}
	}

	/**
	 * Initialize program systems called at startup
	 */
	private void init() {
		display.init();
		render.init();
	}

	/**
	 * Update methods to be called by game loop
	 */
	private void update() {
		display.update();
	}

	/**
	 * OpenGL/LJGWL lib rendering
	 */
	private void render() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		render.render(mesh);
	}

	/**
	 * Handle program shutdown
	 */
	private void destroy() {
		display.destroy();
	}
}
