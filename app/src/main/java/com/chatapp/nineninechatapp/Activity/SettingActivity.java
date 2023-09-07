package com.chatapp.nineninechatapp.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
=======
import android.os.Bundle;
import android.view.View;
>>>>>>> YAP
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chatapp.nineninechatapp.R;
<<<<<<< HEAD
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;
=======
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
>>>>>>> YAP

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView toolbar;
    ImageView back;
    Switch aSwitch;
<<<<<<< HEAD
    Button btnLogout;

=======
>>>>>>> YAP
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

<<<<<<< HEAD
        btnLogout=findViewById(R.id.btn_logout);
=======
>>>>>>> YAP
        back=findViewById(R.id.back);
        toolbar=findViewById(R.id.toolbar_com);
        aSwitch=findViewById(R.id.btn_switch);
        toolbar.setText(R.string.setting);


        if (AppStorePreferences.getBoolean(SettingActivity.this,"dark_mode")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            aSwitch.setChecked(true);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            aSwitch.setChecked(false);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    AppStorePreferences.putBoolean(SettingActivity.this,"dark_mode",true);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    AppStorePreferences.putBoolean(SettingActivity.this,"dark_mode",false);
                }
            }
        });

        back.setOnClickListener(this);
<<<<<<< HEAD
        btnLogout.setOnClickListener(this);
=======
>>>>>>> YAP
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:

                finish();

                break;
<<<<<<< HEAD
            case R.id.btn_logout:

                Utility.delete_UserProfile(this);
                AppStorePreferences.putInt(SettingActivity.this, AppENUM.LOGIN_CON,0);
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                finishAffinity();

                break;
=======
>>>>>>> YAP
        }
    }
}