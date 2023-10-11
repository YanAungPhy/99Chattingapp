package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.chatapp.nineninechatapp.R;
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

                finish();
                startActivity(new Intent(SplashActivity.this, AdSliderActivity.class));

            }
        }, 2000);

    }

}
