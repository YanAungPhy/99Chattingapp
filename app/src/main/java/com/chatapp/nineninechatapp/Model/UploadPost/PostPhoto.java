package com.chatapp.nineninechatapp.Model.UploadPost;

import java.io.Serializable;

public class PostPhoto implements Serializable {
    private String imageFilePath;
    private boolean isSelected;

    public PostPhoto() {
    }

    public PostPhoto(String imageFilePath, boolean isSelected) {
        this.imageFilePath = imageFilePath;
        this.isSelected = isSelected;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
