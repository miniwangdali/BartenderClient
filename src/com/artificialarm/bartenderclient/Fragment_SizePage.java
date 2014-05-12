package com.artificialarm.bartenderclient;

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

public class Fragment_SizePage extends Fragment{

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_sizepage, container, false);
		
		Button normalbtn = (Button)view.findViewById(R.id.normalBtn);
		normalbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// schreibt die Größe in SizeOrder
				Variable.setSizeOrder("normal");
				
				
				// öffnet die neue Fragment fragment_SeatPage
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
				
				// schreibt die Größe in SizeOrder
				Variable.setSizeOrder("small");
				
				
				// öffnet die neue Fragment fragment_SeatPage
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
				
				// schreibt die Größe in SizeOrder
				Variable.setSizeOrder("ristretto");
				
				
				// öffnet die neue Fragment fragment_SeatPage
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fragmentTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
			}
		});
		
		
		return view;
	}
	
}
