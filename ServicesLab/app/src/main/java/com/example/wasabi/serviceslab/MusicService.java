package com.example.wasabi.serviceslab;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MusicService extends Service {
    public MusicService() {
    }

    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(getApplicationContext(), R.raw.bensound_funnysong);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(player.isPlaying()){
            player.pause();
        }else{
            player.start();
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_media_play);
        builder.setContentTitle("MUSIC");
        builder.setContentText("Playing");


        PendingIntent pendingIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis(),new Intent(this, MusicService.class),0);

        builder.addAction(android.R.drawable.ic_media_play,"Play",pendingIntent);

        Notification notification = builder.build();

        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.pause();
   }
}
