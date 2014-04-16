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
import android.widget.LinearLayout;

import com.artificialarm.bartenderclient.common.Variable;
import com.artificialarm.bartenderclient.ui.ConfirmDialog;
import com.artificialarm.bartenderclient.ui.ListLayout;
import com.example.bartenderclient.R;

public class Fragment_SeatPage extends Fragment{
	
	Button driverButton, codriverButton, rearleftButton,rearrightButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_seatpage, container, false);
		LinearLayout seatLayout = (LinearLayout)v.findViewById(R.id.seatLayout);
		ListLayout finalLayout = new ListLayout(getActivity());
		finalLayout.setTaste(Variable.getTasteOrder());
		seatLayout.addView(finalLayout, 1);
		
		driverButton = (Button)v.findViewById(R.id.driverBtn);
		driverButton.setOnClickListener(new ButtonOnClickListener(driverButton));
		codriverButton = (Button)v.findViewById(R.id.codriverBtn);
		codriverButton.setOnClickListener(new ButtonOnClickListener(codriverButton));
		rearleftButton = (Button)v.findViewById(R.id.rearleftBtn);
		rearleftButton.setOnClickListener(new ButtonOnClickListener(rearleftButton));
		rearrightButton = (Button)v.findViewById(R.id.rearrightBtn);
		rearrightButton.setOnClickListener(new ButtonOnClickListener(rearrightButton));
		
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
			switch(button.getId()){
			case R.id.driverBtn:
				Variable.setSeatOrder("driver");
				break;
			case R.id.codriverBtn:
				Variable.setSeatOrder("codriver");
				break;
			case R.id.rearleftBtn:
				Variable.setSeatOrder("rearleft");
				break;
			case R.id.rearrightBtn:
				Variable.setSeatOrder("rearright");
				break;
			default:
				break;	
			}
			
			ConfirmDialog confirmDialog = new ConfirmDialog(getActivity()).createDialog(getActivity());
			confirmDialog.setMessage("YOU ORDERED:\n" + Variable.getTasteOrder() + "\nAT\n" + Variable.getSeatOrder());
			confirmDialog.show();
			

		}
		
	}
	
}
