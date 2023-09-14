package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.chatapp.nineninechatapp.R;
import com.squareup.picasso.Picasso;

public class ImagePreviewActivity extends AppCompatActivity {

    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

       String avatar = getIntent().getStringExtra("Avatar");
       imgView = findViewById(R.id.imgView);

        Picasso.get().load(avatar).into(imgView);
        Log.d("CheckingPhoto",avatar);
    }
}