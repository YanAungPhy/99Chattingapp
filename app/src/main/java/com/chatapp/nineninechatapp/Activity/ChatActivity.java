package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

public class ChatActivity extends AppCompatActivity {

    ImageView imgPhoto;
    ImageView imgSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activtiy);

        Utility.FullScreen(this);
        initView();
     //   checkDartModeView();
    }

    private void initView() {


    }

    private void checkDartModeView() {
        if (AppStorePreferences.getBoolean(getApplicationContext(),"dark_mode")){
            imgPhoto.setImageResource(R.drawable.ic_photo_white);
            imgSend.setImageResource(R.drawable.ic_white_send);
        }else {
            imgPhoto.setImageResource(R.drawable.ic_photo);
            imgSend.setImageResource(R.drawable.ic_send);
        }
    }
}