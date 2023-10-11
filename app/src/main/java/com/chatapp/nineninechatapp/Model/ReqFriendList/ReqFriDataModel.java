package com.chatapp.nineninechatapp.Model.ReqFriendList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqFriDataModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("requestFriendId")
    @Expose
    private String requestFriendId;
    @SerializedName("requestFriendName")
    @Expose
    private String requestFriendName;
    @SerializedName("requestFriendImagePath")
    @Expose
    private Object requestFriendImagePath;
    @SerializedName("friendId")
    @Expose
    private String friendId;
    @SerializedName("friendStatus")
    @Expose
    private String friendStatus;
    @SerializedName("actionStatus")
    @Expose
    private String actionStatus;
    @SerializedName("createAt")
    @Expose
    private Integer createAt;
    @SerializedName("createTime")
    @Expose
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestFriendId() {
        return requestFriendId;
    }

    public void setRequestFriendId(String requestFriendId) {
        this.requestFriendId = requestFriendId;
    }

    public String getRequestFriendName() {
        return requestFriendName;
    }

    public void setRequestFriendName(String requestFriendName) {
        this.requestFriendName = requestFriendName;
    }

    public Object getRequestFriendImagePath() {
        return requestFriendImagePath;
    }

    public void setRequestFriendImagePath(Object requestFriendImagePath) {
        this.requestFriendImagePath = requestFriendImagePath;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
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

    public Integer getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
