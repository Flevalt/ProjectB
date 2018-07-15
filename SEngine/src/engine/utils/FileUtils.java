package engine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.display.DisplayManager;
import engine.graphics.Model;
import engine.objects.TexturedModel;
import engine.textures.Texture;

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
	
	public static TexturedModel loadSprite(String path) {
		BufferedImage image = loadImage(path);	
		Texture texture = new Texture(image);
		
		float modelWidth = ((float)image.getWidth())/((float)DisplayManager.resolution.width());
		float modelHeight = ((float)image.getHeight())/((float)DisplayManager.resolution.height());
		
		float scaling = 2.5f;
		
		float[] vertices = new float[] {
			//Top left point
			-modelWidth * DisplayManager.resolution.aspect1() / scaling, modelHeight * DisplayManager.resolution.aspect2() / scaling, 0f,
			//Bottom left point
			-modelWidth * DisplayManager.resolution.aspect1() / scaling, -modelHeight * DisplayManager.resolution.aspect2() / scaling, 0f,
			//Bottom right point
			modelWidth * DisplayManager.resolution.aspect1() / scaling, -modelHeight * DisplayManager.resolution.aspect2() / scaling, 0f,
			//Top right point
			modelWidth * DisplayManager.resolution.aspect1() / scaling, modelHeight * DisplayManager.resolution.aspect2() / scaling, 0f
			
		};
		
		byte[] index = new byte[] {
				0, 1, 2,
				0, 2, 3
		};
		
		float[] tex = new float[] {
				0f, 0f,
				0f, 1f,
				1f, 1f,
				1f, 0f
		};		
		
		Model model = new Model(vertices, index, tex);
		TexturedModel texturedModel = new TexturedModel(texture, model);
		return texturedModel;
	}
	
}
