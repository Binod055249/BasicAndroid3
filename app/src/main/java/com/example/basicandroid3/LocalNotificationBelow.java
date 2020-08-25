package com.example.basicandroid3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocalNotificationBelow extends AppCompatActivity {

    private EditText edtContentTitle,edtContentText;
private Button btnNotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_notification_below);

        edtContentText=findViewById(R.id.edtContentText);
        edtContentTitle=findViewById(R.id.edtContentTitle);
     btnNotify=findViewById(R.id.btnNotify);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtContentTitle.getText().toString().matches("")||
                        edtContentText.getText().toString().matches("")){
                    Toast.makeText(LocalNotificationBelow.this, "Fill all details", Toast.LENGTH_SHORT).show();
                 return;
                }
                   showNotification();
            }
        });
    }

    public void showNotification(){

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"notify_001");
        builder.setSmallIcon(R.drawable.watch_movie);
        builder.setContentText(edtContentText.getText().toString());
        builder.setContentTitle(edtContentTitle.getText().toString());

        //user click on notification
        Intent intent =new Intent(LocalNotificationBelow.this,LocalNotificationBelow.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(LocalNotificationBelow.this,0,intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify("Mynotify",123,builder.build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "notify_001";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }


    }
}