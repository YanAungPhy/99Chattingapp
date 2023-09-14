package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.chatapp.nineninechatapp.R;


public class CommentFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_view, container, false);

        // Find the close button in your layout
        Button closeButton = view.findViewById(R.id.btnClick);

        // Set a click listener for the close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apply an animation before dismissing the dialog
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom);
                view.startAnimation(animation);

                // Dismiss the dialog after the animation
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Animation started
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Animation ended, dismiss the dialog
                        dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // Animation repeated
                    }
                });
            }
        });

        return view;
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
}
