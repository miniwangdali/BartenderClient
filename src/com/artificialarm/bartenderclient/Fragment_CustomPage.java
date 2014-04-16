package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.common.Variable;
import com.artificialarm.bartenderclient.ui.ListLayout;
import com.example.bartenderclient.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_CustomPage extends Fragment {
	private String[] taste;
	WindowManager windowManager;
	TextView tasteTxt;
	
	public Fragment_CustomPage(){
		this.taste = Variable.getTaste();
		//windowManager = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_custompage, container, false);
		ScrollView scrollView = (ScrollView)view.findViewById(R.id.scrollView);
		LinearLayout listlinearLayout = (LinearLayout)view.findViewById(R.id.listlinearLayout);
		ListLayout listLayout = null;
		for(int i = taste.length; i > 0; i--){
			listLayout = new ListLayout(getActivity());
			listLayout.setTaste(taste[i - 1]);
			listlinearLayout.addView(listLayout,0);
		}
		scrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				LinearLayout tempLayout = (LinearLayout)v.findViewById(R.id.listlinearLayout);
				
				for(int i = 0; i < tempLayout.getChildCount() - 1; i++){
					int[] location = new int[2];
					tempLayout.getChildAt(i).getLocationOnScreen(location);
					//int height = windowManager.getDefaultDisplay().getHeight();
					
					if(location[1] > 384 && location[1] < 768){
						Variable.setTasteOrder(taste[i]);
					}
				}
				return false;
			}
		});

		ImageButton nextButton = (ImageButton)view.findViewById(R.id.nextBtn);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), Variable.getTasteOrder(), Toast.LENGTH_LONG).show();
				FragmentManager fManager = getActivity().getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
				fTransaction.replace(R.id.homepage_fragment, fragment_SeatPage);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
			}
		});
		
		return view;
	}
}
