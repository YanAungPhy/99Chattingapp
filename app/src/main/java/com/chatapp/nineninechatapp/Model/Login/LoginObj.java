package com.chatapp.nineninechatapp.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginObj {

    private String phone;
    private String password;
    private String firebaseToken;

    public LoginObj(String phone, String password, String firebaseToken) {
        this.phone = phone;
        this.password = password;
        this.firebaseToken = firebaseToken;
    }

    public LoginObj() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
