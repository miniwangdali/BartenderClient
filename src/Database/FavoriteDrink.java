package Database;

import java.util.List;


public class FavoriteDrink {

	
	public  String getDrinkCount(List<Variable> list){

		//Laufvariablen um festzustellen, welches Getränk wie oft bestellt wurde
		
		int i;
		int idrink1 = 0;		//Variable.getTaste()[0]
		int idrink3 = 0;		//Variable.getTaste()[1]
		int idrink2 = 0;		//Variable.getTaste()[2]

		Time currenttime = new Time();			// die aktuelle Zeit
		String currentTime = currenttime.getcurrentTime();
		
		
		
		// wenn aktuelle Zeit zwischen 7:00 und 11:00 Uhr ist
		
		if (currentTime.compareTo("07:00:00")>=0 && currentTime.compareTo("11:00:00")<=0){
				
			for (i=0; i<list.size(); i++){
					
				String time = list.get(i).getTime();	
				
			if (time.compareTo("07:00:00")>=0 && time.compareTo("11:00:00")<=0){
				
				String beverage = list.get(i).getDrink();
				
				if(beverage.equals(Variable.getTaste()[0])){
					++idrink1;	
				} 
				else if(beverage.equals(Variable.getTaste()[1])){
					++idrink2;	
				}
				else if(beverage.equals(Variable.getTaste()[2])){
					++idrink3;	
				}	
			}
			
			}		
			
		}
		
		// wenn aktuelle Zeit zwischen 16:00 und 19:00 Uhr ist
		
		else if (currentTime.compareTo("16:00:00")>=0 && currentTime.compareTo("19:00:00")<=0){
			
			for (i=0; i<list.size(); i++){
				
				String time = list.get(i).getTime();
				
			if (time.compareTo("16:00:00")>=0 && time.compareTo("19:00:00")<=0){
				
				String beverage = list.get(i).getDrink();
				
				if(beverage.equals(Variable.getTaste()[0])){
					++idrink1;	
				} 
				else if(beverage.equals(Variable.getTaste()[1])){
					++idrink2;	
				}
				else if(beverage.equals(Variable.getTaste()[2])){
					++idrink3;	
				}	
			}
			
			}	
		}
		else{
			
			for (i=0; i<list.size(); i++){
				
				String beverage = list.get(i).getDrink();
				
				if(beverage.equals(Variable.getTaste()[0])){
					++idrink1;	
				} 
				else if(beverage.equals(Variable.getTaste()[1])){
					++idrink2;	
				}
				else if(beverage.equals(Variable.getTaste()[2])){
					++idrink3;	
				}

			}
		}
			
		
		
	//  gibt das Getränk aus, dass am häufigsten bestellt wurde 
		
		// bei einem Maximum
		
		if (idrink1>idrink2 && idrink1>idrink3){
			return Variable.getTaste()[0];
		}
		else if(idrink2>idrink1 && idrink2>idrink3){
			return Variable.getTaste()[1];
		}
		else if (idrink3>idrink1 && idrink3>idrink2){
			return Variable.getTaste()[2];
		}
		
		
		// bei zwei Maximums
		
		else if (idrink1==idrink3 && idrink1>idrink2){

			int j=list.size()-1;	// die Größe der Arraylist minus 1--> Zugriff auf letzten Eintrag

			do{
				
				if(list.get(j).getDrink().equals(Variable.getTaste()[0])){
					
					return Variable.getTaste()[0];
				}
				else if(list.get(j).getDrink().equals(Variable.getTaste()[2])){
					
					return Variable.getTaste()[2];
					
				}				
				else{
					--j;
				}
			}while (!list.get(j+1).getDrink().equals(Variable.getTaste()[0]) && !list.get(j+1).getDrink().equals(Variable.getTaste()[2]));	

		}
		else if (idrink1==idrink2 && idrink1>idrink3){
			
			int j=list.size()-1;	// die Größe der Arraylist minus 1--> Zugriff auf letzten Eintrag

			do{
				
				if(list.get(j).getDrink().equals(Variable.getTaste()[0])){
					
					return Variable.getTaste()[0];
				}
				else if(list.get(j).getDrink().equals(Variable.getTaste()[1])){
					
					return Variable.getTaste()[1];				
				}
				else{
					--j;
				}

			}while (!list.get(j+1).getDrink().equals(Variable.getTaste()[0]) && !list.get(j+1).getDrink().equals(Variable.getTaste()[1]));


		}
		else if (idrink3==idrink2 && idrink3>idrink1){
			
			int j=list.size()-1;	// die Größe der Arraylist minus 1--> Zugriff auf letzten Eintrag

			do{
				
				if(list.get(j).getDrink().equals(Variable.getTaste()[2])){
					
					return Variable.getTaste()[2];
				}
				else if(list.get(j).getDrink().equals(Variable.getTaste()[1])){
					
					return Variable.getTaste()[1];
					
				}
				else{
					--j;
				}

			}while (!list.get(j+1).getDrink().equals(Variable.getTaste()[2]) && !list.get(j+1).getDrink().equals(Variable.getTaste()[1]));
		
		}			
		
		// bei drei Maximums
		else if (idrink1==idrink2 && idrink1==idrink3){
			
			int j=list.size()-1;	// die Größe der Arraylist minus 1--> Zugriff auf letzten Eintrag
			String favstring = list.get(j).getDrink();
			
			return favstring;
		}
		return null;		
	}
	
}

