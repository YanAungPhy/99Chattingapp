package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Model.Login.UserObj;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoUploadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private CircleImageView userImg;
    private UserObj userObj;
    private ImageView imgSelectPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);

        userName = findViewById(R.id.txtUsername);
        userImg = findViewById(R.id.circularImageView);
        imgSelectPhoto = findViewById(R.id.imgSelectPhoto);
        userObj= Utility.query_UserProfile(getApplicationContext());
        show_UserProfile(userObj);

        imgSelectPhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSelectPhoto:
                startActivity(new Intent(this,PostPhotoListActivity.class));
                break;
        }

    }

    public void show_UserProfile(UserObj userObj){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(getApplicationContext()).load(APIURL.ImageUrl+userObj.getImagePath()).apply(requestOptions).into(userImg);

        userName.setText(userObj.getName());
    }
}

