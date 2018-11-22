package co.teruh.planets.simulation;

import org.joml.Matrix4f;
import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Shader;

public class SolarSystem {

	private Shader testShader;
	private Mesh testMesh;
	private Matrix4f projectionMatrix;

	private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;

	public void init(Display display) {
		testShader = new Shader("vertex.vert", "fragment.frag");
		
		float aspectRatio = (float) display.getWidth() / display.getHeight();
		projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
		testShader.getUniform("projectionMatrix");

		float[] vertices = new float[] { -0.5f, 0.5f, -1.05f, -0.5f, -0.5f, -1.05f, 0.5f, -0.5f, -1.05f, 0.5f, 0.5f,
				-1.05f, };
		int[] indices = new int[] { 0, 1, 3, 3, 1, 2, };
		float[] textureCoords = new float[] { 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, };

		testMesh = new Mesh(vertices, indices, textureCoords);
	}

	public void render() {
		testShader.enable();
		testShader.setUniformMatrix4f("projectionMatrix", projectionMatrix);
		testMesh.render();
		testShader.disable();
	}

	public void registerBodies() {
	}

}
