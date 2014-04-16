package com.artificialarm.bartenderclient.ui;

import com.example.bartenderclient.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmDialog extends Dialog {
	private Context						context					= null;
	private static ConfirmDialog	confirmDialog	= null;

	public ConfirmDialog(Context context) {
		super(context);
		this.context = context;
	}

	public ConfirmDialog(Context context, int theme) {
		super(context, theme);
	}

	public static ConfirmDialog createDialog(Context context) {
		confirmDialog = new ConfirmDialog(context,
				R.style.ConfirmDialog);
		confirmDialog.setContentView(R.layout.confirmdialog);
		confirmDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		
		ImageButton confirmButton = (ImageButton)confirmDialog.findViewById(R.id.confirmBtn);
		confirmButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				confirmDialog.dismiss();
				
			}
		});
		return confirmDialog;
	}
	
	


	public ConfirmDialog setTitile(String strTitle) {
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
