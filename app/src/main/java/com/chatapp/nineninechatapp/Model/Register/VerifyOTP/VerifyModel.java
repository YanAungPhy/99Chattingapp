package com.chatapp.nineninechatapp.Model.Register.VerifyOTP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyModel {
    @SerializedName("con")
    @Expose
    private Boolean con;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private VerifyDataModel data;

    public Boolean getCon() {
        return con;
    }

    public void setCon(Boolean con) {
        this.con = con;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VerifyDataModel getData() {
        return data;
    }

    public void setData(VerifyDataModel data) {
        this.data = data;
    }
}
