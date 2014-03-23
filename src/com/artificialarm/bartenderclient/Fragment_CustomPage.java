package com.artificialarm.bartenderclient;

import com.artificialarm.bartenderclient.common.Variable;
import com.example.bartenderclient.R;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_CustomPage extends ListFragment {
	private String[] taste;
	
	TextView tasteTxt;
	
	public Fragment_CustomPage(){
		this.taste = Variable.getTaste();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_custompage, container, false);
		
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setListAdapter(new CustomListAdapter());
	}
	class CustomListAdapter extends ArrayAdapter<String> {
		public CustomListAdapter() {
			// TODO Auto-generated constructor stub
			super(getActivity(), R.layout.listline, taste);
		}
		public View getView(int position,View convertView, ViewGroup parent){
			View row = convertView;
			if(row==null){
				LayoutInflater inflater=getActivity().getLayoutInflater();
				row=inflater.inflate(R.layout.listline,parent, false);
			}
			tasteTxt = (TextView)row.findViewById(R.id.tasteTxt);
			tasteTxt.setText(taste[position]);
			
			
			return(row);
		}
	}
	public void onListItemClick(ListView parent, View v, int position, long id) {
		Fragment_Orderlist orderlist = (Fragment_Orderlist)getActivity().getFragmentManager().findFragmentById(R.id.orderlist_fragment);
		Button orderButton = (Button)orderlist.getView().findViewById(R.id.beverageOrderButton);
		orderButton.setText(taste[position]);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment_SeatPage fragment_SeatPage = new Fragment_SeatPage();
		ft.replace(R.id.homepage_fragment, fragment_SeatPage);
		ft.addToBackStack(null);
		ft.commit();
	}
}
