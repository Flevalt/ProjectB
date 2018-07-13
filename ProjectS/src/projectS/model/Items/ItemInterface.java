package projectS.model.Items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * ItemInterface ist ein {@link Interface} für Items.
 * Es werden nur die Signaturen der Methoden erfasst, aber keine Implementierung.
 * 
 * Methode = Eine Funktion einer Klasse, z.B. speichereBild() oder hebItemAuf().
 * 
 * Signatur = der oberste Teil einer Klasse oder Methode, 
 * z.B. "public void speichereBild()". 
 * Der Inhalt der Klasse/Methode gehört nicht zur Signatur.
 * 
 * Interface = Eine abstrakte Klasse, das heißt eine Klasse 
 * in der NUR Signaturen solcher Methoden definiert werden,
 * die für alle Klassen dieser Sorte umzusetzen sind.
 * z.B. soll jedes Item aufhebbar und auf den Boden dropbar sein. Ein Item-Interface
 * sollte also Signaturen für diese Sorte von Klassen definieren.
 * Beispiel 2: Nicht jedes Item ist Nahrung. 
 * Ein FoodInterface wird verwendet, um Funktionen zu definieren, 
 * die nur spezifisch für Nahrungsgegenstände sind, damit
 * diese Items ihren eigenen Charakter bekommen und sich von anderen Itemsorten 
 * unterscheiden. 
 * 
 *
 */
public interface ItemInterface {
	
	//Funktion: Wählt das entsprechende Bild für das Item aus und speichert es.
	 public void speichereBild(String ItemName); 
	 
	 public int hebItemAuf();
	 
	 public int lassItemFallen(int SpielerKoordinaten);
	 
	 public void verdirbNahrung();
	 
}
