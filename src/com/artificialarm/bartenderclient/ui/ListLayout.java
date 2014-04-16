package com.artificialarm.bartenderclient.ui;

import com.example.bartenderclient.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListLayout extends LinearLayout {
	
	private TextView tasteText;
	private String taste = new String();

	public ListLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		((Activity)getContext()).getLayoutInflater().inflate(R.layout.listline, this);
		initialLayout();
	}

	private void initialLayout() {
		// TODO Auto-generated method stub
		View view = this;
		tasteText = (TextView)view.findViewById(R.id.tasteTxt);
		tasteText.setText(taste);
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
		initialLayout();
	}

}
