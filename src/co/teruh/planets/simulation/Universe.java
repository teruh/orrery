package co.teruh.planets.simulation;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import co.teruh.planets.entities.CelestialBody;
import co.teruh.planets.graphics.Camera;

public class Universe {

	private Matrix4f projectionMatrix;
	private Matrix4f worldMatrix;
	private Matrix4f modelViewMatrix;
	private Matrix4f viewMatrix;

	public Universe() {
		projectionMatrix = new Matrix4f();
		worldMatrix = new Matrix4f();
		modelViewMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
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
	
	public Matrix4f getModelViewMatrix(CelestialBody body, Matrix4f viewMatrix) {
		Vector3f rotation = body.getRotation();
		modelViewMatrix.identity().translate(body.getPosition()).rotateX((float) Math.toRadians(-rotation.x))
				.rotateY((float) Math.toRadians(-rotation.y)).rotateZ((float) Math.toRadians(-rotation.z))
				.scale(body.getScale());
		Matrix4f view = new Matrix4f(viewMatrix);
		return view.mul(modelViewMatrix);
	}

	public Matrix4f getViewMatrix(Camera camera) {
		Vector3f cameraPos = camera.getPosition();
		Vector3f cameraRot = camera.getRotation();
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(cameraRot.x), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(cameraRot.y), new Vector3f(0, 1, 0));
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		return viewMatrix;
	}

}
