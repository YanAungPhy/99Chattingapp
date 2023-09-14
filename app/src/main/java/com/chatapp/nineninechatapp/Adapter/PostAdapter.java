package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Activity.ImagePreviewActivity;
import com.chatapp.nineninechatapp.Fragment.CommentFragment;
import com.chatapp.nineninechatapp.Model.Post;
import com.chatapp.nineninechatapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> postArrayList;
    private Context context;
    private Boolean like = false;

    public PostAdapter(ArrayList<Post> postArrayList, Context context) {
        this.postArrayList = postArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_list, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final Post post = postArrayList.get(position);
        holder.txtName.setText(post.getName());
        Picasso.get().load(post.getAvatar()).into(holder.imgPostPhoto);

        holder.imgLike.setOnClickListener(v -> {
            if(like){
                holder.imgLike.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.like));
                like = false;
            }else {
                holder.imgLike.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.unlike));
                like = true;
            }
        });
        holder.imgPostPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(context, ImagePreviewActivity.class);
            intent.putExtra("Name",post.getName());
            intent.putExtra("Avatar",post.getAvatar());
            context.startActivity(intent);
        });

        holder.imgComment.setOnClickListener(v -> {
            CommentFragment fullBottomDialogFragment = new CommentFragment();
            fullBottomDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), fullBottomDialogFragment.getTag());
            //fullBottomDialogFragment.show(fullBottomDialogFragment.getActivity().getSupportFragmentManager(), fullBottomDialogFragment.getTag());

            CommentFragment commentFragment = new CommentFragment();
            commentFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), commentFragment.getTag());

        });
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imgLike;
        private ImageView imgPostPhoto;
        private ImageView imgComment;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgPostPhoto = itemView.findViewById(R.id.imgPostPhoto);
            imgComment = itemView.findViewById(R.id.imgComment);
        }
    }
}
