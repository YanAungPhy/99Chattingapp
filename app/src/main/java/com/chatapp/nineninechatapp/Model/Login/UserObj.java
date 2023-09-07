package com.chatapp.nineninechatapp.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserObj implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
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
    private String cityId;
    @SerializedName("areaId")
    @Expose
    private Integer areaId;
    @SerializedName("userType")
    @Expose
    private Integer userType;
    @SerializedName("balance")
    @Expose
    private Object balance;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("imagePath")
    @Expose
    private Object imagePath;
    @SerializedName("imageType")
    @Expose
    private Object imageType;
    @SerializedName("registerDateAt")
    @Expose
    private Integer registerDateAt;
    @SerializedName("inviteCode")
    @Expose
    private Object inviteCode;
    @SerializedName("myInviteCode")
    @Expose
    private String myInviteCode;
    @SerializedName("optCode")
    @Expose
    private String optCode;
    @SerializedName("wallet")
    @Expose
    private Double wallet;
    @SerializedName("starPoint")
    @Expose
    private Integer starPoint;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("register")
    @Expose
    private Boolean register;
    @SerializedName("authorities")
    @Expose
    private List<Authority> authorities;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("accountNonLocked")
    @Expose
    private Boolean accountNonLocked;
    @SerializedName("credentialsNonExpired")
    @Expose
    private Boolean credentialsNonExpired;
    @SerializedName("accountNonExpired")
    @Expose
    private Boolean accountNonExpired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public Object getImageType() {
        return imageType;
    }

    public void setImageType(Object imageType) {
        this.imageType = imageType;
    }

    public Integer getRegisterDateAt() {
        return registerDateAt;
    }

    public void setRegisterDateAt(Integer registerDateAt) {
        this.registerDateAt = registerDateAt;
    }

    public Object getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(Object inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMyInviteCode() {
        return myInviteCode;
    }

    public void setMyInviteCode(String myInviteCode) {
        this.myInviteCode = myInviteCode;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Integer getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(Integer starPoint) {
        this.starPoint = starPoint;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
}
