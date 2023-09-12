package com.chatapp.nineninechatapp.Activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView toolbar;
    ImageView back;
    Switch aSwitch;
    Button btnLogout;
    Spinner spinner;
    public static final String[]languages={"Select labguage","English","Burmese"};
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        btnLogout=findViewById(R.id.btn_logout);
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


//    public void setLocale(String langCode) {
//        Locale locale = new Locale(langCode);
//        Locale.setDefault(locale);
//        Resources resources = getResources();
//        Configuration config = new Configuration(resources.getConfiguration());
//        config.setLocale(locale);
//        resources.updateConfiguration(config, resources.getDisplayMetrics());
//
//        // Save the selected language in SharedPreferences
//        AppStorePreferences.putString(this, "selected_language", langCode);
//    }
//    public void loadLocale(){
//        String langCode = AppStorePreferences.getString(this, "selected_language", "");
//        if (!langCode.isEmpty()){
//            setLocale(langCode);
//        }
//    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:

                finish();

                break;
            case R.id.btn_logout:

                Utility.delete_UserProfile(this);
                AppStorePreferences.putInt(SettingActivity.this, AppENUM.LOGIN_CON,0);
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                finishAffinity();

                break;
        }
    }
}