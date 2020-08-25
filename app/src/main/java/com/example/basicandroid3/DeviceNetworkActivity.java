package com.example.basicandroid3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DeviceNetworkActivity extends AppCompatActivity {

    private ImageView imgNetworkType;
    private Button btnNetworkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_network);

        imgNetworkType=findViewById(R.id.imgNetworkType);
        btnNetworkStatus=findViewById(R.id.btnGetNetworkStatus);

        btnNetworkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int statusInfo= NetworkInfo.getNetworkStatus(DeviceNetworkActivity.this);

                if(statusInfo==1){
                    imgNetworkType.setImageDrawable(getResources().getDrawable(R.drawable.wifiinternet));
                    Toast.makeText(DeviceNetworkActivity.this, "Wifi internet", Toast.LENGTH_SHORT).show();
                }else if(statusInfo==0){
                    imgNetworkType.setImageResource(R.drawable.mobileinternet);
                    Toast.makeText(DeviceNetworkActivity.this, "Mobile internet", Toast.LENGTH_SHORT).show();
                }else{
                    imgNetworkType.setImageResource(R.mipmap.ic_launcher);
                    Toast.makeText(DeviceNetworkActivity.this, "Unkown", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}