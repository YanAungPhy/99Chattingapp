package com.chatapp.nineninechatapp.Utils;

import android.content.Context;


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


}


