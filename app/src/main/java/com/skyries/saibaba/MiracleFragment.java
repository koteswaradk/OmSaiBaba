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

public class MiracleFragment extends ListFragment {
	private InterstitialAd interstitial;
	public MiracleFragment(){}
	/*String[] miracle_titles = new String[] { "Lighting Lamps With Water", "Premonition Of Burning Fields ", "Stopping The Rain", "Raising The Water Level In Well",  
		    "Saving a Child From Drowning", "Flow of Godavari(river)From Baba's Feet", "Other Miracles"};  
		  String[]miracle_details  = new String[] { "Long before Sai Baba's fame spread, he was fond of burning lights in his Masjid and other Temples. But for the oil needed in those little earthenware lights that he lit, he depended on the generosity of the grocers of Shirdi. He had made it a rule to light earthenware lamps in the masjid every evening and he would call on the grocers for small donations. But there came a time when the grocers got tired of giving oil free to Sai Baba and one day they bluntly refused to oblige him, saying they had no fresh stocks. Without a word of protest Sai Baba returned to the masjid. Into those earthenware lamps he poured water and lighted the wicks. The lamps continued to burn deep into the midnight. The matter came to the notice of the grocers who now came to Sai Baba with profuse apologies. Wouldn't Sai Baba kindly pardon them? Sai Baba pardoned them, but he warned them never to lie again. You could have refused to give me the oil, but did you have to say that you didn't have fresh stocks? he admonished them. But he had made his point", "2", "3", "4", "5", "6", "7",  
		    "8", "9", "10", "11", "12", "13", "14", "15" };*/  
		  
		  @Override  
		  public void onListItemClick(ListView l, View v, int position, long id) {  
		  Toast.makeText(getActivity(), getActivity().getResources().getStringArray(R.array.miracle_titles)[position], Toast.LENGTH_SHORT).show();  
			  //getActivity().getResources().getStringArray(R.array.miracle_titles)
			  Intent launchingIntent = new Intent(getActivity(),MiracleDetailsActivity.class);
			  launchingIntent.putExtra("position", getActivity().getResources().getStringArray(R.array.miracle_details)[position]);
			  
			  getActivity().startActivity(launchingIntent);
		  }  
		
		  @Override  
		  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
		    Bundle savedInstanceState) {  
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,  getActivity().getResources().getStringArray(R.array.miracle_titles));

			  setListAdapter(adapter);
		 /*  TelephonyManager mgr = (TelephonyManager) getActivity().getSystemService(
	 				Context.TELEPHONY_SERVICE);
	    	 AdView mAdView = (AdView)container.findViewById(R.id.adView);
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
