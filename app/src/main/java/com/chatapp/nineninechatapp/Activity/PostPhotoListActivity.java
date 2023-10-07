package com.chatapp.nineninechatapp.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.post.PhotoListAdapter;
import com.chatapp.nineninechatapp.Model.UploadPost.PostPhoto;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PostPhotoListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    private RecyclerView recyclerView;
    private List<PostPhoto > photoList;
    private PhotoListAdapter photoUploadAdapter;
    private  ArrayList<PostPhoto> selectedPhotosResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_photo_list);

        recyclerView = findViewById(R.id.photoListRecyclerView);
        btnNext = findViewById(R.id.btnNext);
        photoList = getAllImages(this);

        photoUploadAdapter = new PhotoListAdapter(photoList, getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(photoUploadAdapter);

        btnNext.setOnClickListener(this);
    }

    private List<PostPhoto> getAllImages(Context context) {
        List<PostPhoto> imagePaths = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();

        // Query external storage for images
        imagePaths.addAll(getImagesFromExternalStorage(context));

        // Query internal storage for images
        imagePaths.addAll(getImagesFromInternalStorage(context));

        return imagePaths;
    }



    private List<PostPhoto> getImagesFromExternalStorage(Context context) {
        List<PostPhoto> imagePaths = new ArrayList<>();
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            File externalDir = Environment.getExternalStorageDirectory();
            String[] projection = { MediaStore.Images.Media.DATA };
            String selection = MediaStore.Images.Media.DATA + " like ?";
            String[] selectionArgs = new String[]{externalDir.getAbsolutePath() + "%" };

            // Query the MediaStore to get images from external storage
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    null
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    // Create a PostPhoto object and set the imageFilePath
                    PostPhoto postPhoto = new PostPhoto();
                    postPhoto.setImageFilePath(imagePath);
                    imagePaths.add(postPhoto);
                }
                cursor.close();
            }
        }

        return imagePaths;
    }


    private List<PostPhoto> getImagesFromInternalStorage(Context context) {
        List<PostPhoto> imagePaths = new ArrayList<>();
        String[] projection = { MediaStore.Images.Media.DATA };

        // Query the MediaStore to get images from internal storage
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                // Create a PostPhoto object and set the imageFilePath
                PostPhoto postPhoto = new PostPhoto();
                postPhoto.setImageFilePath(imagePath);
                imagePaths.add(postPhoto);
            }
            cursor.close();
        }

        return imagePaths;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                selectedPhotosResult = photoUploadAdapter.getSelectedPhotos();
                Log.d("CheckingPohoto==>>",selectedPhotosResult+"");
                Intent intent = new Intent(PostPhotoListActivity.this,PhotoUploadActivity.class);
                intent.putExtra(Constant.SELECTED_PHOTOLIST,selectedPhotosResult);
                startActivity(intent);
                finish();
                break;

        }
    }
}