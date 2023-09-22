package com.chatapp.nineninechatapp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chatapp.nineninechatapp.Model.FindNickName.NickNameModel;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameObj;
import com.chatapp.nineninechatapp.Model.Login.LogoutModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView toolbar;
    ImageView back;
    Switch aSwitch;
    Button btnLogout;
    Spinner spinner;
    public static final String[]languages={"Select labguage","English","Burmese"};
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        serviceProvider=new NetworkServiceProvider(this);
        btnLogout=findViewById(R.id.btn_logout);
        aSwitch=findViewById(R.id.btn_switch);
        progressBar=findViewById(R.id.progressBar);
        mtoolbar();

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

        btnLogout.setOnClickListener(this);

        spinner=findViewById(R.id.spinner_b);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, languages);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("English")) {
                    Utility.setLocale("en",SettingActivity.this);
                  showLanguageChangeConfirmDialogBox();
                } else if (selectedLang.equals("Burmese")) {
                    Utility.setLocale("my",SettingActivity.this);
                    showLanguageChangeConfirmDialogBox();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case where nothing is selected
            }
        });

    }
    private void showLanguageChangeConfirmDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Language?");
        builder.setMessage("Changing the language will restart the app. Do you want to continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                restartApp();
            }
        });
        builder.setNegativeButton("No", null); // Do nothing on No button click
        builder.show();
    }
    private void restartApp() {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:

                finish();

                break;
            case R.id.btn_logout:

                CallLogout();

                break;
        }
    }

    public void mtoolbar(){
        toolbar=findViewById(R.id.toolbar_com);
        back=findViewById(R.id.back);
        toolbar.setText(R.string.setting);
        back.setOnClickListener(this);
        if (AppStorePreferences.getBoolean(getApplicationContext(),"dark_mode")){
            back.setImageResource(R.drawable.back_white);
            toolbar.setTextColor(getResources().getColor(R.color.white));
        }else {
            back.setImageResource(R.drawable.back_black);
            toolbar.setTextColor(getResources().getColor(R.color.black));
        }
    }

    private void CallLogout() {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.Logout(APIURL.DomainName+APIURL.logout).enqueue(new Callback<LogoutModel>() {
                @Override
                public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                    progressBar.setVisibility(View.GONE);

                    Utility.delete_UserProfile(SettingActivity.this);
                    AppStorePreferences.putInt(SettingActivity.this, AppENUM.LOGIN_CON,0);
                    AppStorePreferences.putString(SettingActivity.this,AppENUM.TOKEN,"");
                    AppStorePreferences.putString(SettingActivity.this,AppENUM.FCM_TOKEN,"");
                    startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                    finishAffinity();
                }
                @Override
                public void onFailure(Call<LogoutModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }

}