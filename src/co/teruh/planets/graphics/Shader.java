package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import co.teruh.planets.utils.GLSLParser;

public class Shader {

	private final int ID; // Shader program handle
	
	private Map<String, Integer> locCache;

	/**
	 * Create new shaders
	 * 
	 * @param vertex
	 * @param fragment
	 */
	public Shader(String vertex, String fragment) {
		ID = loadShader(vertex, fragment);
		locCache = new HashMap<>();
	}

	/**
	 * Load and create new shaders from source
	 * 
	 * @param vPath
	 * @param fPath
	 * @return shader program ID
	 */
	public int loadShader(String vPath, String fPath) {
		String vertexSource = GLSLParser.parseGLSL(vPath);
		String fragmentSource = GLSLParser.parseGLSL(fPath);
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
		int program = glCreateProgram(); // Shader program ID
		int vertexID = glCreateShader(GL_VERTEX_SHADER); // Vertex shader handle
		int fragmentID = glCreateShader(GL_FRAGMENT_SHADER); // Fragment shader handle

		// Get GLSL source
		glShaderSource(vertexID, vertex);
		glShaderSource(fragmentID, fragment);

		// Compile vertex shader source
		glCompileShader(vertexID);
		if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.printf("Could not compile Vertex Shader! \n%s", glGetShaderInfoLog(vertexID));
		}

		// Compile fragment shader source
		glCompileShader(fragmentID);
		if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.printf("Could not compile Fragment Shader! \n%s", glGetShaderInfoLog(fragmentID));
		}

		// Attach shader to shader program
		glAttachShader(program, vertexID);
		glAttachShader(program, fragmentID);

		// Link shaders to our program
		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
			System.err.printf("Could not link shader! \n%s", glGetShaderInfoLog(program));
		}

		// Validate our program after linking
		glValidateProgram(program);
		if (glGetProgrami(program, GL_VALIDATE_STATUS) == GL_FALSE) {
			System.err.printf("Could not validate shader program! \n%s", glGetShaderInfoLog(program));
		}

		// Delete shaders once processed
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);

		return program;
	}
	
	public int getUniform(String name) {
		if (locCache.containsKey(name)) {
			return locCache.get(name);
		}
		int result = glGetUniformLocation(ID, name);
		if (result < 0) {
			System.err.printf("Could not find %s uniform.\n", name);
		} else {
			locCache.put(name, result);
		}
		return result;
	}
	
	public void setUniform1i(String name, int x) {
		glUniform1i(getUniform(name), x);
	}
	
	public void setUniform1f(String name, float x) {
		glUniform1f(getUniform(name), x);
	}
	
	public void setUniform2f(String name, float x, float y) {
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniformMatrix4f(String name, Matrix4f matrix) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            matrix.get(fb);
            glUniformMatrix4fv(locCache.get(name), false, fb);
        }
	}

	/**
	 * Bind to shader program
	 */
	public void enable() {
		glUseProgram(ID);
	}

	/**
	 * Unbind from shader program
	 */
	public void disable() {
		glUseProgram(0);
	}
}
