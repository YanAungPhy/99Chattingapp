package com.chatapp.nineninechatapp.Utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by DELL on 3/21/2018.
 */

public class RetrofitFactory {

    private static Retrofit retrofit;

    public static Retrofit connector(){
        if(retrofit==null) {

            TokenInterceptor interceptor=new TokenInterceptor();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.client(client);
            builder.baseUrl("https://9f4f-69-160-8-39.ngrok-free.app/");
            builder.addConverterFactory(GsonConverterFactory.create());

            //  builder.addConverterFactory(JacksonConverterFactory.create());

            retrofit = builder.build();
        }
        return retrofit;

    }

}



