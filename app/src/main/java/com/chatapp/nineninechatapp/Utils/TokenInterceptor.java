package com.chatapp.nineninechatapp.Utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request newRequest = chain.request().newBuilder()
                .header("Authorization", "Bearer "+AppStorePreferences.getString(AppApplication.getAppContext(),AppENUM.TOKEN))
                .build();

      //  Log.e("mtt_retrofit_token>>>",AppStorePreferences.getString(AppApplication.context,AppENUM.TOKEN));

        return chain.proceed(newRequest);
    }

}
