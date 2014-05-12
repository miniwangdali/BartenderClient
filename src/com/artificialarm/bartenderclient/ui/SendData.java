package com.artificialarm.bartenderclient.ui;

import Database.Variable;

public class SendData {

	
	public String convertData(){
		
		String sendString;
		
		// Frägt ab, welches Getränk, welchen Sitz, und welche Größe gewählt wurde
		
		//Sitz
			// Getränk
				//Größe
				
		
		// vorne
		if (Variable.getSeatOrder().equals("driver") || Variable.getSeatOrder().equals("codriver")){
			//Variable.getTaste()[0]
			if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){
			
				if (Variable.getSizeOrder().equals("normal")){
	
					sendString="111";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
					sendString="112";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="113";
					return sendString;
				}
			}
			//Variable.getTaste()[1]
			else if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){	
				
				if (Variable.getSizeOrder().equals("normal")){
					
					sendString="121";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
					sendString="122";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="123";
					return sendString;
				}
			}
			//Variable.getTaste()[2]
			else if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){
				
				if (Variable.getSizeOrder().equals("normal")){
					
					sendString="131";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
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
		else if (Variable.getSeatOrder().equals("rearleft") || Variable.getSeatOrder().equals("rearright")){
			//Variable.getTaste()[0]
			if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){
			
				if (Variable.getSizeOrder().equals("normal")){
	
					sendString="211";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
					sendString="212";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="213";
					return sendString;
				}
			}
			//Variable.getTaste()[1]
			else if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){	
				
				if (Variable.getSizeOrder().equals("normal")){
					
					sendString="221";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
					sendString="222";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("ristretto")){
					sendString="223";
					return sendString;
				}
			}
			//Variable.getTaste()[2]
			else if (Variable.getTasteOrder().equals(Variable.getTasteOrder())){
				
				if (Variable.getSizeOrder().equals("normal")){
					
					sendString="231";
					return sendString;
				}
				else if (Variable.getSizeOrder().equals("small")){
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
