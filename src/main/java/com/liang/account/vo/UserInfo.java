package com.liang.account.vo;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class UserInfo {
    private long id;
    private String userName;
    private String nickName;
    private String token;
    private int userType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserType() {
        return userType;
    }

    public UserInfo setUserType(int userType) {
        this.userType = userType;
        return this;
    }
}
