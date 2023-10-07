package com.chatapp.nineninechatapp.Adapter.post;

import android.content.Context;
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
import com.chatapp.nineninechatapp.Model.UploadPost.PostPhoto;
import com.chatapp.nineninechatapp.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoUploadViewHolder> {

    private List<PostPhoto> photoList;
    private Context context;
    private Boolean isSelected;
    private int selectImageCount = 0;
    private ArrayList<PostPhoto> selectedPhotos = new ArrayList<>();

    public PhotoListAdapter(List<PostPhoto> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoUploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false);
        return new PhotoUploadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoUploadViewHolder holder, int position) {
        PostPhoto postPhoto = photoList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(postPhoto.getImageFilePath())
                .into(holder.photo);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                postPhoto.setSelected(isChecked);

                if (isChecked) {
                    Log.d("SelectedBorder", isChecked + "");
                    holder.photo.setBackgroundResource(R.drawable.selected_background);
                    selectedPhotos.add(postPhoto);
                } else {
                    holder.photo.setBackgroundResource(0);
                    selectedPhotos.remove(postPhoto);
                }
            }
        });

    }

    public ArrayList<PostPhoto> getSelectedPhotos() {
        return selectedPhotos;
    }

    @Override
    public int getItemCount() {
        if (photoList != null) {
            return photoList.size();
        } else {
            return 0;
        }
    }

    public class PhotoUploadViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private CheckBox checkBox;
        private TextView selectedTextView;

        public PhotoUploadViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imgPhoto);
            checkBox = itemView.findViewById(R.id.checkBox);
            selectedTextView = itemView.findViewById(R.id.txtCount);
        }
    }

}
