package projectS.utilityKlassen;

import java.util.TimerTask;

import projectS.model.Items.Item;

public class VerderbTimer extends TimerTask {

	private Item item; //Oberklasse Item, damit jede Nahrung hier gespeichert werden kann.
	
	public VerderbTimer(Item item){
		this.item = item;
	}
	
	public void run() {
			item.verdirbNahrung();
	    }
}
