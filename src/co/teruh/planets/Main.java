package co.teruh.planets;

import co.teruh.planets.core.PlanetTracker;

/**
 * Entry-point of the program, creates a new instance of the PlanetTracker class
 * for execution.
 * 
 * @author zach clark
 *
 */
public class Main {
	public static void main(String[] args) {
		new PlanetTracker("Orrery").run();
	}
}
