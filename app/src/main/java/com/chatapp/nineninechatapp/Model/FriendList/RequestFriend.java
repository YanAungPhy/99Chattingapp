package com.chatapp.nineninechatapp.Model.FriendList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestFriend {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("imageType")
    @Expose
    private Object imageType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Object getImageType() {
        return imageType;
    }

    public void setImageType(Object imageType) {
        this.imageType = imageType;
    }
}
