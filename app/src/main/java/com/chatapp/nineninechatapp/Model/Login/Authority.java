package com.chatapp.nineninechatapp.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

<<<<<<< HEAD
import java.io.Serializable;

public class Authority implements Serializable {
=======
public class Authority {
>>>>>>> YAP
    @SerializedName("authority")
    @Expose
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
