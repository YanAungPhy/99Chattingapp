package com.chatapp.nineninechatapp.Utils;

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class NetworkSync {

    public interface LoginSync {
        @POST
        Call<LoginModel> getSync(@Url String url, @Body LoginObj obj);
    }

    public interface OTPSync {
        @POST
        Call<OTP_Model> getSync(@Url String url, @Body OTP_Obj obj);
    }


    public interface VerifyOTPSync {
        @POST
        Call<VerifyModel> getSync(@Url String url, @Body VerifyObj obj);
    }

}


