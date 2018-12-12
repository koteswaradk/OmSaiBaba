package com.skyries.saibaba;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class LiveDarshana extends Activity {
	 private static ProgressDialog progressDialog;
     //String videourl="rtsp://cam.live-s.cdn.bitgravity.com/cdn-live/_definst_/cam/live/secure/feed022?e=0&h=ba7f5c5f6bf8a02d29f909dfa8d0c075&nc=1";
     String videourl="http://cam.live.cdn.bitgravity.com/cam/live/secure/saibaba?e=1441537169%26h=2d692cd9891cf959478461e0a8eed288&amp;streamType=live&amp;Mode=live";
     VideoView videoView ;
     MediaController mc;
     boolean condition=false;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.livedarshana);

            videoView = (VideoView) findViewById(R.id.video_View);

            progressDialog = ProgressDialog.show(LiveDarshana.this, "", "Buffering Live Darshana...",true);
            progressDialog.setCancelable(condition);

         //   mc=new MediaController(this);
            PlayVideo();
     }

    /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	 if (keyCode==KeyEvent.KEYCODE_BACK) {
    			progressDialog.dismiss();
		}
    	return super.onKeyDown(keyCode, event);
    }*/
     @Override
 	public void onBackPressed() {
 		// TODO Auto-generated method stub
 		super.onBackPressed();
 		
 	
 		finish();
 		//android.os.Process.killProcess(android.os.Process.myPid());
 		//startActivity(new Intent(LiveDarshana.this, MainActivity.class));
 		
 	}
     private void PlayVideo()
     {
            try
            {   
                   getWindow().setFormat(PixelFormat.TRANSLUCENT);
                 /*  MediaController mediaController = new MediaController(LiveDarshana.this);
                   mediaController.setAnchorView(videoView);  */      

                   Uri video = Uri.parse(videourl);          
                  // videoView.setMediaController(mediaController);
                   videoView.setVideoURI(video);
                   videoView.requestFocus();           
                   videoView.setOnPreparedListener(new OnPreparedListener()
                   {

                         public void onPrepared(MediaPlayer mp)
                         {                
                                progressDialog.dismiss();  
                                videoView.start();
                         }
                   });

            }
            catch(Exception e)
            {
                   progressDialog.dismiss();
                   System.out.println("Video Play Error :"+e.toString());
                   Toast.makeText(this, "Sorry Due to network error cannot play the live Darshana", Toast.LENGTH_SHORT).show();
                   finish();
            }

     }
}
