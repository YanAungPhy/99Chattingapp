package com.chatapp.nineninechatapp.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("con")
    public boolean con;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public LoginDataModel data;
    @SerializedName("token")
    public String token;

    public LoginModel(boolean con, String msg, LoginDataModel data, String token) {
        this.con = con;
        this.msg = msg;
        this.data = data;
        this.token = token;
    }

    public boolean isCon() {
        return con;
    }

    public void setCon(boolean con) {
        this.con = con;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginDataModel getData() {
        return data;
    }

    public void setData(LoginDataModel data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
