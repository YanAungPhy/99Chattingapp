package com.chatapp.nineninechatapp.Model.FriendList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendListDataModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("requestFriend")
    @Expose
    private RequestFriend requestFriend;
    @SerializedName("acceptFriend")
    @Expose
    private Object acceptFriend;
    @SerializedName("friendStatus")
    @Expose
    private String friendStatus;
    @SerializedName("actionStatus")
    @Expose
    private String actionStatus;
    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("acceptAt")
    @Expose
    private Integer acceptAt;
    @SerializedName("acceptTime")
    @Expose
    private String acceptTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestFriend getRequestFriend() {
        return requestFriend;
    }

    public void setRequestFriend(RequestFriend requestFriend) {
        this.requestFriend = requestFriend;
    }

    public Object getAcceptFriend() {
        return acceptFriend;
    }

    public void setAcceptFriend(Object acceptFriend) {
        this.acceptFriend = acceptFriend;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getAcceptAt() {
        return acceptAt;
    }

    public void setAcceptAt(Integer acceptAt) {
        this.acceptAt = acceptAt;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
}
