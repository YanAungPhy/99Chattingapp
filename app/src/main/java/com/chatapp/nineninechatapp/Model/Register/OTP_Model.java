package com.chatapp.nineninechatapp.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTP_Model {
    @SerializedName("con")
    @Expose
    private Boolean con;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("dataSMS")
    @Expose
    private Integer dataSMS;

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

    public Integer getDataSMS() {
        return dataSMS;
    }

    public void setDataSMS(Integer dataSMS) {
        this.dataSMS = dataSMS;
    }

}
