package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chatapp.nineninechatapp.Adapter.post.PhotoListAdapter;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout bottomViewState;
    private ImageView imgPhoto;
    private ImageView imgSend;
    private ImageView imgBtnMore;
    private LinearLayout sendPhoto;
    private LinearLayout sendVideo;
    private Boolean isViewState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activtiy);

        Utility.FullScreen(this);
        initView();
        //checkDartModeView();
    }

    private void initView() {
        imgBtnMore = findViewById(R.id.imgBtnMore);
        bottomViewState = findViewById(R.id.bottomViewState);
        sendPhoto = findViewById(R.id.sendPhoto);
        sendVideo = findViewById(R.id.sendVideo);

        imgBtnMore.setOnClickListener(this);
        sendPhoto.setOnClickListener(this);
        sendVideo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBtnMore:
                chatBottomViewState();
                break;
            case R.id.sendPhoto:
                Toast.makeText(getApplicationContext(),"SendPhoto",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, PostPhotoListActivity.class));
                finish();
                break;
            case R.id.sendVideo:
                Toast.makeText(getApplicationContext(),"SendPhoto",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, VideoListActivity.class));
                finish();
                break;
        }
    }

    private void chatBottomViewState(){
        if(isViewState){
            bottomViewState.setVisibility(View.VISIBLE);
            isViewState = false;
        }else {
            bottomViewState.setVisibility(View.GONE);
            isViewState = true;
        }
    }

    private void checkDartModeView() {
        if (AppStorePreferences.getBoolean(getApplicationContext(), "dark_mode")) {
            imgPhoto.setImageResource(R.drawable.ic_photo_white);
            imgSend.setImageResource(R.drawable.ic_white_send);
        } else {
            imgPhoto.setImageResource(R.drawable.ic_photo);
            imgSend.setImageResource(R.drawable.ic_send);
        }
    }
}