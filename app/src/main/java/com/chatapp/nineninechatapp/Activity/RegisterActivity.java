package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
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

public class RegisterActivity extends AppCompatActivity {

    Button btnOTP;
    TextView txtSingIn;
    EditText edtPhone;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Utility.darkMode(this);
        initView();
        initEvent();
    }

    private void initView() {
        serviceProvider=new NetworkServiceProvider(this);
        txtSingIn = findViewById(R.id.txt_SignIn);
        edtPhone=findViewById(R.id.edt_phone);
        btnOTP=findViewById(R.id.btn_getOTP);
        progressBar=findViewById(R.id.progressBar);
        countryCodePicker=findViewById(R.id.ccp);
    }

    private void initEvent() {
        Utility.darkMode(this);
        txtSingIn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph=edtPhone.getText().toString();
                if (ph.equalsIgnoreCase("")){
                    edtPhone.startAnimation(Utility.shakeError());
                }else {
                    OTP_Obj otpObj=new OTP_Obj();
                    otpObj.setAreaCode(countryCodePicker.getSelectedCountryCodeWithPlus());
                    otpObj.setTelephone(edtPhone.getText().toString());
                    CallOTP(otpObj);
                }

               // startActivity(new Intent(RegisterActivity.this,OTP_Activity.class));

            }
        });
    }

    private void CallOTP(OTP_Obj otpObj) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.GetOTP(APIURL.DomainName+APIURL.get_otp,otpObj).enqueue(new Callback<OTP_Model>() {
                @Override
                public void onResponse(Call<OTP_Model> call, Response<OTP_Model> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body().getCode()==1){

                        Intent intent=new Intent(RegisterActivity.this,OTP_Activity.class);
                        intent.putExtra("otp_model",otpObj);
                        startActivity(intent);
                        finish();


                    }else if (response.body().getCode()==0){

                        Utility.showToast(RegisterActivity.this,response.body().getMsg());

                    }
                }
                @Override
                public void onFailure(Call<OTP_Model> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }
}