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

import com.chatapp.nineninechatapp.R;

public class DiscoveryFrag extends Fragment {

    LinearLayout postVisibleState;
    ImageView btnImg;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.discovery,container,false);

        postVisibleState = view.findViewById(R.id.postVisibleState);
        btnImg = view.findViewById(R.id.btn_img);
        cardView = view.findViewById(R.id.cardView);

        initView();
        return view;
    }

    private void initView(){
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
