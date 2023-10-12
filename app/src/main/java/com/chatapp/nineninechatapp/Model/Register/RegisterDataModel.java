package com.chatapp.nineninechatapp.Model.Register;

import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterDataModel {
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("provinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("since")
    @Expose
    private String since;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
