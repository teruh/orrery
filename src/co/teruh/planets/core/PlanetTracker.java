package co.teruh.planets.core;

import co.teruh.planets.gui.Display;
import co.teruh.planets.gui.Render;

public class PlanetTracker implements Runnable {

	private Display display;
	private Render render;

	public PlanetTracker(String name) {
		display = new Display(name);
		render = new Render();

		run();
	}

	@Override
	public void run() {
		display.init();
		render.init();

		loop();

		display.destroy();
	}

	public void loop() {
		while (!display.isRequestedClose()) {
			display.update();
			render.update();
		}
	}
}
