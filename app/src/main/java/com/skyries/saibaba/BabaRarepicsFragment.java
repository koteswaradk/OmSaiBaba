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

public class BabaRarepicsFragment extends Fragment implements OnClickListener {
	private boolean press=true,change=true;
    ImageButton info,play,share,next,previous;
	Menu menu;
	private InterstitialAd interstitial;
	private ImageView pimage;
	ArrayList<Integer> arr;
	Integer[] items={R.drawable.rare1,R.drawable.rare2,R.drawable.rare3,R.drawable.rare4,
	         R.drawable.rare5,R.drawable.rare6,R.drawable.rare7,R.drawable.rare8};

	
	private Animation fadeIn = null;
	private Animation fadeOut = null;
	
	private int currentImage=0,pos=0;
	
	private LocalFadeInAnimationListener myFadeInAnimationListener = new LocalFadeInAnimationListener();
    private LocalFadeOutAnimationListener myFadeOutAnimationListener = new LocalFadeOutAnimationListener();
	
	public BabaRarepicsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragments_rarepics, container, false);
       /* Intent launchingIntent = new Intent(getActivity(),Play.class);
		 // launchingIntent.putExtra("position", getActivity().getResources().getStringArray(R.array.miracle_details)[position]);
		  getActivity().startActivity(launchingIntent);*/
        play=(ImageButton)rootView.findViewById(R.id.play);
        play.setOnClickListener(this);
		  next=(ImageButton)rootView.findViewById(R.id.next);
	        next.setOnClickListener(this);
	        
	        previous=(ImageButton)rootView.findViewById(R.id.previous);
	        previous.setOnClickListener(this);
	        pimage=(ImageView)rootView.findViewById(R.id.pimage);
		    //pimage.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
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
		/*case R.id.info:
			if(mp!=null && mp.isPlaying())
			{
			stopsong();
			}
			finish();
			break;*/
			
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
					
					v.setBackgroundResource(R.drawable.subplay);
					change=false;
				}
				press=!press;
			}
			break;	
			
		/*case R.id.share:
			Intent in=new Intent(Play.this,SocialNetwork.class);
			startActivity(in);
			finish();
			menu();
			
			break;*/
			
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
		  //  System.out.println("previous...");
		}
		
	}
	 public ArrayList<Integer> preparedlist1() 
		{
			// TODO Auto-generated method stub
		 ArrayList<Integer> array1=new ArrayList<Integer>();
			array1.add(R.drawable.rare1);
			array1.add(R.drawable.rare2);
			array1.add(R.drawable.rare3);
			array1.add(R.drawable.rare4);
			array1.add(R.drawable.rare5);
			array1.add(R.drawable.rare6);
			array1.add(R.drawable.rare7);
			array1.add(R.drawable.rare8);
		
			/*array1.add(R.drawable.image9);
			array1.add(R.drawable.image10);*/
			/*array1.add(R.drawable.image11);
			array1.add(R.drawable.image12);
			array1.add(R.drawable.image13);
			array1.add(R.drawable.image14);
			array1.add(R.drawable.image15);
			array1.add(R.drawable.image16);
			array1.add(R.drawable.image17);
			array1.add(R.drawable.image18);
			array1.add(R.drawable.image19);
			array1.add(R.drawable.image20);*/
			
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
	    	//Log.d(getClass().getName(), "runAnimations(): entering" );  

		    fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
		    fadeIn.setAnimationListener( myFadeInAnimationListener );
		    fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout);
		    fadeOut.setAnimationListener( myFadeOutAnimationListener );
		    	if(change==true)    
		    launchInAnimation();
		   // System.out.println("from fadein or fadeout");
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
		    		//System.out.println("Inside condition now...");
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
		    	//System.out.println("launching from fadeinrunnable");
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
