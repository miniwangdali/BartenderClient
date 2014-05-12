package com.artificialarm.bartenderclient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import com.artificialarm.bartenderclient.ui.SendData;
import com.example.bartenderclient.R;

import Database.Variable;
import android.os.Bundle;
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
    BluetoothAdapter btAdapter;
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> BTArrayAdapter;
    BluetoothDevice selecteddevice;
    static OutputStream mmOutputStream;
	
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentManager fManager = getFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.add(R.id.homepage_fragment, new Fragment_HomePage());
		fTransaction.commit();
		
		setContentView(R.layout.activity_main);
		
	/*		
		//aktualisiert immer die Kategorien
		String array2[] ={"a","b","c"};
		SharedPreferences sp3=PreferenceManager.getDefaultSharedPreferences(this);
		array2[0] = sp3.getString("NAME_CATEGORY0", "not set");
		array2[1] = sp3.getString("NAME_CATEGORY1", "not set");
		array2[2] = sp3.getString("NAME_CATEGORY2", "not set");
		// wenn die Kategorien noch nicht geändert worden sind, dann wird nichts gemacht, wenn schon dann setzt er sie aktuell
		if(!array2[0].equals("not set")){		
			Variable.setCategory(array2);
		}else{
		}
		
	*/	
		
		
		
		// Bluetooth!!!!!!!!!!!!!!!!!!!!!!
		
		 // take an instance of BluetoothAdapter - Bluetooth radio
	      btAdapter = BluetoothAdapter.getDefaultAdapter();
	      
	      getBluetoothAdapter();
	      
	      enableBluetooth();
		
	      find();
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
	   
		// Verbindung bekommen
	   
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
	     
	   public void find() {
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
		   
		    	BluetoothSocket mmSocket;
		        BluetoothSocket tmp = null;
		        tmp = selecteddevice.createRfcommSocketToServiceRecord(MY_UUID);
		        mmSocket = tmp;
		        mmSocket.connect();

		        // Do work to manage the connection (in a separate thread)
		        mmOutputStream = mmSocket.getOutputStream();
		        Toast.makeText(getApplicationContext(), "Now your device is connected", Toast.LENGTH_LONG).show();
		        
		}
	   
	   

	   // Daten senden
	   	   
	  public static void sendData() throws IOException
	    {
	     //   String msg = "111";
	     //   mmOutputStream.write(msg.getBytes());
	        SendData convertedData = new SendData();
	        String send = convertedData.convertData();
	        
	        Log.d("TAGMICHI_1", Variable.getSeatOrder());
	        Log.d("TAGMICHI_2", Variable.getSizeOrder());
	        Log.d("TAGMICHI_3", Variable.getTasteOrder());
	        Log.d("TAGMICHI", send);
	        
	        mmOutputStream.write(send.getBytes());
	     
	    }
	   

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
