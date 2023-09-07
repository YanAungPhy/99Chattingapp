package com.chatapp.nineninechatapp.Utils.NetworkSync;

import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LoginSync {
    @POST
    Call<LoginModel> getSync(@Url String url, @Body LoginObj obj);
}
