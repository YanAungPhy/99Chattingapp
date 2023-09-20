package com.chatapp.nineninechatapp.Utils;

import android.content.Context;
import android.util.Log;


import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NetworkServiceProvider {
    Context context;
    Retrofit retrofit;

    public NetworkServiceProvider(Context context) {
        this.context = context;
        RetrofitFactory factory=new RetrofitFactory();
        retrofit=factory.connector();
    }

    public Call<LoginModel> Login (String url, LoginObj obj) {
        NetworkSync.LoginSync sync=retrofit.create(NetworkSync.LoginSync.class);
        return sync.getSync(url,obj);
    }


    public Call<OTP_Model> GetOTP (String url, OTP_Obj obj) {
        NetworkSync.OTPSync sync=retrofit.create(NetworkSync.OTPSync.class);
        return sync.getSync(url,obj);
    }

    public Call<VerifyModel> VerifyOTP (String url, VerifyObj obj) {
        NetworkSync.VerifyOTPSync sync=retrofit.create(NetworkSync.VerifyOTPSync.class);
        return sync.getSync(url,obj);
    }


    public Call<RegisterModel> Register (String url, RegisterObj obj) {
        NetworkSync.RegisterSync sync=retrofit.create(NetworkSync.RegisterSync.class);
        return sync.getSync(url,obj);
    }


}


