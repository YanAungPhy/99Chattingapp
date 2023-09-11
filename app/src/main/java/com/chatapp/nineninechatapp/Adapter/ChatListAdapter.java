package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Activity.ChatActivity;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.model.ChatModel;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    ArrayList<ChatModel>  itemList;
    Context mContext;

    public ChatListAdapter(ArrayList<ChatModel> itemList,Context context) {
        this.itemList = itemList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel item = itemList.get(position);
        holder.textViewName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final   TextView txtNotification;
        private final  LinearLayout clickLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            clickLayout = itemView.findViewById(R.id.click_layout);
            textViewName = itemView.findViewById(R.id.txt_Name);
            txtNotification = itemView.findViewById(R.id.txt_notification);

            clickLayout.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(),ChatActivity.class);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
