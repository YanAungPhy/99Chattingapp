package com.chatapp.nineninechatapp.Utils;

public class APIURL {

   // public static final String main_url="https://a772-69-160-2-204.ngrok-free.app";
    public static final String main_url="http://64.227.156.101:8080/secret-code-chat-0.0.1-SNAPSHOT";

    public static final String DomainName=main_url+"/api/";
    public static final String ImageUrl=main_url;
    public static final String login="v1/auth/authenticate";
    public static final String get_otp="v1/auth/get-0pt";
    public static final String verify_telephone="v1/auth/verify-telephone";
    public static final String register="v1/auth/register";
    public static final String uploadImageUrl="v1/user/uploadImageUrl";
    public static final String findByNickname="v1/user/findByNickname";
    public static final String add_friend="v1/user/addFriend";
    public static final String requestFriendList="v1/user/requestFriendList";
    public static final String acceptFriend="v1/user/acceptFriend";
    public static final String friendList="v1/user/acceptFriendList";
    public static final String logout="v1/user/logout";
    public static final String postVideoUploadUrl ="api/v1/user/post-video";
    public static final String postVideoUploadUrl ="v1/user/post-video";
}







