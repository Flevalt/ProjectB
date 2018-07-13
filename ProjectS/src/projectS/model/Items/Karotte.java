package projectS.model.Items;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import projectS.creatures.Player;
import projectS.utilityKlassen.VerderbTimer;

// Klasse für Karotte

public class Karotte extends Item implements FoodInterface {
	
	// wieviel Exp der Stat des Spielers durchs Essen bekommt.
	private int strExp = 0;
	private int conExp = 0;
	private int agiExp = 0;
	private int intExp = 0;
	private int perExp = 1;
	private int chaExp = 0;
	private int psyExp = 0;
	private int EssenKategorie = 0; //Zu welcher Kategorie von Essen die Nahrung gehört.
	//0 = Grünzeug, 1 = Jagdfleisch, 2 = Wurst, 3 = Gebäck, 4 = ? etc.
	
	private ScheduledExecutorService executor;
	
	//Konstruktor
	public Karotte(BufferedImage image) throws InterruptedException{
		//Parameter: Id, itemType, height, width, inventory, position, image
		//ruft Konstruktor von Oberklasse Item auf und weißt die Parameter 
		//der Karotte darin zu.
		super(1, 1, 1, 1, 0, 0, image);
		Timer timer = new Timer();
		timer.schedule(new VerderbTimer(this), 0, 5000);
	};
	
	/**
	 * 
	 * eatFood gibt Spieler exp wenn er diese Nahrung isst.
	 * 
	 * @param Player
	 * 			Der Spieler, der das Essen isst.
	 */
	public void eatFood(Player Spieler){
		Spieler.gainFoodExp(strExp, conExp, agiExp, intExp, perExp, chaExp, psyExp);
	}
	
	//Setter für Haltbarkeit.
	public void setHaltbarkeit(int haltbarkeit){
		Haltbarkeit = haltbarkeit;
	}
	
}
