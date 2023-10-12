package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Adapter.post.PhotoUploadAdapter;
import com.chatapp.nineninechatapp.AlbumPhotoHelper;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.Model.UploadPost.PostPhoto;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.Constant;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoUploadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private Button btnPost;
    private CircleImageView userImg;
    private UserObj userObj;
    private ImageView imgSelectPhoto;
    private ArrayList<PostPhoto> postPhotoArrayList;
    private PhotoUploadAdapter uploadAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);

        postPhotoArrayList = (ArrayList<PostPhoto>) getIntent().getSerializableExtra(Constant.SELECTED_PHOTOLIST);
        Log.d("=====>>",postPhotoArrayList+"");

        userName = findViewById(R.id.txtUsername);
        userImg = findViewById(R.id.circularImageView);
        imgSelectPhoto = findViewById(R.id.imgSelectPhoto);
        recyclerView = findViewById(R.id.photoRecyclerView);
        btnPost = findViewById(R.id.btnPost);

        userObj = Utility.query_UserProfile(getApplicationContext());
       // show_UserProfile(userObj);
        uploadAdapter = new PhotoUploadAdapter(postPhotoArrayList,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(uploadAdapter);

        imgSelectPhoto.setOnClickListener(this);
        btnPost.setOnClickListener(this);

        Map<String, List<String>> albumMap = AlbumPhotoHelper.getAlbumsWithPhotos(this);

        for (Map.Entry<String, List<String>> entry : albumMap.entrySet()) {
            String albumName = entry.getKey();
            List<String> photoPaths = entry.getValue();
            int photoCount = photoPaths.size();
            Log.d("AlbumName",albumName);
            Log.d("AlbumName",photoPaths+ "");
            Log.d("AlbumName", photoCount+"");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgSelectPhoto:
                startActivity(new Intent(this, PostPhotoListActivity.class));
                finish();
                break;
            case R.id.btnPost:
                break;
        }

    }

    public void show_UserProfile(UserObj userObj) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(getApplicationContext()).load(APIURL.ImageUrl + userObj.getImagePath()).apply(requestOptions).into(userImg);

        userName.setText(userObj.getName());
    }
}



