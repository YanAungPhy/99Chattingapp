package com.chatapp.nineninechatapp.Model.Register;

import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterDataModel {
    @SerializedName("userData")
    @Expose
    private Object userData;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    public Object getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
