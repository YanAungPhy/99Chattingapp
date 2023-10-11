package com.chatapp.nineninechatapp.Model.FindNickName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotiResult {
    @SerializedName("message_id")
    @Expose
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
