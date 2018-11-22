package co.teruh.planets.simulation;

import co.teruh.planets.graphics.Mesh;
import co.teruh.planets.graphics.Shader;

public class SolarSystem {
	
	private Shader testShader;
	private Mesh testMesh;
	
	public void init() {
		testShader = new Shader("vertex.vert", "fragment.frag");
		
		float[] vertices = new float[] { -0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f, 0.0f, };
		int[] indices = new int[] { 0, 1, 2, 0, 2, 3 };
		float[] textureCoords = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
			};
		
		testMesh = new Mesh(vertices, indices, textureCoords);
	}
	
	public void render() {
		testShader.enable();
		testMesh.render();
		testShader.disable();
	}

}
