package com.artificialarm.bartenderclient.ui;

import Database.Variable;

public class SendData {

	
	public String convertData(){
		
		String sendString;
		
		// Fr�gt ab, welches Getr�nk, welchen Sitz, und welche Gr��e gew�hlt wurde
		
		//Sitz
			// Getr�nk
				//Gr��e
				
		
		// vorne
		if (Variable.getSeatOrder().equals("front")){
			//Variable.getTaste()[0]
			if (Variable.getTasteOrder().equals(Variable.getTaste()[0])){
			
				if (Variable.getSizeOrder().equals("lungo")){
	
					sendString="111";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="112";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="113";
					return sendString;
				}
			}
			//Variable.getTaste()[1]
			else if (Variable.getTasteOrder().equals(Variable.getTaste()[1])){	
				
				if (Variable.getSizeOrder().equals("lungo")){
					
					sendString="121";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="122";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="123";
					return sendString;
				}
			}
			//Variable.getTaste()[2]
			else if (Variable.getTasteOrder().equals(Variable.getTaste()[2])){
				
				if (Variable.getSizeOrder().equals("lungo")){
					
					sendString="131";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="132";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="133";
					return sendString;
				}
			}
			else if (Variable.getTasteOrder().equals("clean")){
				sendString="190";											// CLEAN-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("openstorage")){
				sendString="193";											// Storage-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("closestorage")){
				sendString="194";											// Storage-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("stop")){
				sendString="191";											// Stop-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("continue")){
				sendString="192";											// Continue-ANWEISUNG
				return sendString;
			}
		}
	
		
		// hinten
		else if (Variable.getSeatOrder().equals("back")){
			//Variable.getTaste()[0]
			if (Variable.getTasteOrder().equals(Variable.getTaste()[0])){
			
				if (Variable.getSizeOrder().equals("lungo")){
	
					sendString="211";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="212";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="213";
					return sendString;
				}
			}
			//Variable.getTaste()[1]
			else if (Variable.getTasteOrder().equals(Variable.getTaste()[1])){	
				
				if (Variable.getSizeOrder().equals("lungo")){
					
					sendString="221";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="222";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="223";
					return sendString;
				}
			}
			//Variable.getTaste()[2]
			else if (Variable.getTasteOrder().equals(Variable.getTaste()[2])){
				
				if (Variable.getSizeOrder().equals("lungo")){
					
					sendString="231";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("espresso")){
					sendString="232";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="233";
					return sendString;
				}
			}
			else if (Variable.getTasteOrder().equals("clean")){
					sendString="190";											// CLEAN-ANWEISUNG
					return sendString;
			}
			else if (Variable.getTasteOrder().equals("openstorage")){
				sendString="193";											// Storage-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("closestorage")){
				sendString="194";											// Storage-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("stop")){
				sendString="191";											// Stop-ANWEISUNG
				return sendString;
			}
			else if (Variable.getTasteOrder().equals("continue")){
				sendString="192";											// Continue-ANWEISUNG
				return sendString;
			}
		}
		
		return "117";
	
	}

}
