package com.chatapp.nineninechatapp.Adapter.post;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.Activity.ImagePreviewActivity;
import com.chatapp.nineninechatapp.Model.UploadPost.PostPhoto;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoUploadAdapter extends RecyclerView.Adapter<PhotoUploadAdapter.ViewHolder> {

    private List<PostPhoto> photoList;
    private Context context;

    public PhotoUploadAdapter(List<PostPhoto> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_photo_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostPhoto postPhoto = photoList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(postPhoto.getImageFilePath())
                .into(holder.photo);

        holder.photo.setOnClickListener(v -> {
            Intent intent = new Intent(context, ImagePreviewActivity.class);
            intent.putExtra(Constant.IMAGEVIEW,postPhoto.getImageFilePath());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (photoList != null) {
            return photoList.size();
        } else {
            return 0;
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView photo;
        private CheckBox checkBox;
        private TextView selectedTextView;
        private ImageView imgCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imgPhoto);
            checkBox = itemView.findViewById(R.id.checkBox);
            selectedTextView = itemView.findViewById(R.id.txtCount);
            imgCancel = itemView.findViewById(R.id.imgCancel);

            imgCancel.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Log.d("CheckingAdapterPosition",position+"");
                if (position != RecyclerView.NO_POSITION) {
                    photoList.remove(position);
                    notifyItemRemoved(position);

                }
            });

        }

    }
}
