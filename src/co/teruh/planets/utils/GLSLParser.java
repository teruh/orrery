package co.teruh.planets.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GLSLParser {

	/**
	 * Turn GLSL source files into a string for compilation
	 * 
	 * @param resPath GLSL file name
	 * @return GLSL source as a string
	 */
	public static String parseGLSL(String resPath) {
		StringBuilder string = new StringBuilder();
		BufferedReader bufferedReader;

		try {
			bufferedReader = new BufferedReader(new FileReader(new File("./res/" + resPath)));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				string.append(line + "\n");
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println("Error reading GLSL source.");
			e.printStackTrace();
		}

		return string.toString();
	}
}
