package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Constant;
import com.squareup.picasso.Picasso;

public class ImagePreviewActivity extends AppCompatActivity {

    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

       String avatar = getIntent().getStringExtra("Avatar");
       String imageView = getIntent().getStringExtra(Constant.IMAGEVIEW);
       imgView = findViewById(R.id.imgView);


       if (avatar != null){
           Glide.with(getApplicationContext())
                   .load(avatar)
                   .into(imgView);
           Log.d("CheckingPhoto",avatar);
       }else if(imageView != null){
           Picasso.get().load(imageView).into(imgView);

           Glide.with(getApplicationContext())
                   .load(imageView)
                   .into(imgView);
           Log.d("CheckingPhoto",imageView+"ImageView");
       }


    }
}