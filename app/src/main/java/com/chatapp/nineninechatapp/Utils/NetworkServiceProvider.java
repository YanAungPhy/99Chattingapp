package com.chatapp.nineninechatapp.Utils;

import android.content.Context;


import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Login.LoginObj;
import com.chatapp.nineninechatapp.Utils.NetworkSync.LoginSync;

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
        LoginSync sync=retrofit.create(LoginSync.class);
        return sync.getSync(url,obj);
    }


}


