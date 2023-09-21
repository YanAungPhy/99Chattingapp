package com.chatapp.nineninechatapp.Model.FindNickName.RequestFriend;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestFriendObj {
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
