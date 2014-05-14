package com.artificialarm.bartenderclient.ui;

import java.io.IOException;
import com.artificialarm.bartenderclient.MainActivity;
import com.example.bartenderclient.R;
import Database.Variable;
import android.app.Dialog;
import android.content.Context;
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
				
				if (Variable.getTasteOrder().equals("clean") || Variable.getTasteOrder().equals("stop") ||Variable.getTasteOrder().equals("continue")){
					
					// Anweisung, wenn der Clean-Button zuvor ausgew�hlt wurde!
					
					// sendet die Bestellung per Bluetooth zum Arduino
					try {
						MainActivity.sendData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Schlie�t das Dialogfenster
					confirmDialog.dismiss();
				}

				else{
				// schreib das bestellte Getr�nk und die Zeit in die Datenbank
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
				
				// Schlie�t das Dialogfenster
				confirmDialog.dismiss();
				}
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
