package com.chatapp.nineninechatapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.VideoAdapter;
import com.chatapp.nineninechatapp.Model.ReqFriendList.VideoModel;
import com.chatapp.nineninechatapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_VIDEO = 456;
    private static final int REQUEST_PERMISSIONS = 345;
    private   RecyclerView recyclerView;
    private List<VideoModel> vdList;
    private int offset = 0;
    private int limit = 30; //

    private void retrieveVideosInBackground() {
        new VideoRetrievalTask(this, videoList -> {

            VideoAdapter adapter = new VideoAdapter(videoList, getApplicationContext());
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setAdapter(adapter);
        }).execute(offset, limit);
        offset += limit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_video);
        Log.d("CheckingVideoList",vdList+"");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
        }else {
            retrieveVideosInBackground();
        }


        recyclerView = findViewById(R.id.recyclerView);

        findViewById(R.id.btnGetVideo).setOnClickListener(v -> {
            pickVideo();
        });

    }

    public void pickVideo() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*"); // Specify the MIME type for videos
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Allow multiple selections

        startActivityForResult(intent, REQUEST_PICK_VIDEO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS){
            retrieveVideosInBackground();
        }
    }

    public List<VideoModel> getAllVideos(Context context) {
        List<VideoModel> videoList = new ArrayList<>();
        String[] projection = {
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION
        };

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int titleIndex = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
                int fileIndex = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                long durationIndex = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);

                String title = cursor.getString(titleIndex);
                String filePath = cursor.getString(fileIndex);
                long duration = cursor.getLong((int) durationIndex);

                VideoModel video = new VideoModel();
                video.setTitle(title);
                video.setFilePath(filePath);
                video.setDuration(duration);

                videoList.add(video);
            }
            cursor.close();
        }

        return videoList;
    }

}



 class VideoRetrievalTask extends AsyncTask<Integer, Void, List<VideoModel>> {
     private Context context;
     private OnVideoRetrievedListener listener;

     public interface OnVideoRetrievedListener {
         void onVideoRetrieved(List<VideoModel> videoList);
     }

    public VideoRetrievalTask(Context context, VideoRetrievalTask.OnVideoRetrievedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<VideoModel> doInBackground(Integer... params) {
        int offset = params[0];
        int limit = params[1];

        List<VideoModel> videoList = new ArrayList<>();
        String[] projection = {
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION
        };

        // Query the MediaStore with offset and limit
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        // Process the cursor and retrieve videos based on offset and limit
        if (cursor != null) {
            int count = 0;
            cursor.move(offset);
            while (count < limit && cursor.moveToNext()) {
                while (cursor.moveToNext()) {
                    int titleIndex = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
                    int fileIndex = cursor.getColumnIndex(MediaStore.Video.Media.DATA);

                    String title = cursor.getString(titleIndex);
                    String filePath = cursor.getString(fileIndex);

                    VideoModel video = new VideoModel();
                    video.setTitle(title);
                    video.setFilePath(filePath);

                    videoList.add(video);
                }
                count++;
            }
            cursor.close();
        }

        return videoList;
    }

    @Override
    protected void onPostExecute(List<VideoModel> videoList) {
        if (listener != null) {
            listener.onVideoRetrieved(videoList);
        }
    }
}

