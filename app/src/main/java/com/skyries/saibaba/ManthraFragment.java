package com.skyries.saibaba;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ManthraFragment extends Fragment implements OnClickListener {
	private MediaPlayer mp = null;private PlayMusicService musicService;
	CountReceiver myReceiver;
	ImageButton play; private boolean mIsBound = false;
	private boolean press = true, change = true;
	TelephonyManager mgr;
	PhoneStateListener phoneStateListener;
	

	public ManthraFragment() {
	}

	private InterstitialAd interstitial;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_mantra, container,
				false);

		play = (ImageButton) rootView.findViewById(R.id.play);
		play.setOnClickListener(this);
		//mp = MediaPlayer.create(getActivity(), R.raw.sada);
		mgr = (TelephonyManager) getActivity().getSystemService(
				Context.TELEPHONY_SERVICE);
		/*
		 * if(mgr != null) { mgr.listen(phoneStateListener,
		 * PhoneStateListener.LISTEN_CALL_STATE); }
		 */
		AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(getContext());
		// Insert the Ad Unit ID
		interstitial.setAdUnitId(getResources().getString(
				R.string.admobi_adUid_interstitial_add));
		final TelephonyManager tm = (TelephonyManager) getContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		//String deviceid = tm.getDeviceId();
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
		doBindService();
		/*musicService.player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				play.setBackgroundResource(R.drawable.subplay);
			}
		});*/
		phoneStateListener = new PhoneStateListener() {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				super.onCallStateChanged(state, incomingNumber);
				if (state == TelephonyManager.CALL_STATE_RINGING) {
					musicService.player.pause();
					System.out.println("inside ringing");
					play.setBackgroundResource(R.drawable.subplay);
				} else if (state == TelephonyManager.CALL_STATE_IDLE) {
					System.out.println("inside idle");

				} else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
					// Pause music
					System.out.println("inside CALL_STATE_OFFHOOK");
					if (!musicService.player.isPlaying()) {
						musicService.player.start();
					}

					// mp.pause();

				}

			}
		};
		mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		return rootView;

	}
	public ServiceConnection serviceconnected=new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			musicService = ((PlayMusicService.ServiceBinder) iBinder).getService();
			mIsBound=true;

		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mIsBound=false;

		}
	};
	/*
	 * @Override public void onDestroy() { // TODO Auto-generated method stub
	 * super.onDestroy(); if(mgr != null) { mgr.listen(phoneStateListener,
	 * PhoneStateListener.LISTEN_NONE); } }
	 */

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.play:

		{
			if (press == true) {
				change = true;
				// runAnimations();

				Intent i = new Intent(getActivity(), PlayMusicService.class);
				i.putExtra("OPTION", R.raw.sada);
				i.putExtra("count",1);
				getActivity().bindService(i,serviceconnected,Context.BIND_AUTO_CREATE);
				getActivity().startService(i);
				if ((musicService.player != null) && (!musicService.player.isPlaying())) {

					//playsong();
				} else {
					play.setBackgroundResource(R.drawable.subplay);
				}
				play.setBackgroundResource(R.drawable.pause);
			} else {
				if (musicService.player != null && musicService.player.isPlaying())
					musicService.player.pause();

				// System.out.println("Music paused...");
				play.setBackgroundResource(R.drawable.subplay);
				change = false;
			}
			press = !press;
			//
		}
			break;

		default:
			break;
		}
	}
	private class CountReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub

			int datapassed = arg1.getIntExtra("DATAPASSED", 0);


			Toast. makeText(getActivity(),
					"Triggered by Service!\n"
							+ "Data passed: " + String.valueOf(datapassed),
					Toast.LENGTH_LONG).show();
			//count.setText(String.valueOf(datapassed));

		}

	}
	void doBindService(){
		getActivity().bindService(new Intent(getActivity(),PlayMusicService.class),
				serviceconnected,Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService()
	{
		if(mIsBound)
		{
			getActivity().unbindService(serviceconnected);
			mIsBound = false;
		}
	}
	/*private void playsong() {
		// TODO Auto-generated method stub
		// System.out.println("Starting song...");
		*//*
		 * try { mp.prepare();
		 * 
		 * } catch (IllegalStateException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 *//*

		mp.start();
		// System.out.println("Playing music...");
	}*/

	private void stopsong() {
		// System.out.println("Stopping...");
		musicService.player.stop();
		musicService.player.reset();
		musicService.player.release();
		musicService.player = null;


	}

	@Override
	public void onPause() {
		System.out.println("inside on pause");
		// TODO Auto-generated method stub
		Log.d("activity", "onPause");
		if (musicService != null) {
			musicService.stopMusic();
			doUnbindService();
			getActivity().unbindService(serviceconnected);
			musicService = null;
		}
		super.onPause();

		// getActivity().finish();

	}

	@Override
	public void onStart() {
		super.onStart();
		myReceiver = new CountReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(PlayMusicService.MY_ACTION);
		getActivity().registerReceiver(myReceiver, intentFilter);


	}

	@Override
	public void onStop() {
		super.onStop();

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("inside on resume");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(myReceiver);
	}
}
