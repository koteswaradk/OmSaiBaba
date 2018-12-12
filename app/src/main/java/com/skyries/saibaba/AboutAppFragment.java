package com.skyries.saibaba;



import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutAppFragment extends Fragment {
	private InterstitialAd interstitial;
	public AboutAppFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView = inflater.inflate(R.layout.aboutapp, container, false);
         TextView  aboutapp = (TextView)rootView.findViewById(R.id.t_aboutapp);
	        
         aboutapp.setText( getActivity().getResources().getString(R.string.aboutapp));
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
 	//	interstitial.loadAd(adRequest);
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

}
