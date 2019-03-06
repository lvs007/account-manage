package com.liang.account.constant;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class ErrorConstant {

    private ErrorConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final int SUCCESS_CODE = 10000;
    public static final String SUCCESS_CODE_DESC = "成功";
    public static final int NOT_EXIST_USER_CODE = 10001;
    public static final String NOT_EXIST_USER_CODE_DESC = "不存在这个用户";
    public static final int PASSWORD_ERROR_CODE = 10002;
    public static final String PASSWORD_ERROR_CODE_DESC = "密码错误";
    public static final int USER_NOT_LOGIN = 10003;
    public static final String USER_NOT_LOGIN_DESC = "用户未登陆";
    public static final int TOKEN_EXPIRE_CODE = 10004;
    public static final String TOKEN_EXPIRE_CODE_DESC = "token过期";
    public static final int REPEAT_LOGIN_CODE = 10005;
    public static final String REPEAT_LOGIN_CODE_DESC = "不要重复登陆";

}
