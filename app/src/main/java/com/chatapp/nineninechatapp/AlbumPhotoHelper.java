package com.chatapp.nineninechatapp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumPhotoHelper {

    public static Map<String, List<String>> getAlbumsWithPhotos(Context context) {
        Map<String, List<String>> albumMap = new HashMap<>();
        ContentResolver contentResolver = context.getContentResolver();

        // Define the columns you want to retrieve for albums
        String[] albumProjection = {
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA
        };

        // Query for all images
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor albumCursor = contentResolver.query(
                uri,
                albumProjection,
                null,
                null,
                null
        );

        if (albumCursor != null && albumCursor.moveToFirst()) {
            int albumNameColumn = albumCursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int photoPathColumn = albumCursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                String albumName = albumCursor.getString(albumNameColumn);
                String photoPath = albumCursor.getString(photoPathColumn);

                if (!albumMap.containsKey(albumName)) {
                    albumMap.put(albumName, new ArrayList<>());
                }

                albumMap.get(albumName).add(photoPath);
            } while (albumCursor.moveToNext());

            albumCursor.close();
        }

        return albumMap;
    }
}
