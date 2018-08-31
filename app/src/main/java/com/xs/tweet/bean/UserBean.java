package com.xs.tweet.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 用户信息
 * 作者：xq on 2018/8/29 17:09
 * 邮箱：08xq@163.com
 */
public class UserBean {

    @SerializedName("profile-image")
    private String profileImage;
    private String avatar;
    private String nick;
    @SerializedName("username")
    private String userName;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
