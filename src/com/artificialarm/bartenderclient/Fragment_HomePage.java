package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.common.Variable;
import com.artificialarm.bartenderclient.ui.ConfirmDialog;
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
	Button customButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);
		
		myfavor = (ImageButton)view.findViewById(R.id.myfavorBtn);

		myfavor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//the function to initialize users' favor taste and position
				//loadFavor();
				ConfirmDialog confirmDialog = new ConfirmDialog(getActivity()).createDialog(getActivity());
				confirmDialog.setMessage("YOU ORDERED:\n" + Variable.getTasteOrder() + "\nAT\n" + Variable.getSeatOrder());
				confirmDialog.show();
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
				
			}
		});
		
		return view;
	}
	
		
}
