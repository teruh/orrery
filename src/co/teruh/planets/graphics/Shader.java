package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL20.*;

import co.teruh.planets.utils.ResourceLoader;

public class Shader {

	private final int ID; // Shader program handle

	/**
	 * Create new shaders
	 * 
	 * @param vertex
	 * @param fragment
	 */
	public Shader(String vertex, String fragment) {
		ID = loadShader(vertex, fragment);
	}

	/**
	 * Load and create new shaders from source
	 * 
	 * @param vPath
	 * @param fPath
	 * @return shader program ID
	 */
	public int loadShader(String vPath, String fPath) {
		String vertexSource = ResourceLoader.readGLSLFile(vPath);
		String fragmentSource = ResourceLoader.readGLSLFile(fPath);
		return createShader(vertexSource, fragmentSource);
	}

	/**
	 * Create vertex/fragment shaders, compile the GLSL source, and link/attack them
	 * to the shader program
	 * 
	 * @param vertex   vertex shader source
	 * @param fragment fragment shader source
	 * @return shader program ID
	 */
	public int createShader(String vertex, String fragment) {
		int program = glCreateProgram();
		int vertexID = glCreateShader(GL_VERTEX_SHADER);
		int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

		glShaderSource(vertexID, vertex);
		glShaderSource(fragmentID, fragment);

		glCompileShader(vertexID);
		if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.printf("Could not compile Vertex Shader! \n%s", glGetShaderInfoLog(vertexID));
		}

		glCompileShader(fragmentID);
		if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.printf("Could not compile Fragment Shader! \n%s", glGetShaderInfoLog(fragmentID));
		}

		glAttachShader(program, vertexID);
		glAttachShader(program, fragmentID);

		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
			System.err.printf("Could not link shader! \n%s", glGetShaderInfoLog(program));
		}
		
		glValidateProgram(program);
		if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
			System.err.printf("Could not validate shader program! \n%s", glGetShaderInfoLog(program));
		}
		
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);

		return program;
	}

	public void enable() {
		glUseProgram(ID);
	}

	public void disable() {
		glUseProgram(0);
	}
}
