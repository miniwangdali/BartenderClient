package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.common.Variable;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment_HomePage extends Fragment {

	ImageButton myfavor, custom;
	Fragment_Orderlist orderlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);
		
		myfavor = (ImageButton)view.findViewById(R.id.myfavorBtn);
		myfavor.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if( event.getAction() == MotionEvent.ACTION_DOWN ){
					myfavor.setImageResource(R.drawable.myfavor_pressed);
					myfavor.invalidate();
				} else {
					myfavor.setImageResource(R.drawable.myfavor);
					myfavor.invalidate();
				}
				
				return false;
			}
		});
		myfavor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//loadFavor();
				Toast.makeText(getActivity(), "MyFavor", Toast.LENGTH_LONG).show();
			}
		});
		
		custom = (ImageButton)view.findViewById(R.id.customBtn);
		custom.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if( event.getAction() == MotionEvent.ACTION_DOWN ){
					custom.setImageResource(R.drawable.custom_pressed);
					custom.invalidate();
				} else {
					custom.setImageResource(R.drawable.custom);
					custom.invalidate();
				}
				return false;
			}
		});
		custom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment_CustomPage fragment_CustomPage = new Fragment_CustomPage();
				ft.replace(R.id.homepage_fragment, fragment_CustomPage);
				ft.addToBackStack(null);
				ft.commit();
				
			}
		});
		
		
		return view;
	}
	
		
}
