package engine.graphics;

public class Color {
	public int red;
	public int green;
	public int blue;
	public int alpha;
	
	public Color(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public static Color black() {
		return new Color(0, 0, 0, 1);
	}
	
	public static Color white() {
		return new Color(1, 1, 1, 1);
	}
	
	public static Color green() {
		return new Color(0, 1, 0, 1);
	}
	
	public static Color red() {
		return new Color(1, 0, 0, 1);
	}
	
	public static Color blue() {
		return new Color(0, 0, 1, 1);
	}
}
