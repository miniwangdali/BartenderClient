package com.artificialarm.bartenderclient;

import java.util.List;

import com.example.bartenderclient.R;

import android.content.Context;
import android.inputmethodservice.Keyboard.Row;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {

	private List<String> choices;
	private Context context;
	
	
	public SpinnerAdapter(Context context, List<String> choices) {
		// TODO Auto-generated constructor stub
		this.choices = choices;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return choices.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return choices.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View row = convertView;
		row = inflater.inflate(R.layout.spinneritem, null);
		if(row != null){
			TextView text = (TextView)row.findViewById(R.id.tasteTxt);
			text.setText(choices.get(position));
		}
		return row;
	}

}
