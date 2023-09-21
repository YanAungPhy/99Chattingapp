package com.chatapp.nineninechatapp.Model.AcceptFriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptObj {
    @SerializedName("requestFriendId")
    @Expose
    private String requestFriendId;

    public String getRequestFriendId() {
        return requestFriendId;
    }

    public void setRequestFriendId(String requestFriendId) {
        this.requestFriendId = requestFriendId;
    }
}
