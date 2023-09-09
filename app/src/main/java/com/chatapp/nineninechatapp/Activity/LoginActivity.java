package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    ImageView btnShowImageHide;
    EditText edtPassword, edtphone;
    TextView txtSingUp;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    public void initView() {
        serviceProvider = new NetworkServiceProvider(this);
        btnLogin = findViewById(R.id.btn_login);
        edtphone = findViewById(R.id.edt_phone);
        btnShowImageHide = findViewById(R.id.btn_showImageHide);
        btnShowImageHide.setImageResource(R.drawable.visibility_off);
        edtPassword = findViewById(R.id.edt_password);
        txtSingUp = findViewById(R.id.txt_SingUp);
        progressBar = findViewById(R.id.progressBar);

        Utility.darkMode(this);
        edtphone.setText("095310432");
        edtPassword.setText("123456");
    }

    private void initEvent() {
        btnLogin.setOnClickListener(v -> {

            /*if (edtphone.getText().toString().equalsIgnoreCase("")){
                edtphone.startAnimation(Utility.shakeError());
            }else if (edtPassword.getText().toString().equalsIgnoreCase("")){
                edtPassword.startAnimation(Utility.shakeError());
            }else {
                LoginObj loginObj = new LoginObj();
                loginObj.setTelephone(edtphone.getText().toString());
                loginObj.setPassword(edtPassword.getText().toString());
                CallLogin(loginObj);
            }*/
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });

        btnShowImageHide.setOnClickListener(v -> {
            if (edtPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_off);
            } else {
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_fill);
            }
        });

        txtSingUp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });

    }

    private void CallLogin(LoginObj authObj) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.Login(APIURL.DomainName+APIURL.login,authObj).enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCode()==1){

                        Utility.Save_UserProfile(LoginActivity.this,response.body().getData().getData());
                        AppStorePreferences.putInt(LoginActivity.this, AppENUM.LOGIN_CON,1);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                        if (response.body().getCode()==1){

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else if (response.body().getCode()==0){

                            Utility.showToast(LoginActivity.this,response.body().getMsg());

                        }
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

