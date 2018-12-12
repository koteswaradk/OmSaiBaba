package com.skyries.saibaba;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MiracleDetailsActivity extends Activity implements OnClickListener{
	String s_value;
	private InterstitialAd interstitial;
	ImageButton miracle_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.miracledetails);
		TextView tv=(TextView)findViewById(R.id.textView1);
		findViewById(R.id.miracle_back_Button).setOnClickListener(this);
		Intent data=getIntent();
		 s_value=data.getStringExtra("position");
		tv.setText(s_value);
		TelephonyManager mgr = (TelephonyManager) MiracleDetailsActivity.this.getSystemService(
 				Context.TELEPHONY_SERVICE);
    	 AdView mAdView = (AdView)findViewById(R.id.adView);
 		// Prepare the Interstitial Ad
 		interstitial = new InterstitialAd(MiracleDetailsActivity.this);
 		// Insert the Ad Unit ID
 		interstitial.setAdUnitId(getResources().getString(
 				R.string.admobi_adUid_interstitial_add));
 		final TelephonyManager tm = (TelephonyManager) getBaseContext()
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
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.miracle_back_Button:
			dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
			dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
		finish();
			break;

		
		}
	}
	
	
	

}
