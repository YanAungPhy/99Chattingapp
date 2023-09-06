package com.chatapp.nineninechatapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Activity.ChatActivity;
import com.chatapp.nineninechatapp.Adapter.ChatListAdapter;
import com.chatapp.nineninechatapp.R;
<<<<<<< HEAD
import com.chatapp.nineninechatapp.Utils.Utility;
=======
import com.chatapp.nineninechatapp.model.ChatModel;

import java.util.ArrayList;
>>>>>>> origin/master

public class HomeFrag extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ChatModel> chatList;
    private ChatListAdapter adapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        View view=inflater.inflate(R.layout.home,container,false);
        Utility.darkMode(getActivity());
=======
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.chat_RecyclerView);
        chatList = new ArrayList<>();
        chatList.add(new ChatModel("Sai Sai"));
        chatList.add(new ChatModel("Ko Thant"));
        chatList.add(new ChatModel("Ko Kyaw Latt"));
        chatList.add(new ChatModel("Testing"));
        chatList.add(new ChatModel("Yan Aung Phyo"));
>>>>>>> origin/master

        adapter = new ChatListAdapter(chatList, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initView();
        return view;
    }

    private void initView() {

    }
}
