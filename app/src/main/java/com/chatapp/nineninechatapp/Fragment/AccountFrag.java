package com.chatapp.nineninechatapp.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.chatapp.nineninechatapp.Activity.SettingActivity;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;

public class AccountFrag extends Fragment implements View.OnClickListener {

    ImageView setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account,container,false);
        Utility.darkMode(getActivity());
        setting=view.findViewById(R.id.img_setting);

        setting.setOnClickListener(this);
        checkDartView();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    private void  checkDartView(){
        if (AppStorePreferences.getBoolean(getContext(),"dark_mode")){
            setting.setImageResource(R.drawable.white_setting);
        }else {
            setting.setImageResource(R.drawable.setting);
        }
    }

}
