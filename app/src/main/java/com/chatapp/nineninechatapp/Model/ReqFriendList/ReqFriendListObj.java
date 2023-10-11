package com.chatapp.nineninechatapp.Model.ReqFriendList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqFriendListObj {
    @SerializedName("friendId")
    @Expose
    private String friendId;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
