package co.teruh.planets.graphics;

import org.joml.Vector3f;

public class Camera {

	private final Vector3f position, rotation; // Camera telemetry

	/**
	 * Create new camera at origin
	 */
	public Camera() {
		position = new Vector3f(0, 0, 0);
		rotation = new Vector3f(0, 0, 0);
	}

	/**
	 * Create new camera a passed location
	 * 
	 * @param position position of the camera
	 * @param rotation rotation of the camera
	 */
	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y, float z) {
		position.x = x;
		position.y = y;
		position.z = z;
	}
	
	public void updatePosition(float xOffset, float yOffset, float zOffset) {
		if (zOffset != 0) {
			position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * zOffset;
			position.z += (float) Math.cos(Math.toRadians(rotation.y)) * zOffset;
		}
		if (xOffset != 0) {
			position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * xOffset;
			position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * xOffset;
		}
		position.y += yOffset;
	}

	public Vector3f getRotation() {
		return rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		rotation.x = x;
		rotation.y = y;
		rotation.z = z;
	}

	public void updateRotation(float xOffset, float yOffset, float zOffset) {
		rotation.x += xOffset;
		rotation.y += yOffset;
		rotation.z += zOffset;
	}
}