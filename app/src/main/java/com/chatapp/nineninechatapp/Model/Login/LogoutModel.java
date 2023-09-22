package com.chatapp.nineninechatapp.Model.Login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutModel {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
