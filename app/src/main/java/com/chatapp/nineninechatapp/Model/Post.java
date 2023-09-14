package com.chatapp.nineninechatapp.Model;

public class Post {
    private String name;
    private String avatar;

    public Post(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
