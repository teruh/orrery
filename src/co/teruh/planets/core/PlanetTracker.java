package co.teruh.planets.core;

import static org.lwjgl.opengl.GL11.*;

import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.simulation.SolarSystem;
import co.teruh.planets.utils.Timer;

public class PlanetTracker implements Runnable {

	private int targetFPS = 75; // Ideal frames per second
	private final int TARGET_UPS = 30; // Ideal updates per second

	private boolean isRunning = false; // Flag to track is program is/should be running

	private Display display; // Used to create GLFW window
	private Timer timer; // Used for program's timestep
	private Thread thread; // Handle programming threading
	private SolarSystem solarSystem; // Handle rendering/processing of primary program level

	/**
	 * Creates a new instance of our GLFW window and renderer, starts a new thread
	 * 
	 * @param name name of the program
	 */
	public PlanetTracker(String name) {
		display = new Display(name);
		timer = new Timer();
		solarSystem = new SolarSystem();

		thread = new Thread(this, "simulation");
		thread.start();
	}

	/**
	 * Initialize game systems
	 */
	@Override
	public void run() {
		init();
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

			System.out.printf("%d FPS @ %d UPS\n", timer.getFPS(), timer.getUPS());

			// Reset timer
			timer.update();

			// If vsync is enabled, wait for vsync
			if (display.isVsync()) {
				sync();
			}

			// Check if user has sent a window close event
			if (display.isRequestedClose()) {
				isRunning = false;
			}
		}
	}

	/**
	 * Sync with target FPS
	 */
	private void sync() {
		float loop = 1f / targetFPS;
		double endTime = timer.getLastLoopTime() + loop;
		while (timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize program systems called at startup
	 */
	private void init() {
		display.init();
		solarSystem.init();
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
		solarSystem.render();
	}

	/**
	 * Handle program shutdown
	 */
	private void destroy() {
		display.destroy();
	}
}
