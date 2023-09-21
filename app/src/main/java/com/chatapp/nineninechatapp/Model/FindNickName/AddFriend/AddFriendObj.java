package com.chatapp.nineninechatapp.Model.FindNickName.AddFriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFriendObj {
    @SerializedName("requestFriendId")
    @Expose
    private String requestFriendId;
    @SerializedName("friendId")
    @Expose
    private String friendId;

    public String getRequestFriendId() {
        return requestFriendId;
    }

    public void setRequestFriendId(String requestFriendId) {
        this.requestFriendId = requestFriendId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
