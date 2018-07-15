package engine.textures;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import engine.utils.BufferUtils;
import engine.utils.FileUtils;

public class Texture{
	
	private int width;
	private int height;
	private int texture;
	
	public Texture(String path) {
		texture = load(path);
	}
	
	public Texture(BufferedImage image) {
		texture = createGLTexture(image);
	}
	
	private int load(String path) {	
		BufferedImage image = FileUtils.loadImage(path);
		return createGLTexture(image);
	}
	
	private int createGLTexture(BufferedImage image) {
		int[] pixels = null;
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	
		int[] data = new int[width * height];
		//Sorting the array
		for(int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}		
		int result = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, result);
		//setting the texture filter
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);//Filter method
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);//Filter method
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height,0 ,GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		return result;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getTextureId() {
		return texture;
	}
	

	
	public void delete() {
		GL11.glDeleteTextures(texture);
	}
}
