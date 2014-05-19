package com.artificialarm.bartenderclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.artificialarm.bartenderclient.ui.ConfirmDialog;
import com.example.bartenderclient.R;
import Database.Variable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class Fragment_HomePage extends Fragment {

	ImageButton myfavor, custom;
	Button customButton;
	
	// Voice Control
	Button btn;
	ArrayList<String> results;
	private TextToSpeech tts;
	static int REQUESTCODE;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);
		
		
		//aktualisiert immer die Kategorien
		String array2[] ={"a","b","c"};
		SharedPreferences sp3=PreferenceManager.getDefaultSharedPreferences(getActivity());
		array2[0] = sp3.getString("NAME_CATEGORY0", "not set");
		array2[1] = sp3.getString("NAME_CATEGORY1", "not set");
		array2[2] = sp3.getString("NAME_CATEGORY2", "not set");
		
		// wenn die Kategorien noch nicht ge�ndert worden sind, dann wird nichts gemacht, wenn schon dann setzt er sie aktuell
		if(!array2[0].equals("not set")){		
			Variable.setCategory(array2);
		}else{
		}
		
		// aktualisiert immer die Tastes durch sharedPreferences
		String array[] ={"a","b","c"};
		// schreibt die aktualisierten Tastes in ein Array
		SharedPreferences sp2=PreferenceManager.getDefaultSharedPreferences(getActivity());
		array[0] = sp2.getString("NAME_TASTE0", "not set");
		array[1] = sp2.getString("NAME_TASTE1", "not set");
		array[2] = sp2.getString("NAME_TASTE2", "not set");
		if(!array[0].equals("not set")){		
			Variable.setTaste(array);
		}else{
			
		}
		
		
		
		
		myfavor = (ImageButton)view.findViewById(R.id.myfavorBtn);

		myfavor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				
				Database.DatabaseOpenHelper db = new Database.DatabaseOpenHelper(getActivity());
				
				// schreibt die schon bestellten Getr�nke in eine List
				List<Variable> list = db.getAllDrinks();
				
				//�berpr�fen ob in der Liste was steht
				
				if (list.size()==0){
					// wenn noch kein Getr�nk bestellt wurde
					
					FragmentManager fm = getFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					Fragment_CustomPage fragment_CustomPage = new Fragment_CustomPage();
					ft.replace(R.id.homepage_fragment, fragment_CustomPage);
					ft.addToBackStack(null);
					ft.commit();	
					MainActivity.setPage(2);
				}
				else{
					// wenn schon ein Getr�nk bestellt wurde	
				Database.FavoriteDrink favDrink = new Database.FavoriteDrink();
				
				// das Getr�nk wird in Variable TasteOrder gespeichert
				Variable.setTasteOrder(favDrink.getDrinkCount(list));
				// es wird immer ein gro�er f�r den fahrer bestellt
				Variable.setSizeOrder("lungo");
				Variable.setSeatOrder("front");
				
				//Best�tigen des Favorite Drinks und der Sitzposition Fahrer 
				
				ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
				confirmDialog.setMessage("You ordered:\n" + Variable.getTasteOrder() + "\nat\n" + "front");
				confirmDialog.show();
				
				}
			}
		});
		
		
		
		customButton = (Button)view.findViewById(R.id.customBtn);
		
		customButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment_CustomPage fragment_CustomPage = new Fragment_CustomPage();
				ft.replace(R.id.homepage_fragment, fragment_CustomPage);
				ft.addToBackStack(null);
				ft.commit();
				MainActivity.setPage(2);
			}
		});
	
		

		
		// Voice Control
		
		btn = (Button)view.findViewById(R.id.voiceControl);
		PackageManager pm = getActivity().getPackageManager();		// getActivity() wird wegen Fragment gebraucht
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		
		// unable Button, if voice recognition is not present
		if(activities.size() == 0){
			
			
			btn.setEnabled(false);
			btn.setText("Rocognizer not present");
			
		}
		
		
		// Text to Speech
		tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {

			@Override
			public void onInit(int status) {
				
				
		        if (status == TextToSpeech.SUCCESS) {
		        	 
		            int result = tts.setLanguage(Locale.ENGLISH);
		 
		            if (result == TextToSpeech.LANG_MISSING_DATA
		                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
		                Log.e("TTS", "This Language is not supported");
		            } else {
		                
		                speakOut("");
		            }
		 
		        } else {
		            Log.e("TTS", "Initilization Failed!");
		        }
			}
        	
        });
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				REQUESTCODE = 1234;
				
				// Gibt dir die Anweisung ein Getr�nk zu w�hlen
    			speakOut("Please choose your drink!");
    			
    			try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			// started voice recognition
    			
				startVoiceRecognition();
				
		
			}
		});
		
		return view;
	}
	

	private void speakOut(String string) {
		 
        String text = string;
 
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
	
	private void startVoiceRecognition() {
		
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak");
		
		startActivityForResult(intent, REQUESTCODE);
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
		// Array um Getr�nk und Sitzposition zu senden
		String OrderString;
		
		
		
// Getr�nkeauswahl
		
        if (requestCode == 1234 && resultCode == getActivity().RESULT_OK)
        {
            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            
    		if (results.get(0).equals("coffee") || results.get(1).equals("coffee") || results.get(2).equals("coffee")){
    			
    			// in Variable schreiben
    			OrderString="Coffee";
    			
    			// schauen ob in category enthalten
    			if(Variable.getCategory()[0].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[0]);
    	    			REQUESTCODE = 1235;
    	    			
    					// Gibt dir die Anweisung die Gr��e zu w�hlen
    	    			speakOut("Please choose the size of your drink!");
    	    			
    	    			try {
    						Thread.sleep(2000);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
    	    			
    	    			
    			}
    			else if(Variable.getCategory()[1].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[1]);
    	    			REQUESTCODE = 1235;
    	    			
    					// Gibt dir die Anweisung die Gr��e zu w�hlen
    	    			speakOut("Please choose the size of your drink!");
    	    			
    	    			try {
    						Thread.sleep(2000);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else if(Variable.getCategory()[2].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[2]);
    	    			REQUESTCODE = 1235;
    	    			
    					// Gibt dir die Anweisung die Gr��e zu w�hlen
    	    			speakOut("Please choose the size of your drink!");
    	    			
    	    			try {
    						Thread.sleep(2000);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else{
    				// Sagt dir das kein kaffee eingelegt ist
        			speakOut("There is no" + OrderString);
        			
        			try {
    					Thread.sleep(2000);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			speakOut("Please choose your drink again!");
        			
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();	
    			}
 	

    		}
    		else if(results.get(0).equals("juice") || results.get(1).equals("juice") || results.get(2).equals("juice") || results.get(3).equals("juice") || results.get(4).equals("juice") ){
    			
    			// in Variable schreiben
    			OrderString="Juice";
    			
    			// schauen ob in category enthalten
    			if(Variable.getCategory()[0].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[0]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
    			}
    			else if(Variable.getCategory()[1].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[1]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else if(Variable.getCategory()[2].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[2]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else{
    				// Sagt dir das kein kaffee eingelegt ist
        			speakOut("There is no" + OrderString);
        			
        			try {
    					Thread.sleep(2000);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			speakOut("Please choose your drink again!");
        			
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();	
    			}
    		}
    		else if(results.get(0).equals("tea") || results.get(1).equals("tea") || results.get(2).equals("tea") || results.get(3).equals("tea") || results.get(4).equals("tea") ||
    				results.get(0).equals("he") || results.get(1).equals("he") || results.get(2).equals("he") || results.get(3).equals("he") || results.get(4).equals("he") ||
    				results.get(0).equals("ti") || results.get(1).equals("ti") || results.get(2).equals("ti") || results.get(3).equals("ti") || results.get(4).equals("ti") ||
    				results.get(0).equals("t-") || results.get(1).equals("t-") || results.get(2).equals("t-") || results.get(3).equals("t-") || results.get(4).equals("t-") ||
    				results.get(0).equals("t") || results.get(1).equals("t") || results.get(2).equals("t") || results.get(3).equals("t") || results.get(4).equals("t") 
    				){
    			// in Variable schreiben
    			OrderString="Tea";
    			
    			// schauen ob in category enthalten
    			if(Variable.getCategory()[0].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[0]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
    			}
    			else if(Variable.getCategory()[1].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[1]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else if(Variable.getCategory()[2].equals(OrderString)){
    					Variable.setTasteOrder(Variable.getTaste()[2]);
    					Variable.setSizeOrder("lungo");
    	    			REQUESTCODE = 1236;
    	    			speakOut("Please choose your seat!");
    	    			try {
    						Thread.sleep(1200);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    			
    	    			startVoiceRecognition();
				}
    			else{
    				// Sagt dir das kein kaffee eingelegt ist
        			speakOut("There is no" + OrderString);
        			
        			try {
    					Thread.sleep(2000);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			speakOut("Please choose your drink again!");
        			
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();	
    			}
    		}
    		else{
    			
    			speakOut("Please choose your drink again!");
    			
    			try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			startVoiceRecognition();
    		}
    			
        	}

        
        
// Gr��enauswahl
        
    	if (requestCode == 1235 && resultCode == getActivity().RESULT_OK)
    	{
    		results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        
    		if (results.get(0).equals("lungo") || results.get(1).equals("lungo") || results.get(2).equals("lungo")){
    			Variable.setSizeOrder("lungo");
    			REQUESTCODE = 1236;
    			speakOut("Please choose your seat!");
    			try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			startVoiceRecognition();
    		}
    		else if(results.get(0).equals("espresso") || results.get(1).equals("espresso") || results.get(2).equals("espresso") || results.get(3).equals("espresso") ||
    				results.get(0).equals("Espresso") || results.get(1).equals("Espresso") || results.get(2).equals("Espresso") || results.get(3).equals("Espresso")){
    			Variable.setSizeOrder("espresso");
    			REQUESTCODE = 1236;
    			speakOut("Please choose your seat!");
    			try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			startVoiceRecognition();	
    		}
    		else if(results.get(0).equals("ristretto") || results.get(1).equals("ristretto") || results.get(2).equals("ristretto")||
    				results.get(0).equals("Ristretto") || results.get(1).equals("Ristretto") || results.get(2).equals("Ristretto")){
    			Variable.setSizeOrder("ristretto");
    			REQUESTCODE = 1236;
    			speakOut("Please choose your seat!");
    			try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			startVoiceRecognition();
    		}
    		else{
    			        			
    			speakOut("Please choose the size again!");
    			
    			try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			startVoiceRecognition();
    		}
    	}
        
        
        
// Sitzauswahl
        
        	if (requestCode == 1236 && resultCode == getActivity().RESULT_OK)
        	{
        		results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            
        		if (results.get(0).equals("front") || results.get(1).equals("front") || results.get(2).equals("front")){
        			Variable.setSeatOrder("front");
        			REQUESTCODE = 1237;
        			speakOut("Please confirm your order!");
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();
        		}
        		else if(results.get(0).equals("back") || results.get(1).equals("back") || results.get(2).equals("back") || results.get(3).equals("back")){
        			Variable.setSeatOrder("back");
        			REQUESTCODE = 1237;
        			speakOut("Please confirm your order!");
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();
        		}else{
        			        			
        			speakOut("Please choose your seat again!");
        			
        			try {
    					Thread.sleep(1500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();
        		}
        	}
        
        	
        	
// Best�tigen oder zur�ckgehen
        	
        	if (requestCode == 1237 && resultCode == getActivity().RESULT_OK)
        	{
        		results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            
        		if (results.get(0).equals("order")){
        			
        			// die Daten senden
        			try {
						MainActivity.sendData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			
        			// wieder auf 
        			REQUESTCODE = 1234;
        		}
        		else if(results.get(0).equals("cancel") || results.get(1).equals("cancel") || results.get(2).equals("cancel") || results.get(3).equals("cancel") || results.get(4).equals("cancel") || results.get(5).equals("cancel")){
        			
        			REQUESTCODE = 1234;
        		}else{
        			        			
        			speakOut("do you want to order or to cancel it");
        			
        			try {
    					Thread.sleep(2800);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			startVoiceRecognition();
        		}
        	}
        	
        	
   
        super.onActivityResult(requestCode, resultCode, data);
    }
	
	
}
