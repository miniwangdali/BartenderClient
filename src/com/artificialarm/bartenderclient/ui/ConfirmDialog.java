package com.artificialarm.bartenderclient.ui;

import java.io.IOException;

import com.artificialarm.bartenderclient.Fragment_HomePage;
import com.artificialarm.bartenderclient.Fragment_SeatPage;
import com.artificialarm.bartenderclient.MainActivity;
import com.example.bartenderclient.R;
import Database.Variable;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class ConfirmDialog extends Dialog {
	private Context		context		= null;
	private static ConfirmDialog	confirmDialog	= null;

	public ConfirmDialog(Context context) {
		super(context);
		this.context = context;
	}

	public ConfirmDialog(Context context, int theme) {
		super(context, theme);
	}

	public static ConfirmDialog createDialog(final Context context) {
		
		confirmDialog = new ConfirmDialog(context,
				R.style.ConfirmDialog);
		confirmDialog.setContentView(R.layout.confirmdialog);
		confirmDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		
		ImageButton confirmButton = (ImageButton)confirmDialog.findViewById(R.id.confirmBtn);
		confirmButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// das wird beim Confirm-Button geklickt gemacht:
				
				// Zusatzfunktionen
				if (Variable.getTasteOrder().equals("clean") || Variable.getTasteOrder().equals("stop") ||Variable.getTasteOrder().equals("continue") ||Variable.getTasteOrder().equals("openstorage") ||Variable.getTasteOrder().equals("closestorage")){
					
					// Anweisung, wenn der Clean-Button zuvor ausgewï¿½hlt wurde!
					
					// sendet die Bestellung per Bluetooth zum Arduino
					try {
						MainActivity.sendData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Schlieï¿½t das Dialogfenster
					confirmDialog.dismiss();
					
					// geht auf die HomePage zurück, wenn
					if(Variable.getTasteOrder().equals("clean")){
						
						FragmentTransaction ft = ((Activity)context).getFragmentManager().beginTransaction();
						Fragment_HomePage fragment_HomePage = new Fragment_HomePage();
						ft.replace(R.id.homepage_fragment, fragment_HomePage);
						MainActivity.setPage(1);
						ft.commit();

					}
		
					
				}
				// refill
				else if( Variable.getRefill()==2){
					
					confirmDialog.dismiss();
					Variable.setRefill(1);
					
				}
				else if( Variable.getRefill()==1){
					
					// schreib das bestellte Getrï¿½nk und die Zeit in die Datenbank
					Database.Time time = new Database.Time();
					
					Database.DatabaseOpenHelper db = new Database.DatabaseOpenHelper(context);
					db.writeInDatabase(new Variable(Variable.getTasteOrder(), time.getcurrentTime()));
					
					
					// sendet die Bestellung per Bluetooth zum Arduino
					try {
						
						MainActivity main = new MainActivity();
						main.sendData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Schlieï¿½t das Dialogfenster
					confirmDialog.dismiss();
					
					FragmentTransaction ft = ((Activity)context).getFragmentManager().beginTransaction();
					Fragment_HomePage fragment_HomePage = new Fragment_HomePage();
					ft.replace(R.id.homepage_fragment, fragment_HomePage);
					MainActivity.setPage(1);
					ft.commit();
					
				}
				
				
				// normale Bestellung
				else{
				// schreib das bestellte Getrï¿½nk und die Zeit in die Datenbank
				Database.Time time = new Database.Time();
				
				Database.DatabaseOpenHelper db = new Database.DatabaseOpenHelper(context);
				db.writeInDatabase(new Variable(Variable.getTasteOrder(), time.getcurrentTime()));
				
				
				// sendet die Bestellung per Bluetooth zum Arduino
				try {
					
					MainActivity main = new MainActivity();
					main.sendData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Schlieï¿½t das Dialogfenster
				confirmDialog.dismiss();
				
				FragmentTransaction ft = ((Activity)context).getFragmentManager().beginTransaction();
				Fragment_HomePage fragment_HomePage = new Fragment_HomePage();
				ft.replace(R.id.homepage_fragment, fragment_HomePage);
				MainActivity.setPage(1);
				ft.commit();

				}
/*			
 * wurde in die einzelnen if schleifen eingebunden
 * 	
				FragmentTransaction ft = ((Activity)context).getFragmentManager().beginTransaction();
				Fragment_HomePage fragment_HomePage = new Fragment_HomePage();
				ft.replace(R.id.homepage_fragment, fragment_HomePage);
				MainActivity.setPage(1);
				ft.commit();
*/				
			}
		});
		
		
		
		return confirmDialog;
	}
	
	


	public ConfirmDialog setTitle(String strTitle) {
		return confirmDialog;
	}

	public ConfirmDialog setMessage(String strMessage) {
		TextView loadingMsg = (TextView) confirmDialog
				.findViewById(R.id.confirmTxt);

		if (loadingMsg != null) {
			loadingMsg.setText(strMessage);
		}

		return confirmDialog;
	}
}
