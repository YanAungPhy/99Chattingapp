package com.chatapp.nineninechatapp.Utils;

import android.content.Context;


import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptModel;
import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptObj;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendModel;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendObj;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameModel;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameObj;
import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Model.Register.OTP_Model;
import com.chatapp.nineninechatapp.Model.Register.OTP_Obj;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriModel;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriendListObj;

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

    public Call<NickNameModel> FindNickName (String url, NickNameObj obj) {
        NetworkSync.FindNickNameSync sync=retrofit.create(NetworkSync.FindNickNameSync.class);
        return sync.getSync(url,obj);
    }

    public Call<AddFriendModel> AddFriend (String url, AddFriendObj obj) {
        NetworkSync.AddFriendSync sync=retrofit.create(NetworkSync.AddFriendSync.class);
        return sync.getSync(url,obj);
    }

    public Call<ReqFriModel> ReqFriend (String url, ReqFriendListObj obj) {
        NetworkSync.ReqFriendSync sync=retrofit.create(NetworkSync.ReqFriendSync.class);
        return sync.getSync(url,obj);
    }

    public Call<AcceptModel> AcceptFriend (String url, AcceptObj obj) {
        NetworkSync.AcceptFriendSync sync=retrofit.create(NetworkSync.AcceptFriendSync.class);
        return sync.getSync(url,obj);
    }

}


