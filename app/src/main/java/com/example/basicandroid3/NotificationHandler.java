package com.example.basicandroid3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationHandler extends ContextWrapper {

      private NotificationManager notificationManager;

      public static final String WATCH_MOVIE_NOTIFICATION_CHANNEL_ID="WMNCID";
      public static final String WATCH_MOVIE_NOTIFICATION_CHANNEL_Name="WMNCName";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHandler(Context base) {
        super(base);
        createNotificationChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(){

        NotificationChannel notificationChannel=new NotificationChannel(WATCH_MOVIE_NOTIFICATION_CHANNEL_ID,
                                                         WATCH_MOVIE_NOTIFICATION_CHANNEL_Name,
                                                             notificationManager.IMPORTANCE_HIGH);

        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    private NotificationManager getNotificationManager(){
        if(notificationManager==null){
            notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }
            return  notificationManager;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder createAndReturnWatchMoviesNotification(String titleText, String bodyText){

        return new Notification.Builder(getApplicationContext(),WATCH_MOVIE_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(titleText)
                .setContentText(bodyText)
                .setSmallIcon(R.drawable.watch_movie)
                .setAutoCancel(true);
    }

    public void notifyTheUser(int notificationId,Notification.Builder notificationBuilder){

        getNotificationManager().notify(notificationId,notificationBuilder.build());

    }

}
