package com.artificialarm.bartenderclient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.artificialarm.bartenderclient.common.Variable;
import com.example.bartenderclient.R;

public class Fragment_SeatPage extends Fragment{

	Button driverButton, coButton, rearLButton, rearMButton, rearRButton, orderButton;
	Fragment_Orderlist orderlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_seatpage, container, false);
		
		driverButton = (Button)v.findViewById(R.id.driverSeatButton);
		coButton = (Button)v.findViewById(R.id.codriverSeatButton);
		rearLButton = (Button)v.findViewById(R.id.rearLeftButton);
		rearMButton = (Button)v.findViewById(R.id.rearMidButton);
		rearRButton = (Button)v.findViewById(R.id.rearRightButton);
		
		driverButton.setOnClickListener(new ButtonOnClickListener(driverButton));
		coButton.setOnClickListener(new ButtonOnClickListener(coButton));
		rearLButton.setOnClickListener(new ButtonOnClickListener(rearLButton));
		rearMButton.setOnClickListener(new ButtonOnClickListener(rearMButton));
		rearRButton.setOnClickListener(new ButtonOnClickListener(rearRButton));
		
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
			Variable.setSeatOrder(button.getText().toString());
			orderlist = (Fragment_Orderlist)getActivity().getFragmentManager().findFragmentById(R.id.orderlist_fragment);
			orderButton = (Button)orderlist.getView().findViewById(R.id.seatOrderButton);
			orderButton.setText(Variable.getSeatOrder());

		}
		
	}
	
}
