package com.artificialarm.bartenderclient;

import com.example.bartenderclient.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Fragment_HomePage extends Fragment {

	ImageButton coffeeButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);
		
		coffeeButton = (ImageButton)view.findViewById(R.id.coffeeButton);
		/*coffeeButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//coffeeButton.getDrawable().setAlpha(150);
					coffeeButton.invalidate();
				} else {
					//coffeeButton.getDrawable().setAlpha(255);
					coffeeButton.invalidate();
				}
				return false;
			}
		});*/
		coffeeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_SeatPage seatPage = new Fragment_SeatPage();
				fTransaction.replace(R.id.homepage_fragment, seatPage);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
				
			}
		});
		
		
		return view;
	}
	
}
