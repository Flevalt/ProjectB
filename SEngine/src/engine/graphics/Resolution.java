package engine.graphics;

public enum Resolution {
	SVGA(800, 600),
	WSVGA(1024, 600),
	XGA(1024, 768),
	WXGA(1280, 720),
	HD(1360, 768),
	WXGAPLUS(1440, 900),
	FHD(1920, 1080),
	WQHD(2560, 1440),
	UHD(3840, 2160);
	
	private int width;
	private int height;
	
	private Resolution(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
}
