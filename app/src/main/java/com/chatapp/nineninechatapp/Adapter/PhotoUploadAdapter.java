package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.Model.UploadPost.PostPhoto;
import com.chatapp.nineninechatapp.R;

import java.io.File;
import java.util.List;

public class PhotoUploadAdapter extends RecyclerView.Adapter<PhotoUploadAdapter.PhotoUploadViewHolder> {

    private List<PostPhoto> photoList;
    private Context context;
    private Boolean isSelected;

    public PhotoUploadAdapter(List<PostPhoto> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoUploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false);
        return new PhotoUploadAdapter.PhotoUploadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoUploadViewHolder holder, int position) {
        PostPhoto postPhoto = photoList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(postPhoto.getImageFilePath())
                .into(holder.photo);

       /* if(isSelected){
            Log.d("CheckingSelectedState",postPhoto.isSelected()+"");
        }else {
            Log.d("CheckingSelectedState",postPhoto.isSelected()+"");
        }*/

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                postPhoto.setSelected(isChecked);

                if (isChecked){
                    Log.d("SelectedBorder",isChecked+"");
                    holder.photo.setBackgroundResource(R.drawable.selected_background);
                }else {
                    holder.photo.setBackgroundResource(0);
                    Log.d("SelectedBorder",isChecked+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size() ;
    }

    public static class PhotoUploadViewHolder extends RecyclerView.ViewHolder{
        private ImageView photo;
        private CheckBox checkBox;

        public PhotoUploadViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imgPhoto);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
