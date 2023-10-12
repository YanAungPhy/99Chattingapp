package com.chatapp.nineninechatapp.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {
    @SerializedName("con")
    @Expose
    private Boolean con;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private RegisterDataModel data;

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

    public RegisterDataModel getData() {
        return data;
    }

    public void setData(RegisterDataModel data) {
        this.data = data;
    }
}
