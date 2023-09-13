package com.chatapp.nineninechatapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chatapp.nineninechatapp.EventBusModel.StringBus;
import com.chatapp.nineninechatapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

public class DialogImagePicker extends BottomSheetDialog implements View.OnClickListener{

    LinearLayout camera,gallery;
    Context context;

    public DialogImagePicker(@NonNull @NotNull Context context) {
        super(context);
    }

    public DialogImagePicker(@NonNull @NotNull Context context, int theme) {
        super(context, theme);
        this.context=context;

    }

    protected DialogImagePicker(@NonNull @NotNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        camera=findViewById(R.id.camera);
        gallery=findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBus stringBus=new StringBus();
                stringBus.setEvent_name("camera");
                EventBus.getDefault().post(stringBus);
                dismiss();

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBus stringBus=new StringBus();
                stringBus.setEvent_name("gallery");
                EventBus.getDefault().post(stringBus);
                dismiss();

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){


        }
    }
}
