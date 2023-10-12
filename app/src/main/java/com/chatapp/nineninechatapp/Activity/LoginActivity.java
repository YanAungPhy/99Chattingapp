package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Post;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    ImageView btnShowImageHide;
    EditText edtPassword, edtphone;
    TextView txtSingUp;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        Utility.FullScreen(this);

        String name = getIntent().getStringExtra("Name");

    }

    public void initView() {
        serviceProvider = new NetworkServiceProvider(this);
        btnLogin = findViewById(R.id.btn_login);
        edtphone = findViewById(R.id.edt_phone);
        countryCodePicker=findViewById(R.id.ccp);
        btnShowImageHide = findViewById(R.id.btn_showImageHide);
        btnShowImageHide.setImageResource(R.drawable.visibility_off);
        edtPassword = findViewById(R.id.edt_password);
        txtSingUp = findViewById(R.id.txt_SingUp);
        progressBar = findViewById(R.id.progressBar);

        Utility.darkMode(this);
        btnLogin.setOnClickListener(this);
        btnShowImageHide.setOnClickListener(this);
        txtSingUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_showImageHide:
                passwordVisibleState();
                break;
            case R.id.txt_SingUp:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }

    }

    private void passwordVisibleState() {
        if (edtPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowImageHide.setImageResource(R.drawable.visibility_off);
        } else {
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowImageHide.setImageResource(R.drawable.visibility_fill);
        }
    }

    private void login(){
         if (edtphone.getText().toString().equalsIgnoreCase("")){
                edtphone.startAnimation(Utility.shakeError());
            }else if (edtPassword.getText().toString().equalsIgnoreCase("")){
                edtPassword.startAnimation(Utility.shakeError());
            }else {
                LoginObj loginObj = new LoginObj();
                loginObj.setPhone(edtphone.getText().toString());
                loginObj.setPassword(edtPassword.getText().toString());
                loginObj.setFirebaseToken(AppStorePreferences.getString(LoginActivity.this,AppENUM.FCM_TOKEN));
                CallLogin(loginObj);
            }

    }

    private void CallLogin(LoginObj authObj) {
        AppStorePreferences.putString(LoginActivity.this,AppENUM.TOKEN,"");
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.Login(APIURL.DomainName+APIURL.login,authObj).enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    progressBar.setVisibility(View.GONE);

                    Utility.showToast(getApplicationContext(),response.body().getMsg());
                    if (response.body().isCon()){
                        Utility.showToast(getApplicationContext(),response.body().getMsg());
                        AppStorePreferences.putInt(LoginActivity.this, AppENUM.LOGIN_CON,1);
                        AppStorePreferences.putString(LoginActivity.this, AppENUM.TOKEN,response.body().token);
                       // Utility.Save_UserProfile(LoginActivity.this,response.body().getData().getData());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();

                    }else {
                        Utility.showToast(getApplicationContext(),response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }

}

