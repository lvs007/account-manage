package com.liang.account.bo;

public class Device {

  private long id;
  private long userId;
  private String intranetIp;
  private String extranetIp;
  private int intranetPort;
  private int extranetPort;
  private boolean inUse;
  private String osType;
  private String model;
  private String uuid;
  private int createTime;
  private int updateTime;

  public long getId() {
    return id;
  }

  public Device setId(long id) {
    this.id = id;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public Device setUserId(long userId) {
    this.userId = userId;
    return this;
  }

  public String getIntranetIp() {
    return intranetIp;
  }

  public Device setIntranetIp(String intranetIp) {
    this.intranetIp = intranetIp;
    return this;
  }

  public String getExtranetIp() {
    return extranetIp;
  }

  public Device setExtranetIp(String extranetIp) {
    this.extranetIp = extranetIp;
    return this;
  }

  public int getIntranetPort() {
    return intranetPort;
  }

  public Device setIntranetPort(int intranetPort) {
    this.intranetPort = intranetPort;
    return this;
  }

  public int getExtranetPort() {
    return extranetPort;
  }

  public Device setExtranetPort(int extranetPort) {
    this.extranetPort = extranetPort;
    return this;
  }

  public boolean isInUse() {
    return inUse;
  }

  public Device setInUse(boolean inUse) {
    this.inUse = inUse;
    return this;
  }

  public String getOsType() {
    return osType;
  }

  public Device setOsType(String osType) {
    this.osType = osType;
    return this;
  }

  public String getModel() {
    return model;
  }

  public Device setModel(String model) {
    this.model = model;
    return this;
  }

  public String getUuid() {
    return uuid;
  }

  public Device setUuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  public int getCreateTime() {
    return createTime;
  }

  public Device setCreateTime(int createTime) {
    this.createTime = createTime;
    return this;
  }

  public int getUpdateTime() {
    return updateTime;
  }

  public Device setUpdateTime(int updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}
