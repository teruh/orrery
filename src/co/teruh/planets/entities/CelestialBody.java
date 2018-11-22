package co.teruh.planets.entities;

public class CelestialBody {
	
	// Physical body attributes
	private String name;
	private double diameter;
	
	// Program attributes
	private float lightValue;
	
	public CelestialBody(String name, double diameter) {
		this.name = name;
		this.diameter = diameter;
	}
}
