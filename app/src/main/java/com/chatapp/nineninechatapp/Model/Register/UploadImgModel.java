package com.chatapp.nineninechatapp.Model.Register;

import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImgModel {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserObj data;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserObj getData() {
        return data;
    }

    public void setData(UserObj data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
