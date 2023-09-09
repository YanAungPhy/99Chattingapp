package com.chatapp.nineninechatapp.Model.Register.VerifyOTP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyObj {
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("optCode")
    @Expose
    private String optCode;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }
}
