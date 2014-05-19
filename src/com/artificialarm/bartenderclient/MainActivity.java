package com.artificialarm.bartenderclient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.artificialarm.bartenderclient.ui.ConfirmDialog;
import com.artificialarm.bartenderclient.ui.CustomProgressDialog;
import com.artificialarm.bartenderclient.ui.SendData;
import com.example.bartenderclient.R;

import Database.Variable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
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
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	// Bluetooth
    private static final int REQUEST_ENABLE_BT = 1;
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
   
    TextView text;
    Button button;
    CustomProgressDialog customProgressDialog;
    BluetoothAdapter btAdapter;
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> BTArrayAdapter;
    BluetoothDevice selecteddevice;
    static OutputStream OutputStream;
    java.io.InputStream InputStream;
    
	
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentManager fManager = getFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.add(R.id.homepage_fragment, new Fragment_HomePage());
		fTransaction.commit();
		
		setContentView(R.layout.activity_main);
		
		
		// Dialog mit dem Wartezeichen fürs Verbinden
		customProgressDialog = CustomProgressDialog.createDialog(this);
		customProgressDialog.setMessage("connecting to the bartender...");
		customProgressDialog.show();
			
		
		// Bluetooth!!!!!!!!!!!!!!!!!!!!!!
		
		 // take an instance of BluetoothAdapter - Bluetooth radio

		btAdapter = BluetoothAdapter.getDefaultAdapter();
		
		//new BluetoothCheckTask(this, customProgressDialog, btAdapter).execute("");
		
		getBluetoothAdapter();
		enableBluetooth();
	
		
		// wenn noch verbunden wird, kann mann nicht auf die app zugreifen
		
		Thread connectThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				find();
			}
		});
		connectThread.start();
	
		
	 //   find();		// alte Version mit nicht ladezeichen und mit zugriff beim laden
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	       // TODO Auto-generated method stub
	       if(requestCode == REQUEST_ENABLE_BT){
	           if(btAdapter.isEnabled()) {
	               text.setText("Status: Enabled");
	           } else {  
	               text.setText("Status: Disabled");
	           }
	       }
	   }
	
	// Bluetooth zulassen
	
	private void getBluetoothAdapter() {
			
			//get Bluetooth Adapter
			btAdapter = BluetoothAdapter.getDefaultAdapter();
			
			//check if the device support Bluetooth, otherwise make a Toast
			if (btAdapter == null){
				Toast.makeText(getApplicationContext(), "bluetooth is not supported", Toast.LENGTH_SHORT).show();
				finish();
			}
	   }
	
	public void enableBluetooth(){
	      if (!btAdapter.isEnabled()) {
	         Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	         startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
	 
	         Toast.makeText(getApplicationContext(),"Bluetooth turned on" ,
	                 Toast.LENGTH_SHORT).show();
	      }
	      else{
	         Toast.makeText(getApplicationContext(),"Bluetooth is already on",
	                 Toast.LENGTH_SHORT).show();
	      }
	   }

	
	   
	   
		// Verbindung bekommen
	   
	// Discovering devices
	   final BroadcastReceiver bReceiver = new BroadcastReceiver() {
		   
	        public void onReceive(Context context, Intent intent) {
	            String action = intent.getAction();
	            // When discovery finds a device
	            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	                 // Get the BluetoothDevice object from the Intent
	                 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);                 
	                 
	                 
	                 // Falls es das HC-06 findet, soll es sich verbinden
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
	            // Anweisung, wenn kein Gerät gefunden wird
	            else{
	            	/*if(customProgressDialog != null){
	            		customProgressDialog.dismiss();
	            	}
			        Toast.makeText(getApplicationContext(), "Sorry, your device is disconnected", Toast.LENGTH_LONG).show();*/
	            	
	            	// schreibt in msg.what eine eins um zu sagen, dass das Gerät nicht verbunden ist
	            	
	            	Message msg = new Message();
	            	msg.what = 1;
	            	handler.sendMessage(msg);
	            }
	        }
	    };
	     

	    
	    
	   public void find() {
		   
		   // Anweisung: wenn schon nach Geräten gesucht wird abbrechen und neu starten, sonst Discovery starten -> Broadcast Receiver
		   
	       if (btAdapter.isDiscovering()) {
	           // the button is pressed when it discovers, so cancel the discovery
	           btAdapter.cancelDiscovery();
	       }
	       else {
	            btAdapter.startDiscovery();
	             
	            registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));   
	        }   
	   }
	    
	   public void getConnection() throws IOException{
		   
		   // as Client
		   
		    	BluetoothSocket Socket;
		        BluetoothSocket tmp = null;
		        tmp = selecteddevice.createRfcommSocketToServiceRecord(MY_UUID);
		        Socket = tmp;
		        Socket.connect();

		        // Do work to manage the connection (in a separate thread)
		        OutputStream = Socket.getOutputStream();
		        
		        // InputStream
		        InputStream = Socket.getInputStream();		// daten empfangen
		             
		        
		  //      Toast.makeText(getApplicationContext(), "Now your device is connected", Toast.LENGTH_LONG).show();*/
		        
		        // schreibt die null in msg.what -> Handler, gibt aus das das Gerät verbunden ist
		        Message msg = new Message();
		        msg.what = 0;
		        handler.sendMessage(msg);
		}
	   
	   

	   // Daten senden
	   	   
	  public static void sendData() throws IOException
	    {

		  // schreibt in send die zu sendende Zahl, die in SendData gesendet wird
	        SendData convertedData = new SendData();
	        String send = convertedData.convertData();
	        
	        Log.d("TAGMICHI_1", Variable.getSeatOrder());
	        Log.d("TAGMICHI_2", Variable.getSizeOrder());
	        Log.d("TAGMICHI_3", Variable.getTasteOrder());
	        Log.d("TAGMICHI", send);
	     
	       // Sendet send
	        OutputStream.write(send.getBytes());
	     
	    }

	// Daten empfangen 
	  
	  public class ConnectedThread extends Thread {
		 

		  public void run() {
		        byte[] buffer = new byte[1024];  // buffer store for the stream
		        int bytes; // bytes returned from read()
		 
		        // Keep listening to the InputStream until an exception occurs
		        while (true) {
		            try {
		                // Read from the InputStream
		                bytes = InputStream.read(buffer);
		                // Send the obtained bytes to the UI activity
		                handler.obtainMessage(2, bytes, -1, buffer)
		                        .sendToTarget();
		            } catch (IOException e) {
		                break;
		            }
		        }
		    }

	  }

	// Ende Daten empfangen	
	  
	  
	  private Handler handler = new Handler(){
		  @Override
		  public void handleMessage(Message msg){
			  switch (msg.what) {
			  // Toast: Device is Connected
			case 0:
				if(customProgressDialog != null){
		        	customProgressDialog.dismiss();
		        }
		        Toast.makeText(getApplicationContext(), "Now your device is connected", Toast.LENGTH_LONG).show();
				break;
				
// daten empfangen				
				
			  // für das Erhalten von Daten
			case 2:
				// Anweisung
		        
				//Get the bytes from the msg.obj
				 byte[] readBuf = (byte[]) msg.obj;
				 // construct a string from the valid bytes in the buffer
				 String readMessage = new String(readBuf, 0, msg.arg1);
				
				 Log.d("TAGMICHI", readMessage);
				 
				 Toast.makeText(getApplicationContext(), readMessage, Toast.LENGTH_LONG).show();
				
				break;
				
// Ende daten empfangen				
				
				
			default:
				if(customProgressDialog != null){
            		customProgressDialog.dismiss();
            	}
		        Toast.makeText(getApplicationContext(), "Sorry, your device is disconnected", Toast.LENGTH_LONG).show();
				break;
			}
		  }
	  };
	   
	
}
