package com.example.basicandroid3;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkInfo {

    public static int getNetworkStatus(Context context){

        ConnectivityManager connectivityManager=
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        android.net.NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            switch (networkInfo.getType()){
                case ConnectivityManager.TYPE_WIFI:
                    return 1;

                case ConnectivityManager.TYPE_MOBILE:
                    return 0;

                default:
                    return 3;
            }
        }else {
            return 100;
        }
    }

}
