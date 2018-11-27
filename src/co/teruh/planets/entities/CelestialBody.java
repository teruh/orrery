package co.teruh.planets.entities;

import org.joml.Vector3f;

import co.teruh.planets.graphics.Mesh;

public class CelestialBody {
	
	// Physical body attributes
	private String name;
	
	// Program attributes
	private Mesh mesh;
	private float scale;
	private Vector3f position;
	private Vector3f rotation;
	
	public CelestialBody(String name, Mesh mesh) {
		this.name = name;
		this.mesh = mesh;
		scale = 1;
		position = new Vector3f(0, 0, 0);
		rotation = new Vector3f(0, 0, 0);
		
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}
}
