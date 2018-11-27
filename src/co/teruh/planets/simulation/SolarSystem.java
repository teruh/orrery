package co.teruh.planets.simulation;

import org.joml.Matrix4f;
import co.teruh.planets.entities.Star;
import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Shader;

public class SolarSystem extends Universe {

	private Shader testShader;
	private Mesh testMesh;
	private Matrix4f projectionMatrix;
	
	// test
	private Star star;

	private static final float FOV = (float) Math.toRadians(60.0f);
	private static final float Z_NEAR = 0.01f;
	private static final float Z_FAR = 1000.0f;

	public void init(Display display) {
		testShader = new Shader("vertex.vert", "fragment.frag");

		float aspectRatio = (float) display.getWidth() / display.getHeight();
		projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
		testShader.getUniform("projectionMatrix");
		testShader.getUniform("worldMatrix");

		float[] vertices = new float[] {
				// VO
				-0.5f, 0.5f, 0.5f,
				// V1
				-0.5f, -0.5f, 0.5f,
				// V2
				0.5f, -0.5f, 0.5f,
				// V3
				0.5f, 0.5f, 0.5f,
				// V4
				-0.5f, 0.5f, -0.5f,
				// V5
				0.5f, 0.5f, -0.5f,
				// V6
				-0.5f, -0.5f, -0.5f,
				// V7
				0.5f, -0.5f, -0.5f, };
		int[] indices = new int[] {
				// Front face
				0, 1, 3, 3, 1, 2,
				// Top Face
				4, 0, 3, 5, 4, 3,
				// Right face
				3, 2, 7, 5, 3, 7,
				// Left face
				6, 1, 0, 6, 0, 4,
				// Bottom face
				2, 1, 6, 2, 6, 7,
				// Back face
				7, 6, 4, 7, 4, 5, };
		float[] textureCoords = new float[] { 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f,
				0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, };

		testMesh = new Mesh(vertices, indices, textureCoords);
		star = new Star("sun", testMesh);
		star.setPosition(0, 0, -3);
	}

	public void render(Display display) {
		testShader.enable();
		
		Matrix4f projectionMatrix = super.getProjectionMatrix(FOV, display.getWidth(), display.getHeight(), Z_NEAR, Z_FAR);
		testShader.setUniformMatrix4f("projectionMatrix", projectionMatrix);
		
		Matrix4f worldMatrix = super.getWorldMatrix(star.getPosition(), star.getRotation(), star.getScale());
		testShader.setUniformMatrix4f("worldMatrix", worldMatrix);
	
		star.getMesh().render();
		
		testShader.disable();
	}
	
	public void update() {
		float rotation = star.getRotation().x + 1.5f;
		if (rotation > 360) {
			rotation = 0;
		}
		
		star.setRotation(rotation, rotation, rotation);
	}

	public void registerBodies() {
	}

}
