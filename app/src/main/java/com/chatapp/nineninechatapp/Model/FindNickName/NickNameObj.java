package com.chatapp.nineninechatapp.Model.FindNickName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NickNameObj {
    @SerializedName("keyword")
    @Expose
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
