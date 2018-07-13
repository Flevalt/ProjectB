package projectS.model.Map;

//DiamondSquare Utility Klasse
public class HexMapGenerator {

	public HexMapGenerator() {
	}

	public int[][] getDiamondSquare() {
		DiamondSquareAlgorithm md = new DiamondSquareAlgorithm();
		return md.getMap();
	}
}
