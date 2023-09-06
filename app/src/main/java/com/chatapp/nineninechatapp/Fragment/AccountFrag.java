package com.chatapp.nineninechatapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chatapp.nineninechatapp.Activity.SettingActivity;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;

public class AccountFrag extends Fragment implements View.OnClickListener {

    ImageView setting;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account,container,false);
        Utility.darkMode(getActivity());

        setting=view.findViewById(R.id.img_setting);

<<<<<<< HEAD

        setting.setOnClickListener(this);

=======
>>>>>>> origin/master
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
}
