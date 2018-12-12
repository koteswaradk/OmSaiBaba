package com.skyries.saibaba;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AssuranceFragment extends Fragment {
	private InterstitialAd interstitial;
	public AssuranceFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_assurance, container, false);
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
}
