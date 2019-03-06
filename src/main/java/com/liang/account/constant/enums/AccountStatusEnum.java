package com.liang.account.constant.enums;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
public enum AccountStatusEnum {
    ABLE(0, "有效"), DISABLE(1, "无效");

    AccountStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int status;
    public String desc;
}
