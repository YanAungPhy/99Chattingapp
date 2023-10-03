package com.chatapp.nineninechatapp.Utils;

import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptModel;
import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptObj;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendModel;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendObj;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameModel;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameObj;
import com.chatapp.nineninechatapp.Model.FindNickName.Noti_Obj;
import com.chatapp.nineninechatapp.Model.FindNickName.ResponNoti;
import com.chatapp.nineninechatapp.Model.FriendList.FriendListModel;
import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Login.LogoutModel;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.UploadImgModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriModel;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriendListObj;
import com.chatapp.nineninechatapp.Model.VideoUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public class NetworkSync {

    public interface LoginSync {
        @POST
        Call<LoginModel> getSync(@Url String url, @Body LoginObj obj);
    }


    public interface NotiSync {
        @Headers({
                "Content-Type:application/json",
                "Authorization:key=AAAAhz4nlCQ:APA91bFUf4_kDowoU6kFOt1Ssn1HI6qrqj6JZnXJmYsEtuA5W-iz-oOgsLuaFga1UIRtIAEnMpjMCxtek9CEsMUADQ4GKjnxIE6Ag-ylyGOBx1LBOnqM2x0-iw5niYZJ_y0d7VY2-aSY"
        }
        )
        @POST
        Call<ResponNoti> get_notification(@Url String url, @Body Noti_Obj body);
    }


    public interface UserImgSync {
        @Multipart
        @POST
        Call<UploadImgModel> UserImg(@Url String url,
                                     @Part("telephone") RequestBody telephone,
                                     @Part("firebase_token") RequestBody firebase_token,
                                     @Part MultipartBody.Part food_image
        );
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


    public interface FindNickNameSync {
        @POST
        Call<NickNameModel> getSync(@Url String url, @Body NickNameObj obj);
    }

    public interface AddFriendSync {
        @POST
        Call<AddFriendModel> getSync(@Url String url, @Body AddFriendObj obj);
    }

    public interface ReqFriendSync {
        @POST
        Call<ReqFriModel> getSync(@Url String url, @Body ReqFriendListObj obj);
    }

    public interface LogoutSync {
        @POST
        Call<LogoutModel> getSync(@Url String url);
    }

    public interface FriendListSync {
        @POST
        Call<FriendListModel> getSync(@Url String url);
    }

    public interface AcceptFriendSync {
        @POST
        Call<AcceptModel> getSync(@Url String url, @Body AcceptObj obj);
    }

    public interface  VideoUploadSync{
        @Multipart
        @POST
        Call<VideoUploadResponse> videoUpload(
                @Url String url,
                @Header("Authorization") String authToken,
                @Part MultipartBody.Part videoFilePath

        );

    }

}


