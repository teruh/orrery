package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL20.*;

import co.teruh.planets.utils.ResourceLoader;

public class Shader {
	
	private int id;
	private int vertexID;
	private int fragmentID;
	
	public Shader() throws Exception {
		id = glCreateProgram();
		
		if (id == 0) {
			throw new Exception("ERROR: COULD NOT CREATE SHADER PROGRAM!");
		}
	}
	
	public void createVertex(String vsPath) throws Exception {
		vertexID = createShader(ResourceLoader.readFile(vsPath), GL_VERTEX_SHADER);
	}
	
	public void createFragment(String fsPath) throws Exception {
		fragmentID = createShader(ResourceLoader.readFile(fsPath), GL_FRAGMENT_SHADER);
	}

	public int createShader(String path, int type) throws Exception {
		int shader = glCreateShader(type);
		
		if (shader == 0) {
			throw new Exception("ERROR: COULD NOT CREATE " + type + " SHADER!");
		}
		
		glShaderSource(shader, path);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT COMPILE SHADER. " + glGetShaderInfoLog(shader));
		}
		
		glAttachShader(id, shader);
		
		return shader;
	}
	
	public void link() throws Exception {
		glLinkProgram(id);
		if(glGetProgrami(id, GL_LINK_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT LINK SHADER. " + glGetShaderInfoLog(id));
		}
		
		if (vertexID != 0) {
			glDetachShader(id, vertexID);	
		}
		
		if(fragmentID != 0) {
			glDetachShader(id, fragmentID);	
		}
		
		glValidateProgram(id);
		if(glGetProgrami(id, GL_VALIDATE_STATUS) == 0) {
			throw new Exception("ERROR: COULD NOT VALIDATE SHADER. " + glGetShaderInfoLog(id));
		}
	}
	
	public void bind() {
		glUseProgram(id);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public void dispose() {
		unbind();
	}
}
