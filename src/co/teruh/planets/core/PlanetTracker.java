package co.teruh.planets.core;

import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Render;

public class PlanetTracker implements Runnable {

	private Display display; // Used to create GLFW window
	private Render render; // Used to handle graphical rendering
	private Mesh mesh; // Used to handle meshes
	private Thread thread; // Used for programming threading

	/**
	 * Creates a new instance of our GLFW window and renderer, starts a new thread
	 * 
	 * @param name name of the program
	 */
	public PlanetTracker(String name) {
		display = new Display(name);
		render = new Render();

		thread = new Thread(this, name);
		thread.start();
	}

	/**
	 * Try to initialize program systems
	 */
	@Override
	public void run() {
		try {
			display.init();
			render.init();

			// temporary, just for testing mesh rendering
			float[] position = new float[] { -0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f,
					0.0f, };
			int[] indices = new int[] { 0, 1, 2, 0, 2, 3 };

			mesh = new Mesh(position, indices);

			loop();

			display.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main program logic run until user clicks the exit button
	 */
	public void loop() {
		while (!display.isRequestedClose()) {
			display.update();
			render.render(mesh);
		}
	}
}
