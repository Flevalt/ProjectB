package Beschreibungen;

//Erklärt alle Statements
public class Statements {

	int i = 1;
	
	public void ifStatement(){
		
		if(i == 1){
			i+=1;
		} 
		else if(i == 2) {
			i+=2;
		} 
		else {
			i +=3;
		}
		
	}
	
	public void switchStatement(){
		
		switch(i){
		case 1:
			i+=1;
			break;
		case 2:
			i+=2;
			break;
		case 3:
			i+=3;
			break;
		}
	}
	
}
