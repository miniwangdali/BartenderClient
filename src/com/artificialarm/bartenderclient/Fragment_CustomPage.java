package com.artificialarm.bartenderclient;

import java.util.ArrayList;
import java.util.List;

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
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
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
	private int itemHeight;
	private int lastY;
	ListLayout listLayout = null;
	ScrollView scrollView;
	List<ListLayout> listgroup = new ArrayList<ListLayout>();
/*	
	public Fragment_CustomPage(){
		
	//	this.taste = Variable.getTaste();

	}

*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_custompage, container, false);
		
		scrollView = (ScrollView)view.findViewById(R.id.scrollView);
		LinearLayout listlinearLayout = (LinearLayout)view.findViewById(R.id.listlinearLayout);
		
		
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

		Variable.setTasteOrder(taste[0]);
		Variable.setCategoryOrder(Variable.getCategory()[0]);
		
/*	ist in Fragment_Homepage verschoben worden, wegen Sprachsteuerung
 		
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
		
*/		
		
		

		//f�gt die Getr�nke, die in taste stehen in das ListLayout ein
		
		for(int i = taste.length; i > 0; i--){
			listLayout = new ListLayout(getActivity());
			listLayout.setTaste(taste[i - 1]);	
			listlinearLayout.addView(listLayout,0);
			listgroup.add(0, listLayout);
		}
		
		
		
		
		// gibt an, was beim Ber�hren passiert
		
		final ViewTreeObserver observer = scrollView.getViewTreeObserver();
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				
				itemHeight = 360;
				if(scrollView.getViewTreeObserver().isAlive()){
					scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				}
				
				for(int i = 0; i < listgroup.size(); i ++){
					int[] location = new int[2];
					listgroup.get(i).getLocationOnScreen(location);
					TextView textView = (TextView)listgroup.get(i).findViewById(R.id.tasteTxt);
					textView.setTextSize((float)(32 - Math.abs(location[1] - 500) * 0.03));
					textView.setPadding((int)(150 - Math.floor(Math.pow(2, ((Math.abs(location[1] - 500) < 220) ? (Math.abs(location[1] - 500) * 0.03) : 220 * 0.03)) - 1)), 0, 0, 0);
				}
			}
		});
		
		scrollView.setOnTouchListener(new OnTouchListener() {
			
			
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				LinearLayout tempLayout = (LinearLayout)v.findViewById(R.id.listlinearLayout);
				final ScrollView sv = (ScrollView)v;
				for(int i = 0; i < tempLayout.getChildCount() - 1; i++){
					int[] location = new int[2];
					tempLayout.getChildAt(i).getLocationOnScreen(location);
					//int height = windowManager.getDefaultDisplay().getHeight();
					
					// schreibt  das Getr�nk in TasteOrder
					if(location[1] > 384 && location[1] < 768){
						Variable.setTasteOrder(taste[i]);
						Variable.setCategoryOrder(Variable.getCategory()[i]);
					}
					
				}
				
				for(int i = 0; i < listgroup.size(); i ++){
					int[] location = new int[2];
					listgroup.get(i).getLocationOnScreen(location);
					TextView textView = (TextView)listgroup.get(i).findViewById(R.id.tasteTxt);
					textView.setTextSize((float)(32 - Math.abs(location[1] - 500) * 0.03));
					textView.setPadding((int)(150 - Math.floor(Math.pow(2, ((Math.abs(location[1] - 500) < 220) ? (Math.abs(location[1] - 500) * 0.03) : 220 * 0.03)) - 1)), 0, 0, 0);
				}
				
				if(event.getAction() == MotionEvent.ACTION_UP){
					
					lastY = sv.getScrollY();
					handler.sendMessageDelayed(handler.obtainMessage(0, v), 50);
				}
				
				return false;
			}
		});
		


		// �ffnet das n�chste Layout und best�tigt das Getr�nk
		
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
					
				// setzt die Gr��e des Getr�nks auf normal
				Variable.setSizeOrder("normal");
						
				//�ffnet neues Layout SeatPage und gibt den TasteOrder in einem Toast aus
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
				// Anweisung, was beim Dr�cken des refill-Buttons geschehen soll
				// Anweisung, falls die Maschine gereinigt werden soll
				// �ffnet wieder einen Dialog
				
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
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			ScrollView sv = (ScrollView)msg.obj;
			if(msg.what == 0){
				if(lastY == sv.getScrollY()){
					int num = lastY / itemHeight;
					int over = lastY % itemHeight;
					if(over > itemHeight / 2){
						sv.smoothScrollTo(0, ( num+ 1 ) * itemHeight);
					}else{
						sv.smoothScrollTo(0, num * itemHeight);
						
					}
				}else{
					lastY = sv.getScrollY();
					handler.sendMessageDelayed(handler.obtainMessage(0, sv), 50);
				}
			}
		}
	};
	
}
