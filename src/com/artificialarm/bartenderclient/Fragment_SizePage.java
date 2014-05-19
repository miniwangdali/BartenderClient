package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.ui.ListLayout;
import com.example.bartenderclient.R;
import Database.Variable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Fragment_SizePage extends Fragment{

	LinearLayout linearLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_sizepage, container, false);
		LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.sizepage);
		ListLayout finalLayout = new ListLayout(getActivity());
		finalLayout.setTaste(Database.Variable.getTasteOrder());
		if(Database.Variable.getTasteOrder().equals("juice")){
			finalLayout.setPicID(R.drawable.cappuccino6);
		}else if(Database.Variable.getTasteOrder().equals("tea")){
			finalLayout.setPicID(R.drawable.cappuccinotea);
		}else{
			finalLayout.setPicID(R.drawable.cappuccino);
		}
		linearLayout.addView(finalLayout, 1);
		
		
		
		Button normalbtn = (Button)view.findViewById(R.id.normalBtn);
		normalbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// schreibt die Gr��e in SizeOrder
				Variable.setSizeOrder("lungo");
				
				
				// �ffnet die neue Fragment fragment_SeatPage
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fragmentTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
			}
		});
		
		Button smallbtn = (Button)view.findViewById(R.id.smallBtn);
		smallbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// schreibt die Gr��e in SizeOrder
				Variable.setSizeOrder("espresso");
				
				
				// �ffnet die neue Fragment fragment_SeatPage
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fragmentTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
			}
		});	
		
		
		Button ristrettobtn = (Button)view.findViewById(R.id.ristrettoBtn);
		ristrettobtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// schreibt die Gr��e in SizeOrder
				Variable.setSizeOrder("ristretto");
				
				
				// �ffnet die neue Fragment fragment_SeatPage
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fragmentTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
			}
		});
		
		/*ImageView normal = (ImageView)view.findViewById(R.id.image_normal);
		normal.setImageResource(R.drawable.cup_normal);
		ImageView small = (ImageView)view.findViewById(R.id.image_small);
		small.setImageResource(R.drawable.cup_small);
		ImageView very = (ImageView)view.findViewById(R.id.image_verysmall);
		very.setImageResource(R.drawable.cup_verysmall);*/
		
		return view;
	}
	
}
