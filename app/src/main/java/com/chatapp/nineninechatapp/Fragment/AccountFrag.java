package com.chatapp.nineninechatapp.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Activity.SettingActivity;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFrag extends Fragment implements View.OnClickListener {

    ImageView setting;
    TextView name;
    CircleImageView imageView;
    UserObj userObj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account,container,false);
        Utility.darkMode(getActivity());
        userObj=Utility.query_UserProfile(getActivity());
        setting=view.findViewById(R.id.img_setting);
        imageView=view.findViewById(R.id.circularImageView);
        name=view.findViewById(R.id.txt_status);

        show_User(userObj);

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

    public void show_User(UserObj userObj){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(getActivity()).load(userObj.getImagePath())
                .apply(requestOptions)
                .into(imageView);

        name.setText(userObj.getName());
    }

}
