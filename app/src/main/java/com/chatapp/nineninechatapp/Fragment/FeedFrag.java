package com.chatapp.nineninechatapp.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.FeedAdapter;
import com.chatapp.nineninechatapp.Model.Feed.FeedModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;

public class FeedFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FeedAdapter adapter;
    ArrayList<FeedModel> datalist=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feed,container,false);
        Utility.darkMode(getActivity());

        recyclerView=view.findViewById(R.id.recyclerView);

        FeedModel feedModel=new FeedModel();
        feedModel.setVideoUrl(String.valueOf(R.raw.snaptik_one));
        feedModel.setLike("0");
        feedModel.setLikeCount("0");
        feedModel.setShare("0");
        feedModel.setShareCount("0");

        FeedModel feedModel1=new FeedModel();
        feedModel1.setVideoUrl(String.valueOf(R.raw.snaptik_two));
        feedModel1.setLike("0");
        feedModel1.setLikeCount("0");
        feedModel1.setShare("0");
        feedModel1.setShareCount("0");

        FeedModel feedModel2=new FeedModel();
        feedModel2.setVideoUrl(String.valueOf(R.raw.snaptik_three));
        feedModel2.setLike("0");
        feedModel2.setLikeCount("0");
        feedModel2.setShare("0");
        feedModel2.setShareCount("0");

        datalist.add(feedModel);
        datalist.add(feedModel1);
        datalist.add(feedModel2);

        adapter= new FeedAdapter(getActivity(),datalist);
        layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setClick(this);

        return view;
    }
}
