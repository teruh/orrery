package co.teruh.planets.gui;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
	
	private int id;
	private int vertexID;
	private int fragmentID;
	
	public Shader() {
		id = glCreateProgram();
	}
	
	public void createVertex(String vsPath) {
		vertexID = createShader(vsPath, GL_VERTEX_SHADER);
	}
	
	public void createFragment(String fsPath) {
		fragmentID = createShader(fsPath, GL_FRAGMENT_SHADER);
	}

	public int createShader(String path, int type) {
		int shader = glCreateShader(type);
		glShaderSource(shader, path);
		glCompileShader(shader);
		glAttachShader(id, shader);
		return shader;
	}
	
	public void link() {
		glLinkProgram(id);
		
		glDetachShader(id, vertexID);
		glDetachShader(id, fragmentID);
		
		glValidateProgram(id);
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
