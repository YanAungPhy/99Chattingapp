package com.chatapp.nineninechatapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.ChatListAdapter;
import com.chatapp.nineninechatapp.Model.ChatModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;

public class HomeFrag extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ChatModel> chatList;
    private ChatListAdapter adapter;
    private Context context;
    private ImageView imgNoti;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        Utility.darkMode(getActivity());


        recyclerView = view.findViewById(R.id.chat_RecyclerView);
        imgNoti = view.findViewById(R.id.noti);
        chatList = new ArrayList<>();
        chatList.add(new ChatModel("Sai Sai"));
        chatList.add(new ChatModel("Ko Thant"));
        chatList.add(new ChatModel("Ko Kyaw Latt"));
        chatList.add(new ChatModel("Testing"));
        chatList.add(new ChatModel("Yan Aung Phyo"));

        adapter = new ChatListAdapter(chatList, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initView();
        return view;
    }

    private void initView() {
        checkDartModeView();
    }


    private void checkDartModeView(){
        if (AppStorePreferences.getBoolean(getContext(),"dark_mode")){
            imgNoti.setImageResource(R.drawable.white_noti);
        }else {
            imgNoti.setImageResource(R.drawable.noti);
        }
    }
}
