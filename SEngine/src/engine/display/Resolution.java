package engine.display;

public enum Resolution {
	/**
	 * 800 x 600
	 */
	SVGA(800, 600),
	/**
	 * 1024 x 600
	 */
	WSVGA(1024, 600),
	/**
	 * 1024 x 768
	 */
	XGA(1024, 768),
	/**
	 * 1280 x 720
	 */
	WXGA(1280, 720),
	/**
	 * 1360 x 768
	 */
	HD(1360, 768),
	/**
	 * 1440 x 900
	 */
	WXGAPLUS(1440, 900),
	/**
	 * 1920 x 1080
	 */
	FHD(1920, 1080),
	/**
	 * 2560 x 1440
	 */
	WQHD(2560, 1440),
	/**
	 * 3840 x 2160
	 */
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
