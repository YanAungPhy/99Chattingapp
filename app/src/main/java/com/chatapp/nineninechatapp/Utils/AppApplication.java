package com.chatapp.nineninechatapp.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class AppApplication extends Application  {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();


       getRegID();

    }


    public static Context getAppContext() {
        return context;
    }

    public void  getRegID(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.e("mtt>>>",token);
                        AppStorePreferences.putString(getApplicationContext(), AppENUM.FCM_TOKEN, token);
                    }
                });

    }

}

