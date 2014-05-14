package com.artificialarm.bartenderclient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.artificialarm.bartenderclient.ui.CustomProgressDialog;
import com.example.bartenderclient.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class BluetoothCheckTask extends AsyncTask<String, Integer, String> {
	
	private static final int REQUEST_ENABLE_BT = 1;

	Activity mActivity;
	CustomProgressDialog mDialog;
	BluetoothAdapter btAdapter;

	public BluetoothCheckTask(Activity activity, CustomProgressDialog dialog, BluetoothAdapter adapter) {
		// TODO Auto-generated constructor stub
		mActivity = activity;
		mDialog = dialog;
		btAdapter = adapter;
	}
	
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
		//btAdapter = BluetoothAdapter.getDefaultAdapter();
	      
	    getBluetoothAdapter();
	    
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(String result){
		enableBluetooth();
		mDialog.dismiss();
		//new BluetoothConnectTask(mActivity, mDialog, btAdapter);
		FragmentManager fManager = mActivity.getFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.add(R.id.homepage_fragment, new Fragment_HomePage());
		fTransaction.commit();
		
		super.onPostExecute(result);
	}
	
	private void getBluetoothAdapter() {
		
		//get Bluetooth Adapter
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		
		//check if the device support Bluetooth, otherwise make a Toast
		if (btAdapter == null){
			Toast.makeText(mActivity, "bluetooth is not supported", Toast.LENGTH_SHORT).show();
			//mActivity.finish();
		}
	}

	public void enableBluetooth(){
		if (!btAdapter.isEnabled()) {
			Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			mActivity.startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
 
			Toast.makeText(mActivity,"Bluetooth turned on" ,
                 Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(mActivity,"Bluetooth is already on",
                 Toast.LENGTH_SHORT).show();
		}
	}
	

}
