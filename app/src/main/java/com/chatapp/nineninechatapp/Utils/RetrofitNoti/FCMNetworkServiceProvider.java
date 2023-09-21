package com.chatapp.nineninechatapp.Utils.RetrofitNoti;

import android.content.Context;

import com.chatapp.nineninechatapp.Model.FindNickName.Noti_Obj;
import com.chatapp.nineninechatapp.Model.FindNickName.ResponNoti;
import com.chatapp.nineninechatapp.Utils.NetworkSync;

import retrofit2.Call;
import retrofit2.Retrofit;

public class FCMNetworkServiceProvider {
    Context context;
    Retrofit retrofit;

    public FCMNetworkServiceProvider(Context context) {
        this.context = context;
        RetrofitFCM factory=new RetrofitFCM();
        retrofit=factory.connector();
    }

    public Call<ResponNoti> Notification (String url, Noti_Obj notiObj) {
        NetworkSync.NotiSync sync=retrofit.create(NetworkSync.NotiSync.class);
        return sync.get_notification(url,notiObj);
    }


}


