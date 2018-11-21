package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL20.*;

import co.teruh.planets.utils.ResourceLoader;

public class Shader {

	private int id; // Shader program handle
	private int vertexID; // Vertex shader handle
	private int fragmentID; // Fragment shader handle

	/**
	 * Create new Shader Program
	 * 
	 * @throws Exception
	 */
	public Shader() throws Exception {
		// Set our handle equal to new shader program
		id = glCreateProgram();
		if (id == 0) {
			throw new Exception("ERROR: COULD NOT CREATE SHADER PROGRAM!");
		}
	}

	/**
	 * Create new vertex shader from .vs code
	 * 
	 * @param vsPath path to the vertex shader
	 * @throws Exception
	 */
	public void createVertex(String vsPath) throws Exception {
		vertexID = createShader(ResourceLoader.readGLSLFile(vsPath), GL_VERTEX_SHADER);
	}

	/**
	 * Create new vertex shader from .fs code
	 * 
	 * @param fsPath path to the fragment shader
	 * @throws Exception
	 */
	public void createFragment(String fsPath) throws Exception {
		fragmentID = createShader(ResourceLoader.readGLSLFile(fsPath), GL_FRAGMENT_SHADER);
	}

	/**
	 * Create a new shader
	 * 
	 * @param glsl GLSL shader code
	 * @param type type of shader (vertex or fragment)
	 * @return shader handle
	 * @throws Exception
	 */
	public int createShader(String glsl, int type) throws Exception {
		// Create new shader in shader handle
		int shader = glCreateShader(type);
		if (shader == 0) {
			throw new Exception("ERROR: COULD NOT CREATE " + type + " SHADER!");
		}

		// Get and compile GLSL source
		glShaderSource(shader, glsl);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT COMPILE SHADER. " + glGetShaderInfoLog(shader));
		}

		// Attach shader to our program
		glAttachShader(id, shader);

		return shader;
	}

	/**
	 * Link generated shaders to our shader program
	 * 
	 * @throws Exception
	 */
	public void link() throws Exception {
		glLinkProgram(id);
		if (glGetProgrami(id, GL_LINK_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT LINK SHADER. " + glGetShaderInfoLog(id));
		}

		if (vertexID != 0) {
			glDetachShader(id, vertexID);
		}

		if (fragmentID != 0) {
			glDetachShader(id, fragmentID);
		}

		glValidateProgram(id);
		if (glGetProgrami(id, GL_VALIDATE_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT VALIDATE SHADER. " + glGetShaderInfoLog(id));
		}
	}

	public void bind() {
		glUseProgram(id);
	}

	public void unbind() {
		glUseProgram(0);
	}
}
