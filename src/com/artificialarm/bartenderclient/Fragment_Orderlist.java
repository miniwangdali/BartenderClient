package com.artificialarm.bartenderclient;

import com.example.bartenderclient.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Orderlist extends Fragment {
	
	private Button bartenderButton, seatButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_orderlist, container, false);
		
		bartenderButton = (Button)v.findViewById(R.id.beverageOrderButton);
		bartenderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fmFragmentManager = getFragmentManager();
				FragmentTransaction ftFragmentTransaction = fmFragmentManager.beginTransaction();
				Fragment_CustomPage fragment_CustomPage = new Fragment_CustomPage();
				ftFragmentTransaction.replace(R.id.homepage_fragment, fragment_CustomPage);
				ftFragmentTransaction.commit();
			}
		});
		
		seatButton = (Button)v.findViewById(R.id.seatOrderButton);
		seatButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fmFragmentManager = getFragmentManager();
				FragmentTransaction ftFragmentTransaction = fmFragmentManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				ftFragmentTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				ftFragmentTransaction.commit();
			}
		});
		
		return v;
	}

}
