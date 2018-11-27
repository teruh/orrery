package co.teruh.planets.simulation;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Universe {

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Matrix4f worldMatrix;

	public Universe() {
		projectionMatrix = new Matrix4f();
		worldMatrix = new Matrix4f();
	}

	public Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
		float aspectRatio = width / height;
		projectionMatrix.identity();
		projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
		return projectionMatrix;
	}

	public Matrix4f getWorldMatrix(Vector3f offset, Vector3f rotation, float scale) {
		worldMatrix.identity().translate(offset).rotateX((float) Math.toRadians(rotation.x))
				.rotateY((float) Math.toRadians(rotation.y)).rotateZ((float) Math.toRadians(rotation.z)).scale(scale);
		return worldMatrix;
	}

}
