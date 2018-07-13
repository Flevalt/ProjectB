package engine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {
	
	private FileUtils() {
	}
	
	/**
	 * Reads the file of a given path and returns a string with its content.
	 * @param file - Path of the file to be loaded.
	 * @return String of the file where every line is separated by a line separator.
	 */
	public static String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String buffer = "";
			while((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result.toString();
	}
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			System.err.println("Could not load file '"+ path +"'!");
			System.err.println("Reason: " + e.getMessage());
		}
		return image;
	}
	
}
