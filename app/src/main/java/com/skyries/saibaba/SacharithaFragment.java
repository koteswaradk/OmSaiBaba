package com.skyries.saibaba;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;




import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SacharithaFragment extends Fragment implements OnClickListener {
	private InterstitialAd interstitial;
	public SacharithaFragment(){}
	View rootView;
	Button b;
	private File pdfpath;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
         rootView = inflater.inflate(R.layout.fragment_sacharithe, container, false);
      
        b=(Button)rootView.findViewById(R.id.b_sacharithe);
       b.setOnClickListener(SacharithaFragment.this);
       TelephonyManager mgr = (TelephonyManager) getActivity().getSystemService(
				Context.TELEPHONY_SERVICE);
   	 AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(getContext());
		// Insert the Ad Unit ID
		interstitial.setAdUnitId(getResources().getString(
				R.string.admobi_adUid_interstitial_add));
		final TelephonyManager tm = (TelephonyManager) getContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceid = tm.getDeviceId();
		AdRequest adRequest = new AdRequest.Builder()
		//.addTestDevice(deviceid)
				.build();
		mAdView.loadAd(adRequest);
		//interstitial.loadAd(adRequest);
		interstitial.setAdListener(new AdListener() {
			public void onAdLoaded() {
				// Call displayInterstitial() function
				// interstitial.show();
				displayInterstitial();
			}

			private void displayInterstitial() {
				// TODO Auto-generated method stub
				if (interstitial.isLoaded()) {
					interstitial.show();
				}
			}
		});
        
        return rootView;
        
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b_sacharithe:
			CallPDF();
			break;

		default:
			break;
		}
	}
	private void CallPDF() {
		// TODO Auto-generated method stub
		File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/SaiBabaPDf");
		if(wallpaperDirectory.exists()){
			
		}
		else{
			wallpaperDirectory.mkdirs();
			
		}

		String destFile = wallpaperDirectory.getAbsolutePath().concat("/sai.pdf");
pdfpath = new File(destFile);
		if(pdfpath.exists()){
		
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(pdfpath), "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		}
		else{
			  new LongOperation().execute("");

	
	}
	}
	 private class LongOperation extends AsyncTask<String, Void, String> {

		    

			private ProgressDialog pd;

			@Override
	        protected String doInBackground(String... params) {
	    		try {
	    		    InputStream in = getActivity().getAssets().open("shrisaisatcharitra.pdf");
	    		    System.out.println(""+pdfpath);
	    						        OutputStream out = new FileOutputStream(pdfpath);

	    						        byte[] buf = new byte[1024];
	    						        int len;
	    						        while ((len = in.read(buf)) > 0) {
	    						            out.write(buf, 0, len);
	    						        }
	    						        in.close();
	    						        out.close();
	    						        System.out.println("File copied.");
	    						    } catch (FileNotFoundException ex) {
	    						        System.out
	    						                .println(ex.getMessage() + " in the specified directory.");
	    						        System.exit(0);
	    						    } catch (IOException e) {
	    						        System.out.println(e.getMessage());
	    						    }
	            return "Executed";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	        	if(pd!=null){
	        		pd.dismiss();
		        	Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(pdfpath), "application/pdf");
					intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
	        	}
	        	
	        }

	        @Override
	        protected void onPreExecute() {
	        	pd = new ProgressDialog(getActivity());
				pd.setTitle("Processing...");
				pd.setMessage("Please wait.");
				pd.setCancelable(false);
				pd.setIndeterminate(true);
				pd.show();
	        }

	        @Override
	        protected void onProgressUpdate(Void... values) {}
	    }

	
	
}
