package com.skyries.saibaba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;

public class OtherMantraFragment extends ListFragment {
	private InterstitialAd interstitial;
	public OtherMantraFragment(){}
	
		  @Override  
		  public void onListItemClick(ListView l, View v, int position, long id) {  
		  Toast.makeText(getActivity(), getActivity().getResources().getStringArray(R.array.other_mantra_titles)[position], Toast.LENGTH_SHORT).show();  
			  //getActivity().getResources().getStringArray(R.array.miracle_titles)
			  Intent launchingIntent = new Intent(getActivity(),OtherMantraDetails.class);
			  launchingIntent.putExtra("position", getActivity().getResources().getStringArray(R.array.other_mantra_details)[position]);
			  launchingIntent.putExtra("titles", getActivity().getResources().getStringArray(R.array.other_mantra_titles)[position]);
			  getActivity().startActivity(launchingIntent);

		  }  
		
		  @Override  
		  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
		    Bundle savedInstanceState) {  
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,  getActivity().getResources().getStringArray(R.array.other_mantra_titles));  
		   setListAdapter(adapter);
		 
	    	/* AdView mAdView = (AdView) container.findViewById(R.id.adView);
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
	 		interstitial.loadAd(adRequest);
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
	 		});*/

		   return super.onCreateView(inflater, container, savedInstanceState);  
		  }  
		
		 
}
