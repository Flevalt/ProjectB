package projectS.model.Items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item implements ItemInterface {

	protected int itemID; //identifiziert das Item eindeutig.
	protected int itemType; //definiert Kategorie des Items. 1 = Food, 2 = Equip, 3 = craft-material, 4 = consumable, 5 = Other
	protected int itemHeight; //Höhe des Items im Inventar z.B. 2 slots Höhe.
	protected int itemWidth; //Breite des Items im Inventar, z.B. 3 slots Breite.
	protected int itemInventory; //In welcher Sorte von Inventar sich das Item befindet. z.B. 1 = Inventar, 2 = OreSmelter etc. Muss evtl. später von Int zum Datentypen Inventory geändert werden.
	protected float itemPosition; //Wo sich das Item gerade auf der Karte befindet, wenn es auf der Weltkarte rumliegt.
	protected BufferedImage itemImage = null;
	File file = new File("Bilder/test.jpg"); 
	protected int Haltbarkeit = 604800; //Die Zeit (in Sekunden), die das Essen aushält, bevor es verdirbt.
	//z.B. 60 = 1 Minute, 600 = 10 minuten, 3600 = 1 Stunde, 86400 = 1 Tag, 604800 = 7 Tage
	
	//Konstruktor. Jede Klasse besitzt einen oder mehr Konstruktoren.
	//Diese Methoden haben den selben Namen wie die Klasse selbst.
	//Sie erstellen konkrete Instanzen der Klasse, das heißt tatsächliche Objekte,
	//die mehrfach auftreten können. Jedes mal, wenn also ein neues Item erzeugt wird,
	//macht das ein Konstruktor.
	public Item(int itemID, int itemType, int itemHeight, int itemWidth, int itemInventory, float itemPosition, BufferedImage itemImage){
		//Die Parameter der Methode (z.B. itemPosition), welche von einer anderen Klasse 
		// hierhin übergeben werden, werden benutzt um sie dem konkreten Objekt
		// zuzuweisen. 
		// z.B. wird die konkrete Position also an den Konstruktor dieser Item Klasse
		// übergeben, deren Konstruktor weißt die Positions-Koordinaten
		// dem Objekt dann zu und erzeugt das Objekt.
		this.itemID = itemID;
		this.itemType = itemType;
		this.itemHeight = itemHeight;
		this.itemWidth = itemWidth;
		this.itemInventory = itemInventory;
		this.itemPosition = itemPosition;
		this.itemImage = itemImage;
	}
	
	
	//Leerer Konstruktor. Wird benutzt, wenn statt dem Konstruktor von Item,
	//der Konstruktor in einer Unterklasse benutzt werden soll.
	//Alternativ können aber auch mit dem Befehl super(), im Konstruktor der Unterklasse,
	//die Parameter für die Oberklasse Item zugewiesen werden.
	public Item(){}
	
	
	/**
	 * @param ItemName
	 * 			Der Name der Bilddatei, nach der gesucht werden soll.
	 */
	public void speichereBild(String ItemName){
		try {
		    this.itemImage = ImageIO.read(new File(ItemName)); //itemName wäre z.B. Karotte.jpg
		} catch (IOException e) {
		}
	}
	
	
	/**
	 * @return das Inventar, in welches das Item reingehen soll.
	 */
	public int hebItemAuf(){
		int Inventory = 0; //das Inventar, in welches das Item reingehen soll.
		
		return Inventory;
	}
	
	
	/**
	 * @return die Weltkoordinaten an dem das Item auftauchen soll, nachdem es fallen
	 * gelassen wurde.
	 */
	public int lassItemFallen(int SpielerKoordinaten){
		//Das Item soll nun an der Stelle fallen, wo sich der Spieler gerade befindet.
		int Weltkoordinaten = SpielerKoordinaten;
		
		return Weltkoordinaten;
	}
	
	//verdirbt Nahrung mit jeder Sekunde. Wird von VerderbTimer aufgerufen.
	public void verdirbNahrung(){
		 Haltbarkeit -=1;
	};
	
}
