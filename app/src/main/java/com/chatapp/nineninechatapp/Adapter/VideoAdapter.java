package com.chatapp.nineninechatapp.Adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Model.ReqFriendList.VideoModel;
import com.chatapp.nineninechatapp.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<VideoModel> videoList;

    public VideoAdapter(List<VideoModel> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoModel video = videoList.get(position);
        holder.titleTextView.setText(video.getTitle());
        holder.videoView.setVideoURI(Uri.parse(video.getFilePath()));
        holder.videoView.start();
        Log.d("CheckingVideoUrl",video.getFilePath());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        private MediaController mediaController;
        VideoView videoView;
        // Add other views for video details

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView);
            videoView = itemView.findViewById(R.id.video_view);

            /*mediaController = new MediaController(itemView.getContext());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);*/
        }
    }

}