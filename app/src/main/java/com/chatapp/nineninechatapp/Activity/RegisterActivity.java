package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

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
    }

    private void initView() {
        Utility.darkMode(this);

        serviceProvider=new NetworkServiceProvider(this);
        txtSingIn = findViewById(R.id.txt_SignIn);
        edtPhone=findViewById(R.id.edt_phone);
        btnOTP=findViewById(R.id.btn_getOTP);
        progressBar=findViewById(R.id.progressBar);
        countryCodePicker=findViewById(R.id.ccp);

        txtSingIn.setOnClickListener(this);
        btnOTP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_SignIn:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.btn_getOTP:
                getOtpCode();
                break;
            case R.id.back:
                finish();
                break;


        }
    };

    private void getOtpCode() {
        String ph=edtPhone.getText().toString();
        if (ph.equalsIgnoreCase("")){
            edtPhone.startAnimation(Utility.shakeError());
        }else {
            CallOTP(edtPhone.getText().toString());
        }
    }

    private void CallOTP(String phone) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.GetOTP(APIURL.DomainName+APIURL.check_phone+phone).enqueue(new Callback<OTP_Model>() {
                @Override
                public void onResponse(Call<OTP_Model> call, Response<OTP_Model> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCon()==true){

                        Intent intent=new Intent(RegisterActivity.this,OTP_Activity.class);
                        intent.putExtra("otp_phone",phone);
                        intent.putExtra("otp_code",response.body().getDataSMS());
                        startActivity(intent);
                        finish();

                    }else if (response.body().getCon()==false){

                        Utility.showToast(RegisterActivity.this,response.body().getMsg());

                    }
                }
                @Override
                public void onFailure(Call<OTP_Model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }



}