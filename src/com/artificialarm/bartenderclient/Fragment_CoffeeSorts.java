package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.common.Variable;
import com.example.bartenderclient.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragment_CoffeeSorts extends Fragment {

	Button esButton, doubleesButton,customButton,orderButton;
	Fragment_Orderlist orderlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_coffeesorts, container, false);
		
		esButton = (Button)v.findViewById(R.id.espressoButton);
		doubleesButton = (Button)v.findViewById(R.id.doubleButton);
		customButton = (Button)v.findViewById(R.id.cus1Button);
		
		esButton.setOnClickListener(new ButtonOnClickListener(esButton));
		doubleesButton.setOnClickListener(new ButtonOnClickListener(doubleesButton));
		customButton.setOnClickListener(new ButtonOnClickListener(customButton));
		
		return v;
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
			Variable.setTasteOrder(button.getText().toString());
			orderlist = (Fragment_Orderlist)getActivity().getFragmentManager().findFragmentById(R.id.orderlist_fragment);
			orderButton = (Button)orderlist.getView().findViewById(R.id.beverageOrderButton);
			orderButton.setText(Variable.getBartenderOrder() + "\n" + Variable.getTasteOrder());
			
			FragmentManager fManager = getFragmentManager();
			FragmentTransaction fTransaction = fManager.beginTransaction();
			Fragment_SeatPage seatPage = new Fragment_SeatPage();
			fTransaction.replace(R.id.homepage_fragment, seatPage);
			fTransaction.addToBackStack(null);
			fTransaction.commit();
		}
		
	}
	
}
