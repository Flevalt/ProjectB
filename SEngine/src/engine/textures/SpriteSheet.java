package engine.textures;

import java.awt.image.BufferedImage;

import engine.utils.FileUtils;

public class SpriteSheet {
	
	private Texture[][] sprites;
	
	public SpriteSheet(String path, int width, int height) {
		BufferedImage image = FileUtils.loadImage(path);
		/* 
		 * Creating an multidimensional array where the first dimension
		 * elements represent the rows and the second dimension the columns
		 * 
		 * [1][1] = 
		 *  _____________
		 * |      |      |
		 * |______|______|
		 * |	  |XXXXXX|
		 * |______|XXXXXX|
		 * |	  |	     |
		 * |______|______|
		 * 
		 * 
		 */
		int rows = image.getWidth() / width;
		int columns = image.getHeight() / height;
		sprites = new Texture[rows][columns];
		splitSpriteSheet(image, width, height, rows, columns);
	}
	
	private void splitSpriteSheet(BufferedImage image, int width,
			int height, int rows, int columns) {
		int positionX = 0;
		int positionY = 0;
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				sprites[r][c] = new Texture(image.getSubimage(positionX, positionY, width, height));
				positionY += height;
			}
			positionY = 0;
			positionX += width;
		}
	}
	
	public Texture getTexture(int x, int y) {
		return sprites[x][y];
	}
}