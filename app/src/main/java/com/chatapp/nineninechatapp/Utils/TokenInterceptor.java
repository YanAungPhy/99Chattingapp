package com.chatapp.nineninechatapp.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request newRequest = chain.request().newBuilder()
                .header("Authorization", "Bearer" + String.valueOf(AppStorePreferences.getString(AppApplication.getAppContext(),AppENUM.TOKEN)))
                .build();

       /* Response response =  chain.proceed(newRequest);
        Log.d("MyApp", "Code : "+response.code());
        if (response.code() == 401){

            AppApplication.getAppContext().startActivity(new Intent(AppApplication.getAppContext(),SignInActivity.class));


        }*/

        return chain.proceed(newRequest);
    }


}
