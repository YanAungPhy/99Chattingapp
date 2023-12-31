package com.chatapp.nineninechatapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Activity.PhotoUploadActivity;
import com.chatapp.nineninechatapp.Activity.VideoUploadActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Adapter.post.PostAdapter;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.Model.Post;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiscoveryFrag extends Fragment implements View.OnClickListener {

    private LinearLayout postVisibleState;
    private ImageView btnImg;
    private ImageView imgNoti;
    private CardView cardView;
    private PostAdapter postAdapter;
    private ArrayList<Post> postArrayList;
    private RecyclerView recyclerView;
    private ImageView btnImgVideo;
    private ImageView btnImgPhoto;

    private CircleImageView imageView;
    UserObj userObj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discovery, container, false);
        //userObj=Utility.query_UserProfile(getActivity());
        Utility.darkMode(getActivity());

        postVisibleState = view.findViewById(R.id.postVisibleState);
        btnImg = view.findViewById(R.id.btn_img);
        cardView = view.findViewById(R.id.cardView);
        imgNoti = view.findViewById(R.id.imgNoti);
        recyclerView = view.findViewById(R.id.postRecyclerview);
        btnImgVideo =  view.findViewById(R.id.btnImgVideo);
        imageView =  view.findViewById(R.id.circularImageView);
        btnImgPhoto = view.findViewById(R.id.btnImgPhoto);

        initView();
        return view;

    }

    private void initView() {
        /*RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(getActivity()).load(APIURL.ImageUrl+userObj.getImagePath()).apply(requestOptions).into(imageView);*/

        checkDartModeView();
        postArrayList = new ArrayList<>();
        postArrayList.add(new Post("Justin","https://s.hdnux.com/photos/51/23/24/10827008/4/1200x0.jpg"));
        postArrayList.add(new Post("ရတနာမိုင် ", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRtRxRX9GbLWZflB8f7KWDhVRdaPaqOvMr4WcH2nxLbxL58J8kY"));
        postArrayList.add(new Post("ဝတ်မှုန်ရွှေရည်","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS6ueYPLChazlyyPYKBbdxMe3ck-NlPp0RAkybOCPGUUg_ee8sQ"));
        postArrayList.add(new Post("Justin","https://s.hdnux.com/photos/51/23/24/10827008/4/1200x0.jpg"));

        postAdapter = new PostAdapter(postArrayList,getContext());
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnImgVideo.setOnClickListener(this);
        btnImgPhoto.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnImgVideo:
                startActivity(new Intent(getActivity(), VideoUploadActivity.class));
                break;
            case R.id.btnImgPhoto:
                startActivity(new Intent(getActivity(), PhotoUploadActivity.class));
                break;
        }
    }
}


