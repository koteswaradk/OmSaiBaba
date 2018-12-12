package com.skyries.saibaba;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LiveDarshaFragment extends Fragment implements OnClickListener{
	View rootView;
	Button b;
	ConnectivityManager connec;
	 ConnectivityManager connectivity;
	 NetworkInfo wifiInfo, mobileInfo;
	 WifiManager wifimanager;
	 AlertDialog connectdialog;
	 boolean condition;
	public LiveDarshaFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
         rootView = inflater.inflate(R.layout.fragment_livedarshahna, container, false);
      
        b=(Button)rootView.findViewById(R.id.b_livedarshana);
       b.setOnClickListener(LiveDarshaFragment.this);
       connectdialog=new AlertDialog.Builder(getActivity()).create();
        return rootView;
        
    }
	
	public boolean CheckInternet() 
	{
	    ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    if (wifi.isConnected()||mobile.isConnected()) {
	        return true;
	    } 
	    return false;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.b_livedarshana:
			 condition= CheckInternet();
			 if (condition) {
				
				/* Intent i=new Intent(Intent.ACTION_MAIN);

				 PackageManager manager = getPackageManager();

				 i = manager.getLaunchIntentForPackage("latify.apk");//apk name

				 i.addCategory(Intent.ACTION_DEFAULT);

				 startActivity(i);*/
				 /*Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://m.google.com/latitude"));
					 		intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
					 		startActivity(intent);*/
					 		
					 		//Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("latitude://latitude/friends/location/example@gmail.com"));
				 Intent launchingIntent = new Intent(getActivity(),LiveDarshana.class);
				  getActivity().startActivity(launchingIntent);
				
				
			 }
			
			 else  {
					LayoutInflater inflater = getActivity().getLayoutInflater();
			    	 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup)getActivity().findViewById(R.id.toast_layout_root));

			    	 TextView text = (TextView) layout.findViewById(R.id.text);
			    	 text.setText("Not Connected To Internet Your Data Connection is Unavilable ");
	               text.setTextSize(20);
			    	 Toast toast = new Toast(getActivity());
			    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			    	 toast.setDuration(Toast.LENGTH_LONG);
			    	 toast.setView(layout);
			    	 toast.show();
				
				
				connectdialog.setTitle("No Network is Connected Connect to your WiFi Or Data Pack");
				connectdialog.setCanceledOnTouchOutside(false);
				
				/*connectdialog.setButton("ConnectTo Wifi", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						WifiLoginAsynchTask wifienable=new WifiLoginAsynchTask();
						wifienable.execute();
						
						
		               
						
					}
				});
				connectdialog.setButton2( "Connect to Data Pack", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub					
						MobileLoginAsynchTask mobcheck=new MobileLoginAsynchTask();
						mobcheck.execute();
						 
						
					}
				});
				connectdialog.setButton3( "Cancel", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
						
						
					}
				});
				connectdialog.show();
			}*/
			
			break;

		
		}
		
	}
	
	/*class  WifiLoginAsynchTask extends AsyncTask<Void, ProgressBar, Boolean>{

		Boolean scucess=false;
		 ProgressDialog dialog;
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 for(int i=0;i<20;i++){
				 getActivity().setProgress(5);

	                try {
	                	connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	        		    wifiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	        		    wifimanager=(WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
	        		    wifimanager.startScan();
	        		    scucess=wifimanager.setWifiEnabled(true);
	        		    
	                
	        		    if (scucess) {
	        		    	 System.out.println("returned true"+scucess);
	        		    	 return true;
	        		    	
						}
	                    Thread.sleep(1000);// the timing set to a large value
	                } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	              
	            }
	             	            
			 }
			 System.out.println("returned false"+scucess);
			 return false;
			
			
			
		}
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 dialog = new ProgressDialog(getActivity());
	            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            dialog.setTitle(" Please Wait WiFi is Connecting.....");
	            dialog.setMax(500);
	            dialog.setCancelable(false);
	            dialog.show();
	            System.out.println(" progress dislaed");
		}
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			 System.out.println("onpost execute");
			dialog.dismiss();
		}

		
		
		
	}
	class  MobileLoginAsynchTask extends AsyncTask<Void, ProgressBar, Boolean>{
		 Boolean mobil;
		 ProgressDialog dialog;
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 for(int i=0;i<20;i++){
				 getActivity().setProgress(5);

	                try {
	                	 mobil=setMobileDataEnabled(getActivity(),true);
	                	
	                    Thread.sleep(1000);// the timing set to a large value
	                } catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	              
	            }
	                if (mobil) {
	                	dialog.dismiss();
	                	return true;
					}else{
						LayoutInflater inflater = getActivity().getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup)getActivity().findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("not connected Check your Data Pack");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getActivity());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
						
					}
			 
	            
			 }
			
			 return false;
			
		}	
		private Boolean setMobileDataEnabled(Context context, boolean enabled) {
		    final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    try {
		    	final Class conmanClass = Class.forName(conman.getClass().getName());
		 	    final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
		 	    iConnectivityManagerField.setAccessible(true);
		 	    final Object iConnectivityManager = iConnectivityManagerField.get(conman);
		 	    final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
		 	    final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		 	    setMobileDataEnabledMethod.setAccessible(true);

		 	    setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
				
			} catch (Exception e) {
				// TODO: handle exception
				LayoutInflater inflater = getActivity().getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) getActivity().findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("SetUp Your Data Pack ");
	          text.setTextSize(20);
		    	 Toast toast = new Toast(getActivity());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
			}
			return true;
		   
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 
			    dialog = new ProgressDialog(getActivity());
	            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            dialog.setTitle(" Please Wait Data Pack is Connecting.....");
	            dialog.setMax(500);
	            dialog.setCancelable(false);
	            dialog.show();
		}*/

		
		
		
	}
	
}
