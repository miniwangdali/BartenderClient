package com.artificialarm.bartenderclient.ui;

import com.example.bartenderclient.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomProgressDialog extends Dialog {
	private Context						context					= null;
	private static CustomProgressDialog	customProgressDialog	= null;

	public CustomProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CustomProgressDialog createDialog(Context context) {
		
		// erzeugt den Dialog
		
		customProgressDialog = new CustomProgressDialog(context,
				R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.progressdialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return customProgressDialog;
	}
	
	
	public void onWindowFocusChanged(boolean hasFocus) {

		// gibt die verschiedenen wartezeichen aus
		
		if (customProgressDialog == null) {
			return;
		}
		
		ImageView loadingImage = (ImageView) customProgressDialog
				.findViewById(R.id.loadingImage);
		
		AnimationDrawable animationDrawable = (AnimationDrawable) loadingImage
				.getBackground();
		animationDrawable.start();

	}

	public CustomProgressDialog setTitle(String strTitle) {
		return customProgressDialog;
	}

	public CustomProgressDialog setMessage(String strMessage) {
		TextView loadingMsg = (TextView) customProgressDialog
				.findViewById(R.id.msgTxt);
		if(loadingMsg != null){
			loadingMsg.setText(strMessage);
		}

		return customProgressDialog;
	}
}
