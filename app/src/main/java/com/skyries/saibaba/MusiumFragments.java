package com.skyries.saibaba;

import java.util.ArrayList;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;








import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MusiumFragments extends Fragment implements OnClickListener{
	private boolean press=true,change=true;
    ImageButton info,play,share,next,previous;
    private InterstitialAd interstitial;
	Menu menu;
	private ImageView pimage;
	ArrayList<Integer> arr;
	Integer[] items={R.drawable.musi1,R.drawable.musi2,R.drawable.musi3,R.drawable.musi4,
			R.drawable.musi5,R.drawable.musi6,R.drawable.musi7,R.drawable.musi8,
			R.drawable.musi9,R.drawable.musi10,R.drawable.musi11,R.drawable.musi12
			         };
	private Animation fadeIn = null;
	private Animation fadeOut = null;
	
	private int currentImage=0,pos=0;
	
	private LocalFadeInAnimationListener myFadeInAnimationListener = new LocalFadeInAnimationListener();
    private LocalFadeOutAnimationListener myFadeOutAnimationListener = new LocalFadeOutAnimationListener();
		
	public  MusiumFragments() {
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		  View rootView = inflater.inflate(R.layout.fragments_musiumcollection, container, false);
		  play=(ImageButton)rootView.findViewById(R.id.play);
	        play.setOnClickListener(this);
        
	        next=(ImageButton)rootView.findViewById(R.id.next);
	        next.setOnClickListener(this);
	        
	        previous=(ImageButton)rootView.findViewById(R.id.previous);
	        previous.setOnClickListener(this);

		    pimage=(ImageView)rootView.findViewById(R.id.pimage);
		    pimage.setImageResource(items[currentImage]);
		    arr=preparedlist1();
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
		switch (v.getId()) 
    	{
		
			
		case R.id.play:
			
			if(v.getId()==R.id.play)
			{
				if(press==true)
				{
					change=true;
					runAnimations();
					
					
					v.setBackgroundResource(R.drawable.pause);
				}
				else
				{
					
					//System.out.println("Music paused...");
					v.setBackgroundResource(R.drawable.subplay);
					change=false;
				}
				press=!press;
			}
			break;	

		case R.id.next:
			nextimage();
			break;
			
		case R.id.previous:
			previousimage();
			break;	

		default:
			break;
		}
		
	}
	private void nextimage() 
	{
		// TODO Auto-generated method stub

			if(pos<arr.size())
			{
			   pos++;	
			   if(pos<arr.size())
			   pimage.setImageResource(arr.get(pos));
			 //  System.out.println("next...");
			
		}

	}
	
	private void previousimage() 
	{
		// TODO Auto-generated method stub
		
		if(pos>0)
		{
			--pos;
		    pimage.setImageResource(arr.get(pos));
		    System.out.println("previous...");
		}
		
	}
	public ArrayList<Integer> preparedlist1() 
	{
		// TODO Auto-generated method stub
		ArrayList<Integer> array1=new ArrayList<Integer>();
		array1.add(R.drawable.musi1);
		array1.add(R.drawable.musi12);
		array1.add(R.drawable.musi3);
		array1.add(R.drawable.musi4);
		array1.add(R.drawable.musi5);
		array1.add(R.drawable.musi6);
		array1.add(R.drawable.musi7);
		array1.add(R.drawable.musi8);
		array1.add(R.drawable.musi9);
		array1.add(R.drawable.musi10);
		array1.add(R.drawable.musi11);
		array1.add(R.drawable.musi12);
		
		return array1;
	}
	 private void launchOutAnimation() 
	    {    	
	    	
		    pimage.startAnimation(fadeOut);
		 	  
	    }    
	    
	    private void launchInAnimation() 
	    {   	
	    	
		    pimage.startAnimation(fadeIn);
		 	    
	    }  
	    
	    private void runAnimations() 
	    {    	
	    	Log.d(getClass().getName(), "runAnimations(): entering" );  

		    fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
		    fadeIn.setAnimationListener( myFadeInAnimationListener );
		    fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout);
		    fadeOut.setAnimationListener( myFadeOutAnimationListener );
		    	if(change==true)    
		    launchInAnimation();
		  //  System.out.println("from fadein or fadeout");
	    }
	    
	    private Runnable mLaunchFadeOutAnimation = new Runnable() 
	    {
		    public void run() 
		    {
		    	if(currentImage<arr.size() && change==true)
		    	{
		    	launchOutAnimation();
		    	}
		    	if(currentImage<arr.size())
		    	pimage.setImageResource(arr.get(currentImage));
		    	else
		    	currentImage=0;
		    	//System.out.println("launching from fadeoutrunnable");
		    	if(currentImage<items.length)
		    	{
		    		System.out.println("Inside condition now...");
		    	currentImage++;
		    	}
		    	else
		    		currentImage=0;
		    	if(change==true)
		    	currentImage++;
		    }
	    };    
	    
	    private Runnable mLaunchFadeInAnimation = new Runnable() 
	    {
		    public void run() 
		    {
		    	if(currentImage<arr.size() && change==true)
		    	{
		    	launchInAnimation();
		    	pimage.setImageResource(arr.get(currentImage));
		    	}
		    	else
		    	{
		    		currentImage=0;
		    		launchInAnimation();
		    		pimage.setImageResource(arr.get(currentImage));
		    	}
		    //	System.out.println("launching from fadeinrunnable");
		    	if(currentImage<items.length)
		    	currentImage++;
		    }
	    };   
	    
	    public class LocalFadeInAnimationListener implements AnimationListener 
	    {
	    	
		    public void onAnimationEnd(Animation animation) 
		    {
			    pimage.post(mLaunchFadeOutAnimation);
			    
			}
			
		    public void onAnimationRepeat(Animation animation)
		    {
		    	animation.reset();
		    }
		
		    public void onAnimationStart(Animation animation) 
		    {
		    	
		    }
	    };
	    
	    private class LocalFadeOutAnimationListener implements AnimationListener 
	    {
	    	
		    public void onAnimationEnd(Animation animation) 
		    {
			    pimage.post(mLaunchFadeInAnimation);
			    
			}
			
		    public void onAnimationRepeat(Animation animation) 
		    {
		    	animation.reset();
		    }
		
		    public void onAnimationStart(Animation animation) 
		    {
		    	
		    }
	    }

}
