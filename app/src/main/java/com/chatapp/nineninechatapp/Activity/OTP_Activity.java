package com.chatapp.nineninechatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.otpview.OTPListener;
import com.otpview.OTPTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Activity extends AppCompatActivity {

    TextView timer,resend_otp,otpPhone;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    RelativeLayout btnOTP;
    OTP_Obj otpObj;
    private CountDownTimer countDownTimer;
    private long timerDurationInMillis = 60000; // 60 seconds (adjust as needed)
    private long timerIntervalInMillis = 1000;
    OTPTextView otpTextView;

    String otp_code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        Utility.darkMode(this);
        initView();
    }

    private void initView() {
        mtoolbar();
        otp_code=(String)getIntent().getSerializableExtra("otp_code");
        otpObj=(OTP_Obj) getIntent().getSerializableExtra("otp_model");
        serviceProvider=new NetworkServiceProvider(this);
        timer=findViewById(R.id.timer);
        progressBar=findViewById(R.id.progressBar);
        otpTextView=findViewById(R.id.otp_view);
        otpPhone=findViewById(R.id.otp_phone);
        otpPhone.setText(otpObj.getTelephone());
        otpTextView.requestFocusOTP();
       // ed_otp=findViewById(R.id.edt_otp);
        btnOTP=findViewById(R.id.btn_getOTP);
        resend_otp=findViewById(R.id.resend_otp);
        startTimer();

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(@NonNull String s) {
                doing_otp();
            }
        });
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doing_otp();
            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallOTP(otpObj);
                countDownTimer.start();
            }
        });

    }

    public void doing_otp(){
        if (otpTextView.getOtp().equalsIgnoreCase("") || !otpTextView.getOtp().equalsIgnoreCase(otp_code)){
            otpTextView.startAnimation(Utility.shakeError());
            otpTextView.showError();
        }else {
            VerifyObj verifyObj=new VerifyObj();
            verifyObj.setTelephone(otpObj.getTelephone());
            verifyObj.setAreaCode(otpObj.getAreaCode());
            verifyObj.setOptCode(otpTextView.getOtp());
            CallVerifyOTP(verifyObj);
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timerDurationInMillis, timerIntervalInMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                timer.setText("( " +secondsRemaining+ " )");
                resend_otp.setClickable(false);
                resend_otp.setTextColor(getResources().getColor(R.color.gray));
            }
            @Override
            public void onFinish() {
                resend_otp.setClickable(true);
                resend_otp.setTextColor(getResources().getColor(R.color.primary_color));
                // The timer has finished, handle this event (e.g., resend OTP)

            }
        };
        countDownTimer.start();
    }

    private void CallVerifyOTP(VerifyObj otpObj) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.VerifyOTP(APIURL.DomainName+APIURL.verify_telephone,otpObj).enqueue(new Callback<VerifyModel>() {
                @Override
                public void onResponse(Call<VerifyModel> call, Response<VerifyModel> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body().getCode()==1){

                        Intent intent=new Intent(OTP_Activity.this,RegisterDetailsActivity.class);
                        intent.putExtra("verify_obj",otpObj);
                        startActivity(intent);
                        finish();

                    }else if (response.body().getCode()==0){
                        Utility.showToast(OTP_Activity.this,response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<VerifyModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }


    private void CallOTP(OTP_Obj otpObj) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.GetOTP(APIURL.DomainName+APIURL.get_otp,otpObj).enqueue(new Callback<OTP_Model>() {
                @Override
                public void onResponse(Call<OTP_Model> call, Response<OTP_Model> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body().getCode()==1){

                        otp_code= String.valueOf(response.body().getCode());
                        Utility.showToast(OTP_Activity.this,response.body().getMsg());

                    }else if (response.body().getCode()==0){

                        Utility.showToast(OTP_Activity.this,response.body().getMsg());

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


    public void mtoolbar(){
        TextView toolbar=findViewById(R.id.toolbar_com);
        ImageView back=findViewById(R.id.back);
        toolbar.setText(R.string.register);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (AppStorePreferences.getBoolean(getApplicationContext(),"dark_mode")){
            back.setImageResource(R.drawable.back_white);
            toolbar.setTextColor(getResources().getColor(R.color.white));
        }else {
            back.setImageResource(R.drawable.back_black);
            toolbar.setTextColor(getResources().getColor(R.color.black));
        }
    }
}
