package com.skyries.saibaba;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
    MediaPlayer player;
    Context context;  final static String MY_ACTION = "MY_ACTION";
    private final IBinder mBinder = new ServiceBinder();

    private int length = 0;int i;int icount;
    public PlayMusicService() {
    }
   public class ServiceBinder extends Binder {
        PlayMusicService getService()
        {
            return PlayMusicService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        /*try {
            i = intent.getExtras().getInt("OPTION");
            icount = intent.getExtras().getInt("count");
            player = MediaPlayer.create(this, i);
            player.setLooping(false);
            // player.start();
            player(icount);
            // MyThread myThread = new MyThread();
            //myThread.start();
        }catch (NullPointerException e){

        }*/
        return mBinder;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        try {
            i = intent.getExtras().getInt("OPTION");
            icount = intent.getExtras().getInt("count");
            player = MediaPlayer.create(this, i);
            player.setLooping(false);
            // player.start();
            player(icount);
            // MyThread myThread = new MyThread();
            //myThread.start();
        }catch (NullPointerException e){

        }
        return START_STICKY;
    }


    void player(final int yourCount) {

        Log.i("inside plyer","count"+yourCount);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub

                if (length < yourCount) {
                    Log.i("inside plyer","2");
                    Log.i("log", "" + length);
                    player.start();
                    Log.i("inside plyer","3");
                    length++;

                    Log.i("inside plyer","4");
                    Intent intent = new Intent();
                    intent.setAction(MY_ACTION);

                    intent.putExtra("DATAPASSED", length);

                    sendBroadcast(intent);
                    if (length == yourCount) {


                        stopSelf();

                    }
                }


            }

        });
        if (icount != 0) {
            player.start();

        }


    }

    public void pauseMusic()
    {
        if(player.isPlaying())
        {
            player.pause();
            length=player.getCurrentPosition();

        }
    }

    public void resumeMusic()
    {
        if(player.isPlaying()==false)
        {
            player.seekTo(length);
            player.start();
        }
    }

    public void stopMusic()
    {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        if(player != null)
        {
            try{
                player.stop();
                player.release();
            }finally {
                player = null;
            }
        }
    }

}
