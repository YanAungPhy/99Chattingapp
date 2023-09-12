package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;

public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utility.darkMode(this);
        Utility.FullScreen(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (AppStorePreferences.getInt(SplashActivity.this, AppENUM.LOGIN_CON) == 1) {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                } else {

                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }
                finish();
                startActivity(new Intent(SplashActivity.this, AdSliderActivity.class));

            }
        }, 2000);

    }

}
