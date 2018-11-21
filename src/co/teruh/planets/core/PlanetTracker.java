package co.teruh.planets.core;

import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Render;

public class PlanetTracker implements Runnable {

	private Display display;
	private Render render;
	private Thread thread;
	private Mesh mesh;

	public PlanetTracker(String name) {
		display = new Display(name);
		render = new Render();

		thread = new Thread(this, name);
		thread.start();
	}

	@Override
	public void run() {
		try {
			display.init();
			render.init();

			float[] position = new float[] { -0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f,
					0.0f, };
			int[] indices = new int[] { 0, 1, 3, 3, 1, 2, };

			mesh = new Mesh(position, indices);

			loop();

			display.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loop() {
		while (!display.isRequestedClose()) {
			display.update();
			render.render(mesh);
		}
	}
}
