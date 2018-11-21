package co.teruh.planets.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader {

	public static String readFile(String file) {
		StringBuilder string = new StringBuilder();
		BufferedReader bufferedReader;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(new File("./res/"+ file)));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				string.append(line);
				string.append("\n");
			}
			
			bufferedReader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return string.toString();
	}

}
