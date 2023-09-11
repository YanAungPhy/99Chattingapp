package com.chatapp.nineninechatapp.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OTP_Obj implements Serializable {
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("telephone")
    @Expose
    private String telephone;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
