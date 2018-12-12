package com.skyries.saibaba;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class BhajanaFragment extends Fragment implements OnCheckedChangeListener, OnClickListener {
	private MediaPlayer mp;
	int counter;
	private InterstitialAd interstitial;
	RadioGroup number_group_select;
	ToggleButton toggelbutton;
	ImageButton stopButton;
	RadioButton radio_11, radio_21, radio_51, radio_108;
@Override
@Nullable
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	  View rootView = inflater.inflate(R.layout.fragment_bhajana, container, false);
	  number_group_select = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		toggelbutton = (ToggleButton) rootView.findViewById(R.id.toggle_Button);
		stopButton = (ImageButton) rootView.findViewById(R.id.stop);
		
		number_group_select.setOnCheckedChangeListener(this);
		stopButton.setOnClickListener(this);
		toggelbutton.setOnClickListener(this);
		mp=MediaPlayer.create(getActivity(), R.raw.saving);
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
public void onCheckedChanged(RadioGroup group, int checkedId) {
	// TODO Auto-generated method stub
	switch (checkedId) {
	case R.id.button_11:
		counter = 10;
		mp=MediaPlayer.create(getActivity(), R.raw.saving);

		break;
	case R.id.button_21:
		counter = 20;
		mp=MediaPlayer.create(getActivity(), R.raw.saving);
		break;
	case R.id.button_51:
		counter = 50;
		mp=MediaPlayer.create(getActivity(), R.raw.saving);
		break;
	case R.id.button_108:
		counter = 107;
		mp=MediaPlayer.create(getActivity(), R.raw.saving);
		break;

	}
}
void player(final int yourCount) {

	mp.setOnCompletionListener(new OnCompletionListener() {
		int count = 0;

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub

			if (count < yourCount) {

				Log.i("log", "" + count);
				mp.start();
				count++;
			

				if (count == yourCount) {
					toggelbutton.setBackgroundResource(R.drawable.subplay);
					number_group_select.clearCheck();
					counter = 0;
					stopButton.setVisibility(View.INVISIBLE);
					
					

				}
			}

		}

	});
	if (counter != 0) {
		mp.start();

	}

}
@Override
public void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	if (null != mp) {
		mp.release();

	}
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub

	switch (v.getId()) {

	case R.id.toggle_Button:

		if (counter != 0) {

			if (mp != null && mp.isPlaying()) {
				mp.pause();
				toggelbutton.setBackgroundResource(R.drawable.subplay);
				stopButton.setVisibility(View.INVISIBLE);
			} else {
				player(counter);
				toggelbutton.setBackgroundResource(R.drawable.pause);
				stopButton.setVisibility(View.VISIBLE);
			}
		} else {
			toggelbutton.setBackgroundResource(R.drawable.subplay);
			Toast.makeText(getActivity(),
					"select the nuber of times", Toast.LENGTH_SHORT).show();
		}

		break;
	case R.id.stop:
		if (mp != null && mp.isPlaying()) {
			Log.d("TAG------->", "player is running");
			mp.stop();
			Log.d("Tag------->", "player is stopped");
			mp.release();
			Log.d("TAG------->", "player is released");

			toggelbutton.setBackgroundResource(R.drawable.subplay);

			number_group_select.clearCheck();
			counter=0;
			stopButton.setVisibility(View.INVISIBLE);
		}

		break;

	}
}


}
