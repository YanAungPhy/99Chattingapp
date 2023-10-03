package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.Activity.VideoUploadActivity;

import com.chatapp.nineninechatapp.Model.UploadPost.VideoModel;
import com.chatapp.nineninechatapp.R;

import java.io.File;
import java.util.List;

public class VideoUploadAdapter extends RecyclerView.Adapter<VideoUploadAdapter.ViewHolder> {
    private List<VideoModel> videoList;
    private Context context;

    public VideoUploadAdapter(List<VideoModel> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
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
        Log.d("GetDuration",video.getDuration()+"");
        Glide.with(context)
                .load(Uri.fromFile(new File(video.getFilePath())))
                .thumbnail(0.5f)
                .into(holder.videoView);

        holder.eventFrameLayout.setOnClickListener(v -> {
            if (context != null) {
                Intent intent = new Intent(context, VideoUploadActivity.class);
                intent.putExtra("filePath", video.getFilePath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                // Handle the case where context is null (e.g., log an error).
                Log.d("VideoAdapter", "Context is null");
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        private ImageView videoView;
       private  TextView txtDuration;
       private FrameLayout eventFrameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView);
            videoView = itemView.findViewById(R.id.video_view);
            txtDuration = itemView.findViewById(R.id.txtDuration);
            eventFrameLayout = itemView.findViewById(R.id.eventFrameLayout);

        }
    }

}
