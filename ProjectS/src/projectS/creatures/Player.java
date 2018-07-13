package projectS.creatures;

public class Player {

	//Ein Array mit mehreren Feldern. Jedes Feld repräsentiert einen der Statpunkte,
	//die beim Essen erhöht werden. 
	//Jedes Feld eines Arrays wird mit einer Indexzahl versehen, mit der der Feldinhalt
	//abgerufen werden kann.
	//Ein Array fängt immer mit Index 0 an. Bei 7 Stats gesamt geht das Array
	//von Index 0 bis 6.
	int[] foodExp;
	int foodExpCap; // Das derzeitige ExpMaximum, das erreicht werden muss, für ein Stat Lvlup.
	int Str; //Stärke des Spielers. Beschleunigt zerstören von Gegenständen.
	int Con; //Constitution des Spielers. Erhöht MaxHP und verringert Stamina Verbrauch.
	int Agi; //Agilität des Spielers. Erhöht Kampfgeschwindigkeit.
	int Int; //Intelligenz des Spielers. Erhöht Anzahl der Curios, die studiert werden können.
	int Per; //Perception des Spielers. Erhöht Anzahl der Items, die in Wildniss gefunden werden.
	int Cha; //Charisma des Spielers. Erhöht Anzahl NPCs die kontrolliert werden können?
	int Psy; //Psyche des Spielers. Negativer Stat. Erhöht den Wert des Kopfes beim Tod des Spielers.
	
	
	/**
	 * 
	 * gainFoodExp rechnet die Wahrscheinlichkeit für die Erhöhung jedes Stats aus
	 * und erhöht den entsprechenden Stat des Spielers dann um 1.
	 * 
	 * @param statType
	 * 			Der Stat, der in der FoodExp Leiste erhöht werden soll.
	 * @param expAmount
	 * 			Anzahl an Exp für den zu erhöhenden Stat.
	 */
	public void gainFoodExp(int strExp, int conExp, int agiExp, int intExp, int perExp, int chaExp, int psyExp){
		//erhöht den entsprechenden Stat in der ExpLeiste um den Wert von expAmount.
		foodExp[0] = foodExp[0] + strExp;
		foodExp[1] = foodExp[1] + conExp;
		foodExp[2] = foodExp[2] + agiExp;
		foodExp[3] = foodExp[3] + intExp;
		foodExp[4] = foodExp[4] + perExp;
		foodExp[5] = foodExp[5] + chaExp;
		foodExp[6] = foodExp[6] + psyExp;
		
		//Exp aller Statpunkte zusammen, d.h. Str-exp + Con-exp + Agi-exp etc.
		int totalExp = foodExp[0] + foodExp[1] + foodExp[2] + foodExp[3] + foodExp[4] + foodExp[5] + foodExp[6];
		
		//Wenn alle derzeitigen Exp Punkte zusammen das expCap erreichen oder höher sind, 
		//dann wird der Stat des Spielers um 1 erhöht.
		if(totalExp >= foodExpCap){
			
			double random = Math.random(); //zufällige Zahl
			int cumulativeProbability = 0; //kumulative Wahrscheinlichkeit
			
			//for-Schleife. Wiederholt den Inhalt des Körpers eine gewisse Anzahl von malen.
			//Schleife fängt bei 0 an. Dieses nennen wir i (int i=0).
			//Nach jedem Durchlaufen der Schleife erhöht sich i um 1 (i++).
			//Die Schleife hört auf den Inhalt des Körpers wiederholt auszuführen,
			//wenn i kleiner als die Größe des Arrays ist(i<foodExp.length).
			for(int i=0; i<foodExp.length; i++){
				
				cumulativeProbability += (foodExp[i]/totalExp); //Kumulative(=aufaddierte) Wahrscheinlichkeit.
				//Beim ersten Durchlauf (i=0) der Schleife, wird die anfängliche Wahrscheinlichkeit von 0
				//um die Wahrscheinlichkeit des ersten Stats erhöht (z.B. 0 + 0.25 = 0.25, also 25%).
				//Beim zweiten Durchlauf (i=1), wird sie um die Wahrscheinlichkeit des zweiten
				//Stats erhöht (z.B. 0.25 + 0.10 = 0.35, also 35%).
				//Die Schleife macht das 7 mal, und dann kommt die Wahrscheinlichkeit bei 100% an.
				
				//Bei jedem Durchlauf wird eine Zufällige Zahl von 0 bis 1
				//mit der aufaddierten Wahrscheinlichkeit verglichen.
				//Liegt die zufällige Zahl unter oder 
				//gleich der kumulativen Wahrscheinlichkeit,
				//dann wird der Stat erhöht. Ansonsten wird nichts getan 
				//und der nächste Schleifendurchlauf erfolgt.
				if (random <= cumulativeProbability) {
					if(i == 0){Str+=1;}
					else if(i == 1){Con+=1;}
					else if(i == 2){Agi+=1;}
					else if(i == 3){Int+=1;}
					else if(i == 4){Per+=1;}
					else if(i == 5){Cha+=1;}
					else if(i == 6){Psy+=1;}
			    }
			}
	        
		}
		
		
	}
	
}
