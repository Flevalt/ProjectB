package engine.display;

public enum Resolution {
	/**
	 * 800 x 600
	 * 4:3
	 */
	SVGA(800, 600, 4, 3),
	/**
	 * 1024 x 768
	 * 4:3
	 */
	XGA(1024, 768, 4, 3),
	/**
	 * 1280 x 720
	 * 16:9
	 */
	HD(1280, 720, 16, 9),
	/**
	 * 1360 x 768
	 * 16:10
	 */
	HDPLUS(1360, 768, 16, 10),
	/**
	 * 1440 x 900
	 * 16:10
	 */
	WXGAPLUS(1440, 900, 16, 10),
	/**
	 * 1920 x 1080
	 * 16:10
	 */
	FHD(1920, 1080, 16, 9),
	/**
	 * 2560 x 1440
	 * 16:10
	 */
	WQHD(2560, 1440,  16, 9),
	/**
	 * 3840 x 2160
	 * 16:10
	 */
	UHD(3840, 2160,  16, 9);
	
	private int width;
	private int height;
	private int aspect1;
	private int aspect2;
	
	private Resolution(int width, int height,int aspect1, int aspect2){
		this.width = width;
		this.height = height;
		this.aspect1 = aspect1;
		this.aspect2 = aspect2;
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public int aspect1() {
		return aspect1;
	}
	
	public int aspect2() {
		return aspect2;
	}
}
