package com.chatapp.nineninechatapp.Utils;

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.UploadImgModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    public interface RegisterSync {
        @POST
        Call<RegisterModel> getSync(@Url String url, @Body RegisterObj obj);
    }


    public interface UserImgSync {
        @Multipart
        @POST
        Call<UploadImgModel> UserImg(@Url String url,
                                     @Part("telephone") RequestBody telephone,
                                     @Part MultipartBody.Part food_image

        );
    }

}


