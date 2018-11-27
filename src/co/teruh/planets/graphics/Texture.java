package co.teruh.planets.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

public class Texture {

	private int id; // Texture handle
	
	private int width, height; // Texture dimensions

	/**
	 * Create a new texture
	 */
	public Texture() {
		id = glGenTextures();
	}

	/**
	 * Load and create a new texture
	 * 
	 * @param texPath path to texture
	 */
	public Texture(String texPath) {
		loadTexture(texPath);
	}

	/**
	 * Bind texture to program
	 */
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	/**
	 * Load texture from file
	 * 
	 * @param texPath path to texture image
	 * @return id of the texture
	 */
	public int loadTexture(String texPath) {
		ByteBuffer img;
		int width, height;

		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer widthBuffer = stack.mallocInt(1);
			IntBuffer heightBuffer = stack.mallocInt(1);
			IntBuffer channelsBuffer = stack.mallocInt(1);

			stbi_set_flip_vertically_on_load(true);
			img = stbi_load(texPath, widthBuffer, heightBuffer, channelsBuffer, 4);
			if (img == null) {
				System.err.printf("Could not load texture! \n%s", stbi_failure_reason());
			}

			width = widthBuffer.get();
			height = heightBuffer.get();

			return createTexture(width, height, img);
		}
	}

	/**
	 * Create a new GL texture
	 * 
	 * @param width   width of the texture
	 * @param height  height of the texture
	 * @param imgData texture buffer
	 * @return id of the texture
	 */
	public int createTexture(int width, int height, ByteBuffer imgData) {
		// Create a new texture instance
		Texture texture = new Texture();
		texture.setWidth(width);
		texture.setHeight(height);

		texture.bind();
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, imgData);

		return id;
	}

	public int getID() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
