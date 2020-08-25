package com.example.basicandroid3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWatchMovie;

    //Instance Variable
    private NotificationHandler notificationHandler;
    private static final int WATCH_MOVIE_NOTIFICATION_ID=1000;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        edtWatchMovie=findViewById(R.id.edtWatchMovie);
        findViewById(R.id.btnWatchMovie).setOnClickListener(this);
        findViewById(R.id.btnMovieSettings).setOnClickListener(this);

        notificationHandler=new NotificationHandler(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnWatchMovie:

               postNotificationToUserDevice(WATCH_MOVIE_NOTIFICATION_ID,edtWatchMovie.getText().toString());
                break;

            case R.id.btnMovieSettings:

                openNotificationSettingsForWatchMovieChannel(notificationHandler.WATCH_MOVIE_NOTIFICATION_CHANNEL_ID);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void postNotificationToUserDevice(int notificationId, String titleText){

        Notification.Builder notificationBuilder=null;

        switch (notificationId) {

            case WATCH_MOVIE_NOTIFICATION_ID:
                       notificationBuilder=notificationHandler
                               .createAndReturnWatchMoviesNotification(titleText,"This is a great movie");
                       break;

        }

        if(notificationBuilder!=null){

            notificationHandler.notifyTheUser(WATCH_MOVIE_NOTIFICATION_ID,notificationBuilder);
        }
    }

    private void openNotificationSettingsForWatchMovieChannel(String watchMovieChannelID){

        Intent settingsIntent=new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        settingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
        settingsIntent.putExtra(Settings.EXTRA_CHANNEL_ID,watchMovieChannelID);
        startActivity(settingsIntent);
    }

}