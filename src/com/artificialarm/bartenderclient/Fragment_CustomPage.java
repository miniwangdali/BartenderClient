package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.ui.ConfirmDialog;
import com.artificialarm.bartenderclient.ui.ListLayout;
import com.example.bartenderclient.R;
import Database.Variable;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_CustomPage extends Fragment {
	
	private String[] taste;
	WindowManager windowManager;
	TextView tasteTxt;
	
	
/*	
	public Fragment_CustomPage(){
		
	//	this.taste = Variable.getTaste();

	}

*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_custompage, container, false);
		
		ScrollView scrollView = (ScrollView)view.findViewById(R.id.scrollView);
		LinearLayout listlinearLayout = (LinearLayout)view.findViewById(R.id.listlinearLayout);
		ListLayout listLayout = null;
		
		// aktualisiert immer die Tastes durch sharedPreferences
		String array[] ={"a","b","c"};
		// schreibt die aktualisierten Tastes in ein Array
		SharedPreferences sp2=PreferenceManager.getDefaultSharedPreferences(getActivity());
		array[0] = sp2.getString("NAME_TASTE0", "not set");
		array[1] = sp2.getString("NAME_TASTE1", "not set");
		array[2] = sp2.getString("NAME_TASTE2", "not set");
		if(!array[0].equals("not set")){		
			taste = array;
		}else{
		taste = Variable.getTaste();
		}

/*	ist in Fragment_Homepage verschoben worden, wegen Sprachsteuerung
 		
		//aktualisiert immer die Kategorien
		String array2[] ={"a","b","c"};
		SharedPreferences sp3=PreferenceManager.getDefaultSharedPreferences(getActivity());
		array2[0] = sp3.getString("NAME_CATEGORY0", "not set");
		array2[1] = sp3.getString("NAME_CATEGORY1", "not set");
		array2[2] = sp3.getString("NAME_CATEGORY2", "not set");
		
		// wenn die Kategorien noch nicht geändert worden sind, dann wird nichts gemacht, wenn schon dann setzt er sie aktuell
		if(!array2[0].equals("not set")){		
			Variable.setCategory(array2);
		}else{
		}
		
*/		
		
		

		//fügt die Getränke, die in taste stehen in das ListLayout ein
		
		for(int i = taste.length; i > 0; i--){
			listLayout = new ListLayout(getActivity());
			listLayout.setTaste(taste[i - 1]);	
			listlinearLayout.addView(listLayout,0);
		}
		
		// gibt an, was beim Berühren passiert
		
		scrollView.setOnTouchListener(new OnTouchListener() {
			
			
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				LinearLayout tempLayout = (LinearLayout)v.findViewById(R.id.listlinearLayout);
				
				for(int i = 0; i < tempLayout.getChildCount() - 1; i++){
					int[] location = new int[2];
					tempLayout.getChildAt(i).getLocationOnScreen(location);
					//int height = windowManager.getDefaultDisplay().getHeight();
					
					// schreibt  das Getränk in TasteOrder
					if(location[1] > 384 && location[1] < 768){
						Variable.setTasteOrder(taste[i]);
						Variable.setCategoryOrder(Variable.getCategory()[i]);
					}
				}
				return false;
			}
		});

		// öffnet das nächste Layout und bestätigt das Getränk
		
		ImageButton nextButton = (ImageButton)view.findViewById(R.id.nextBtn);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if (Variable.getCategoryOrder().equals("Coffee")){
					//Anweisung wenn es Kaffee ist
					
					FragmentManager fManager = getActivity().getFragmentManager();
					FragmentTransaction fTransaction = fManager.beginTransaction();
					Fragment_SizePage fragment_SizePage = new Fragment_SizePage();
					fTransaction.replace(R.id.homepage_fragment, fragment_SizePage);
					fTransaction.addToBackStack(null);
					fTransaction.commit();
					
				}
				else{
				// Anweisung, falls es nicht Kaffee ist
					
				// setzt die Größe des Getränks auf normal
				Variable.setSizeOrder("normal");
						
				//öffnet neues Layout SeatPage und gibt den TasteOrder in einem Toast aus
				Toast.makeText(getActivity(), Variable.getTasteOrder(), Toast.LENGTH_LONG).show();
				
				FragmentManager fManager = getActivity().getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
				}
		
			}
		});
		
		
		// Refill-Button
		
		Button refillButton =(Button)view.findViewById(R.id.refillBtn);
		refillButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Anweisung, was beim Drücken des refill-Buttons geschehen soll
				// Anweisung, falls die Maschine gereinigt werden soll
				// öffnet wieder einen Dialog
				
				new ConfirmDialog(getActivity());
				ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
				confirmDialog.setMessage("Do you really want to refill the cup? If so put your cup into the arm!");
				confirmDialog.show();
				
				
			}
		});
		
		// Service-Button
		
		Button serviceButton =(Button)view.findViewById(R.id.serviceBtn);
		serviceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				FragmentManager fManager = getActivity().getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_ServicePage fragment_ServicePage = new Fragment_ServicePage();
				fTransaction.replace(R.id.homepage_fragment, fragment_ServicePage);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
			}
		});
		
		return view;
	}
}
