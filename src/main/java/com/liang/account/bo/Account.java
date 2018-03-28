package com.liang.account.bo;

import java.util.Date;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
public class Account {
    private long id;
    private String userName;
    private String password;
    private String nickName;
    private int status;
    private Date createTime;
    private Date updateTime;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserType() {
        return userType;
    }

    public Account setUserType(int userType) {
        this.userType = userType;
        return this;
    }
}
