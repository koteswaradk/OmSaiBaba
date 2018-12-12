package com.skyries.saibaba;




import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AshtotharaNamavaliFragment extends Fragment implements OnClickListener {
	private MediaPlayer mp=null;
	private InterstitialAd interstitial;
	ImageButton play;
	private boolean press=true,change=true;
	 TelephonyManager mgr;
	 PhoneStateListener phoneStateListener;
	public AshtotharaNamavaliFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_ashtothara, container, false);
        play=(ImageButton)rootView.findViewById(R.id.play);
        play.setOnClickListener(this);
   	 	mp=MediaPlayer.create(getActivity(), R.raw.saibabaashtotranaamavali);
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
   	 	
   	 mp.setOnCompletionListener(new OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			play.setBackgroundResource(R.drawable.subplay);
		}
	});
 	phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
        	 super.onCallStateChanged(state, incomingNumber);
            if (state == TelephonyManager.CALL_STATE_RINGING) {
            	mp.pause();
            	System.out.println("inside ringing");
            	play.setBackgroundResource(R.drawable.subplay);
            } else if(state == TelephonyManager.CALL_STATE_IDLE) {
            	System.out.println("inside idle");
   
            } else if(state == TelephonyManager.CALL_STATE_OFFHOOK) {
              // Pause music
            	System.out.println("inside CALL_STATE_OFFHOOK");
            	if (!mp.isPlaying()) {
            		mp.start();
				}
            	
            	
            	//mp.pause();
            	
            }
                     
        }
    };
    mgr.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
   	  
        return rootView;
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.play:
			
			{
				if(press==true)
				{
					change=true;
					//runAnimations();
					
					if((mp!=null)&&(!mp.isPlaying()))
					{
					playsong();
					}else{
						play.setBackgroundResource(R.drawable.subplay);
					}
					play.setBackgroundResource(R.drawable.pause);
				}
				else
				{
					if(mp!=null && mp.isPlaying())
					mp.pause();
					//System.out.println("Music paused...");
					play.setBackgroundResource(R.drawable.subplay);
					change=false;
				}
				press=!press;
				//
			}
			break;

		default:
			break;
		}
		
	}
	private void playsong() 
    {
		// TODO Auto-generated method stub
	//	System.out.println("Starting song...");
		/*try {
			mp.prepare();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		
		mp.start();
	//	System.out.println("Playing music...");
	}
	
	
	private void stopsong()
	{
		//System.out.println("Stopping...");
	   	mp.stop();
	   	mp.reset();
	   	mp.release();
	   	mp=null;
	   	
	}
	 @Override
	public void onPause() 
	    {
	    	// TODO Auto-generated method stub
	    	if(mp!=null && mp.isPlaying())
	    	{
	    	stopsong();
	    	}
	    	//getActivity().finish();
	    	super.onPause();
	    }
}
