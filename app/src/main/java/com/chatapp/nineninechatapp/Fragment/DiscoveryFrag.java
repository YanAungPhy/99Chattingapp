package com.chatapp.nineninechatapp.Fragment;

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

import com.chatapp.nineninechatapp.Activity.MainActivity;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

public class DiscoveryFrag extends Fragment {

    LinearLayout postVisibleState;
    ImageView btnImg;
    ImageView imgNoti;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.discovery,container,false);
        Utility.darkMode(getActivity());

        postVisibleState = view.findViewById(R.id.postVisibleState);
        btnImg = view.findViewById(R.id.btn_img);
        cardView = view.findViewById(R.id.cardView);
        imgNoti = view.findViewById(R.id.imgNoti);

        initView();
        return view;

    }

    private void initView(){
         checkDartModeView();

    }

    private void checkDartModeView(){

        if (AppStorePreferences.getBoolean(getContext(),"dark_mode")){
            btnImg.setImageResource(R.drawable.down_arrow_white);
            imgNoti.setImageResource(R.drawable.white_noti);
        }else {
            btnImg.setImageResource(R.drawable.down_arrow);
            imgNoti.setImageResource(R.drawable.noti);
        }

        if (AppStorePreferences.getBoolean(getContext(),"dark_mode")){
            btnImg.setOnClickListener(v -> {
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
        }else {
            btnImg.setOnClickListener(v -> {
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
