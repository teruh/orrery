package co.teruh.planets.simulation;

import org.joml.Matrix4f;
import co.teruh.planets.entities.Star;
import co.teruh.planets.graphics.Display;
import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Shader;
import co.teruh.planets.graphics.Texture;

public class SolarSystem extends Universe {

	private Shader testShader;
	private Mesh testMesh;
	private Matrix4f projectionMatrix;

	// test
	private Star star;
	private Texture texture;

	private static final float FOV = (float) Math.toRadians(60.0f);
	private static final float Z_NEAR = 0.01f;
	private static final float Z_FAR = 1000.0f;

	public void init(Display display) {
		testShader = new Shader("vertex.vert", "fragment.frag");
		texture = new Texture("res/bean.png");

		float aspectRatio = (float) display.getWidth() / display.getHeight();
		projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
		testShader.getUniform("projectionMatrix");
		testShader.getUniform("worldMatrix");
		testShader.getUniform("texture_sampler");

		float[] vertices = new float[] { -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
				0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f,
				0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, };
		float[] textureCoords = new float[] { 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.5f,
				0.0f, 0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.5f, 0.5f, 1.0f, 0.5f, };
		int[] indices = new int[] { 0, 1, 3, 3, 1, 2, 8, 10, 11, 9, 8, 11, 12, 13, 7, 5, 12, 7, 14, 15, 6, 4, 14, 6, 16,
				18, 19, 17, 16, 19, 4, 6, 7, 5, 4, 7, };

		testMesh = new Mesh(vertices, indices, textureCoords, texture);
		star = new Star("sun", testMesh);
		star.setPosition(0, 0, -3);
	}

	public void render(Display display) {
		testShader.enable();

		Matrix4f projectionMatrix = super.getProjectionMatrix(FOV, display.getWidth(), display.getHeight(), Z_NEAR,
				Z_FAR);
		testShader.setUniformMatrix4f("projectionMatrix", projectionMatrix);

		Matrix4f worldMatrix = super.getWorldMatrix(star.getPosition(), star.getRotation(), star.getScale());
		testShader.setUniformMatrix4f("worldMatrix", worldMatrix);

		testShader.setUniform1i("texture_sampler", 0);

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