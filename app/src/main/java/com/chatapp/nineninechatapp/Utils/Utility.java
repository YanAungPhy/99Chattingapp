package com.chatapp.nineninechatapp.Utils;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.google.gson.Gson;

import java.util.Locale;

public class Utility {

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean hostReachable = false;

        if (cm != null && cm.getActiveNetworkInfo() != null)
            hostReachable = cm.getActiveNetworkInfo().isConnectedOrConnecting();
        return hostReachable;
    }

    public static void call_phone(Context context,String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public static void showToast (Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void darkMode(Context context){
        if (AppStorePreferences.getBoolean(context,"dark_mode")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static void delete_UserProfile(Context context){
        SharedPreferences pref=context.getSharedPreferences(Constant.SharePref, Context.MODE_PRIVATE);
        pref.edit().remove("userobj").commit();

    }

    public static void Save_UserProfile(Context context, UserObj userObj){
        SharedPreferences pref=context.getSharedPreferences(Constant.SharePref, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonobject = gson.toJson(userObj);
        pref.edit().putString("userobj",jsonobject).apply();

    }

    public static UserObj query_UserProfile(Context context){
        UserObj userObj = new UserObj();
        SharedPreferences pref=context.getSharedPreferences(Constant.SharePref, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("userobj", "");
        userObj = gson.fromJson(json, UserObj.class);
        return userObj;

    }


    public static TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
    public static void setLocale(String langCode,Activity activity) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Save the selected language in SharedPreferences
        AppStorePreferences.putString(activity, "selected_language", langCode);
    }
    public static void loadLocale(Activity activity){
        String langCode = AppStorePreferences.getString(activity, "selected_language", "");
        if (!langCode.isEmpty()){
            setLocale(langCode,activity);
        }
    }


}

