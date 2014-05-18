package com.artificialarm.bartenderclient;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
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
		finalLayout.setTaste(Database.Variable.getTasteOrder());
		seatLayout.addView(finalLayout, 1);
		
		driverButton = (Button)v.findViewById(R.id.driverBtn);
		driverButton.setOnClickListener(new ButtonOnClickListener(driverButton));

		rearleftButton = (Button)v.findViewById(R.id.rearleftBtn);
		rearleftButton.setOnClickListener(new ButtonOnClickListener(rearleftButton));

		
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
			
			// Schreibt je nach gedr�ckten Button den gew�hlten Sitz in SeatOrder.
			
			switch(button.getId()){
			case R.id.driverBtn:
				Database.Variable.setSeatOrder("front");
				break;

			case R.id.rearleftBtn:
				Database.Variable.setSeatOrder("back");
				break;

			default:
				break;	
			}
			
			//�ffnet einen Dialog, bei dem die Bestellung noch bet�tigt werden muss. 
			
			ConfirmDialog confirmDialog = ConfirmDialog.createDialog(getActivity());
			confirmDialog.setMessage("You ordered:\n" + Database.Variable.getTasteOrder() + "\nat\n" + Database.Variable.getSeatOrder());
			confirmDialog.show();
			

		}
		
	}
	
}
