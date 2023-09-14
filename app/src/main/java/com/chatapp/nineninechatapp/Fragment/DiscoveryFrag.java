package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.PostAdapter;
import com.chatapp.nineninechatapp.Model.Post;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;

public class DiscoveryFrag extends Fragment {

    private LinearLayout postVisibleState;
    private ImageView btnImg;
    private ImageView imgNoti;
    private CardView cardView;
    private PostAdapter postAdapter;
    private ArrayList<Post> postArrayList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discovery, container, false);
        Utility.darkMode(getActivity());

        postVisibleState = view.findViewById(R.id.postVisibleState);
        btnImg = view.findViewById(R.id.btn_img);
        cardView = view.findViewById(R.id.cardView);
        imgNoti = view.findViewById(R.id.imgNoti);
        recyclerView = view.findViewById(R.id.postRecyclerview);

        initView();
        return view;

    }

    private void initView() {
        checkDartModeView();
        postArrayList = new ArrayList<>();
        postArrayList.add(new Post("Justin","https://s.hdnux.com/photos/51/23/24/10827008/4/1200x0.jpg"));
        postArrayList.add(new Post("ရတနာမိုင် ", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRtRxRX9GbLWZflB8f7KWDhVRdaPaqOvMr4WcH2nxLbxL58J8kY"));
        postArrayList.add(new Post("ဝတ်မှုန်ရွှေရည်","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS6ueYPLChazlyyPYKBbdxMe3ck-NlPp0RAkybOCPGUUg_ee8sQ"));
        postArrayList.add(new Post("Justin","https://s.hdnux.com/photos/51/23/24/10827008/4/1200x0.jpg"));

        postAdapter = new PostAdapter(postArrayList,getContext());
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void checkDartModeView() {

        if (AppStorePreferences.getBoolean(getContext(), "dark_mode")) {
            btnImg.setImageResource(R.drawable.down_arrow_white);
            imgNoti.setImageResource(R.drawable.white_noti);
        } else {
            btnImg.setImageResource(R.drawable.down_arrow);
            imgNoti.setImageResource(R.drawable.noti);
        }

        if (AppStorePreferences.getBoolean(getContext(), "dark_mode")) {
            cardView.setOnClickListener(v -> {
                if (postVisibleState.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent(), new AutoTransition());
                    postVisibleState.setVisibility(View.GONE);
                    btnImg.setImageResource(R.drawable.down_arrow_white);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent(), new AutoTransition());
                    postVisibleState.setVisibility(View.VISIBLE);
                    btnImg.setImageResource(R.drawable.up_arrow_white);
                }
            });
        } else {
            cardView.setOnClickListener(v -> {
                if (postVisibleState.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent(), new AutoTransition());
                    postVisibleState.setVisibility(View.GONE);
                    btnImg.setImageResource(R.drawable.down_arrow);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent(), new AutoTransition());
                    postVisibleState.setVisibility(View.VISIBLE);
                    btnImg.setImageResource(R.drawable.up_arrow);
                }
            });
        }
    }
    }


