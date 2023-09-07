package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

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
<<<<<<< HEAD
import com.chatapp.nineninechatapp.Utils.AppENUM;
=======
>>>>>>> YAP
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    ImageView btnShowImageHide;
    EditText edtPassword,edtphone;
    TextView txtSingUp;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serviceProvider=new NetworkServiceProvider(this);
        initView();
        initEvent();
    }

    public void initView(){
        btnLogin = findViewById(R.id.btn_login);
        edtphone=findViewById(R.id.edt_phone);
        btnShowImageHide = findViewById(R.id.btn_showImageHide);
        btnShowImageHide.setImageResource(R.drawable.visibility_off);
        edtPassword = findViewById(R.id.edt_password);
        txtSingUp = findViewById(R.id.txt_SingUp);
        progressBar=findViewById(R.id.progressBar);

        Utility.darkMode(this);
<<<<<<< HEAD
        edtphone.setText("095310432");
        edtPassword.setText("123456");
=======
>>>>>>> YAP
    }

    private void initEvent() {
        btnLogin.setOnClickListener(v -> {
<<<<<<< HEAD
           // startActivity(new Intent(getApplicationContext(),MainActivity.class));

            LoginObj loginObj=new LoginObj();
            loginObj.setTelephone(edtphone.getText().toString());
            loginObj.setPassword(edtPassword.getText().toString());
            CallLogin(loginObj);
=======
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

           /* LoginObj loginObj=new LoginObj();
            loginObj.setTelephone(edtphone.getText().toString());
            loginObj.setPassword(edtPassword.getText().toString());
            CallLogin(loginObj);*/
>>>>>>> YAP

        });

        btnShowImageHide.setOnClickListener(v -> {
            if(edtPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_off);
            }else {
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_fill);
            }
        });

        txtSingUp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });

    }

    public void CallLogin(LoginObj authObj) {
<<<<<<< HEAD
=======

>>>>>>> YAP
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.Login(APIURL.DomainName+APIURL.login,authObj).enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    progressBar.setVisibility(View.GONE);

<<<<<<< HEAD
                    if (response.body().getCode()==1){

                        Utility.Save_UserProfile(LoginActivity.this,response.body().getData().getData());
                        AppStorePreferences.putInt(LoginActivity.this, AppENUM.LOGIN_CON,1);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
=======
                    Log.e("mtt_userData>>>", String.valueOf(response.body().getTimestamp()));
                    Log.e("mtt>>>",response.body().getMsg());
                    if (response.body().getCode()==1){

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
>>>>>>> YAP

                    }else if (response.body().getCode()==0){

                        Utility.showToast(LoginActivity.this,response.body().getMsg());

                    }
                }
                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
<<<<<<< HEAD
=======
                    Log.e("mtt_error>>>>",t.getLocalizedMessage());
>>>>>>> YAP
                    progressBar.setVisibility(View.GONE);

                }
            });

        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }
}