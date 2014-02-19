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

public class Fragment_HomePage extends Fragment {

	Button myfavorButton,coffeeButton,teaButton, hotwaterButton,orderButton;
	Fragment_Orderlist orderlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);
		
		myfavorButton = (Button)view.findViewById(R.id.FaverateButton);
		coffeeButton = (Button)view.findViewById(R.id.coffeeButton);
		teaButton = (Button)view.findViewById(R.id.teaButton);
		hotwaterButton = (Button)view.findViewById(R.id.waterButton);
		
		myfavorButton.setOnClickListener(new ButtonOnClickListener(myfavorButton));
		coffeeButton.setOnClickListener(new ButtonOnClickListener(coffeeButton));
		teaButton.setOnClickListener(new ButtonOnClickListener(teaButton));
		hotwaterButton.setOnClickListener(new ButtonOnClickListener(hotwaterButton));
		
		
		return view;
	}
	
	private class ButtonOnClickListener implements OnClickListener {

		private Button button;
		public ButtonOnClickListener(Button btn){
			super();
			this.button = btn;
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Variable.setBartenderOrder(button.getText().toString());
			orderlist = (Fragment_Orderlist)getActivity().getFragmentManager().findFragmentById(R.id.orderlist_fragment);
			orderButton = (Button)orderlist.getView().findViewById(R.id.beverageOrderButton);
			orderButton.setText(Variable.getBartenderOrder());
			if(button.getId() == R.id.coffeeButton){
				FragmentManager fManager = getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_CoffeeSorts coffeeSorts = new Fragment_CoffeeSorts();
				fTransaction.replace(R.id.homepage_fragment, coffeeSorts);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
			}else {
				FragmentManager fManager = getFragmentManager();
				FragmentTransaction fTransaction = fManager.beginTransaction();
				Fragment_SeatPage seatPage = new Fragment_SeatPage();
				fTransaction.replace(R.id.homepage_fragment, seatPage);
				fTransaction.addToBackStack(null);
				fTransaction.commit();
			}
			
		}
		
	}
	
}
