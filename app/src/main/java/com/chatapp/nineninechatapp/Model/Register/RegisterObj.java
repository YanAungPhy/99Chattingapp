package com.chatapp.nineninechatapp.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterObj {
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("provinceId")
    @Expose
    private String provinceId;
    @SerializedName("cityId")
    @Expose
    private String cityId;
    @SerializedName("areaId")
    @Expose
    private String areaId;
    @SerializedName("location")
    @Expose
    private LocationRegister location;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public LocationRegister getLocation() {
        return location;
    }

    public void setLocation(LocationRegister location) {
        this.location = location;
    }
}
