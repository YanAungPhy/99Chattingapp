package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chatapp.nineninechatapp.Model.Register.UploadImgModel;
import com.chatapp.nineninechatapp.Model.VideoUploadResponse;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkSync;
import com.chatapp.nineninechatapp.Utils.RetrofitFactory;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideoUploadActivity extends AppCompatActivity {

    private String accessToken;
    private String videoFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload);

        accessToken = AppStorePreferences.getString(this, AppENUM.TOKEN);
        videoFilePath = getIntent().getStringExtra("filePath");

        Log.d("CheckingSystemToken", accessToken+"CheckToken");
        Log.d("CheckingVideoFilePath", videoFilePath+"CheckToken");


        findViewById(R.id.btnUpload).setOnClickListener(v -> {
            startActivity(new Intent(VideoUploadActivity.this,VideoListActivity.class));
        });

        findViewById(R.id.postVideo).setOnClickListener(v -> {
           uploadVideoFile();
        });
    }


    private void uploadVideoFile(){

        File videoFileToUpload = new File(videoFilePath);
        RequestBody requestVideoFile = RequestBody.create(MediaType.parse("multipart/form-data"), videoFileToUpload);
        MultipartBody.Part videoPart = MultipartBody.Part.createFormData("video", videoFileToUpload.getName(), requestVideoFile);

        RetrofitFactory factory=new RetrofitFactory();
        Retrofit retrofit=factory.connector();
        NetworkSync.VideoUploadSync sync =retrofit.create(NetworkSync.VideoUploadSync.class);

        Call<VideoUploadResponse> call=sync.videoUpload(APIURL.DomainName+APIURL.postVideoUploadUrl,accessToken,videoPart);
        call.enqueue(new Callback<VideoUploadResponse>() {
            @Override
            public void onResponse(Call<VideoUploadResponse> call, Response<VideoUploadResponse> response) {

            }

            @Override
            public void onFailure(Call<VideoUploadResponse> call, Throwable t) {

            }
        });


    }

}


