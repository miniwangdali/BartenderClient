package com.artificialarm.bartenderclient;

import java.util.ArrayList;
import java.util.List;
import com.artificialarm.bartenderclient.ui.ConfirmDialog;
import com.example.bartenderclient.R;
import Database.Variable;
import android.R.integer;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment_ServicePage extends Fragment{

	
	Button confirmTastes, clean;
	EditText leftSlide, centralSlide, rightSlide;
	Spinner  spleft, spcentral, spright;
	List<String> state;
	
	// shared preferences
	
	public  final String PREF_NAME = "test";
	public  final String PREF_KEY_TASTE = "taste";
	public  final String PREF_KEY_CATEGORY = "prefkey_category";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		final View view = inflater.inflate(R.layout.fragment_servicepage, container, false);
		
		//Spinner
		
		spleft = (Spinner)view.findViewById(R.id.spinnerleft);
		spcentral = (Spinner)view.findViewById(R.id.spinnercentral);
		spright = (Spinner)view.findViewById(R.id.spinnerright);
		
		// schreib die Werte von den verschidenen Kategorien in den spinner
		state = new ArrayList<String>();
		state.add("Coffee");
		state.add("Tea");
		state.add("Juice");
		
		/*ArrayAdapter<String> adapterspinner = new ArrayAdapter<String>(getActivity(), R.layout.spinnerchecked, state){
			@Override
			public View getDropDownView(int position, View convertView, ViewGroup parent){
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				View view = inflater.inflate(R.layout.spinneritem, null);
				TextView text = (TextView)view.findViewById(R.id.tasteTxt);
				text.setText(state.get(position));
				return view;
			}
		};
		
		adapterspinner.setDropDownViewResource(R.layout.spinneritem);*/
		

		SpinnerAdapter adapterspinner = new SpinnerAdapter(getActivity(), state);
		spleft.setAdapter(adapterspinner);
		spcentral.setAdapter(adapterspinner);
		spright.setAdapter(adapterspinner);

		
		// Anweisung wenn confirmButton gedr�ckt wird
		
		Button confirmButton = (Button)view.findViewById(R.id.attestBtn);
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				leftSlide = (EditText)view.findViewById(R.id.leftET);
				centralSlide = (EditText)view.findViewById(R.id.centralET);
				rightSlide = (EditText)view.findViewById(R.id.rightET);
				
				String tasteleft = leftSlide.getText().toString();
				String tastecentral = centralSlide.getText().toString();
				String tasteright = rightSlide.getText().toString();
				
				// schreibt die drei Geschm�cker in Variable.Taste
				
				String[] taste = { tasteleft , tastecentral , tasteright}; 
				
				// in shared preferences schreiben
				
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
				Editor ed = sp.edit();
				for(int k=0;k<3;k++){
					ed.putString("NAME_TASTE"+k, taste[k] );
				}
				ed.commit();
				
				Variable.setTaste(taste);
				
				// Category
				String categoryleft = String.valueOf(spleft.getSelectedItem());
				String categorycentral = String.valueOf(spcentral.getSelectedItem());
				String categoryright = String.valueOf(spright.getSelectedItem());
				
				String[] category = {categoryleft, categorycentral, categoryright};
				
	//			Log.d("TAGZWEI", category[1]);
				
				// in shared preferences schreiben
				SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
				Editor ed1 = sp1.edit();
				
				for(int k=0;k<3;k++){
					ed1.putString("NAME_CATEGORY"+k, category[k] );
				}
				ed1.commit();
				
				Variable.setCategory(category);

				
				// man kommt zur�ck zur CustomPage
				
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment_CustomPage fragment_CustomPage = new Fragment_CustomPage();
				ft.replace(R.id.homepage_fragment, fragment_CustomPage);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
// Storage-Button	
		Button openstroageButton = (Button)view.findViewById(R.id.openstorageBtn);
		openstroageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Anweisung, was beim Dr�cken des clean-Buttons geschehen soll
				// Anweisung, falls die Maschine gereinigt werden soll
				// �ffnet wieder einen Dialog

				ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
				confirmDialog.setMessage("Do you really want to clean the coffee machine? If so put a cup below the machine!");
				confirmDialog.show();
				
				Variable.setTasteOrder("openstorage");	
				Variable.setSeatOrder("front");
				Variable.setSizeOrder("lungo");
			}
		});

// Storage-Button	
				Button closestorageButton = (Button)view.findViewById(R.id.closestorageBtn);
				closestorageButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						// Anweisung, was beim Dr�cken des clean-Buttons geschehen soll
						// Anweisung, falls die Maschine gereinigt werden soll
						// �ffnet wieder einen Dialog

						ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
						confirmDialog.setMessage("Do you really want to clean the coffee machine? If so put a cup below the machine!");
						confirmDialog.show();
						
						Variable.setTasteOrder("closestorage");	
						Variable.setSeatOrder("front");
						Variable.setSizeOrder("lungo");
					}
				});		
		
	
// Stop function f�r die tests	
		Button stopButton = (Button)view.findViewById(R.id.stopBtn);
		stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Anweisung, was beim Dr�cken des clean-Buttons geschehen soll
				// Anweisung, falls die Maschine gereinigt werden soll
				// �ffnet wieder einen Dialog

				ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
				confirmDialog.setMessage("Do you really want to stop the process?");
				confirmDialog.show();
				
				Variable.setTasteOrder("stop");
				Variable.setSeatOrder("front");
				Variable.setSizeOrder("lungo");
	
			}
		});
//continue	
		Button continueButton = (Button)view.findViewById(R.id.continueBtn);
		continueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Anweisung, was beim Dr�cken des clean-Buttons geschehen soll
				// Anweisung, falls die Maschine gereinigt werden soll
				// �ffnet wieder einen Dialog

				ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
				confirmDialog.setMessage("Do you really want to continue the process?");
				confirmDialog.show();
				
				Variable.setTasteOrder("continue");	
				Variable.setSeatOrder("front");
				Variable.setSizeOrder("lungo");
	
			}
		});
		
		
		return view;
	}
	
}
