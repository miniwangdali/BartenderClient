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

public class BluetoothConnectTask extends AsyncTask<String, String, String> {
	

    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	Activity mActivity;
	CustomProgressDialog mDialog;
	BluetoothAdapter btAdapter;
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> BTArrayAdapter;
    BluetoothDevice selecteddevice;
    static OutputStream mmOutputStream;

	public BluetoothConnectTask(Activity activity, CustomProgressDialog dialog, BluetoothAdapter adapter) {
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
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		find();
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result){
		mDialog.dismiss();

		
		super.onPostExecute(result);
	}
	
	public void getConnection() throws IOException{
		   
		BluetoothSocket mmSocket;
		BluetoothSocket tmp = null;
		tmp = selecteddevice.createRfcommSocketToServiceRecord(MY_UUID);
		mmSocket = tmp;
		mmSocket.connect();

		        // Do work to manage the connection (in a separate thread)
		mmOutputStream = mmSocket.getOutputStream();
		        Toast.makeText(mActivity, "Now your device is connected", Toast.LENGTH_LONG).show();
		        
	}
	
	public void find() {
		   if (btAdapter.isDiscovering()) {
		       // the button is pressed when it discovers, so cancel the discovery
		       btAdapter.cancelDiscovery();
		   }else{
		       btAdapter.startDiscovery();
		             
		       mActivity.registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));   
		   }   
		}
	
	final BroadcastReceiver bReceiver = new BroadcastReceiver() {
     public void onReceive(Context context, Intent intent) {
         String action = intent.getAction();
         // When discovery finds a device
         if (BluetoothDevice.ACTION_FOUND.equals(action)) {
              // Get the BluetoothDevice object from the Intent
              BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);                 
              
              if (device.getName().equals("HC-06")){
             	 
             	 selecteddevice=device;
             	 
             	 try {
						getConnection();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
              }
          
         }
     }
 };

}
