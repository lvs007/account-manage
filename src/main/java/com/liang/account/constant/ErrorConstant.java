package com.liang.account.constant;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class ErrorConstant {

    public static final int success_code = 10000;
    public static final String success_code_desc = "成功";
    public static final int not_exist_user_code = 10001;
    public static final String not_exist_user_code_desc = "不存在这个用户";
    public static final int password_error_code = 10002;
    public static final String password_error_code_desc = "密码错误";
    public static final int user_not_login = 10003;
    public static final String user_not_login_desc = "用户未登陆";
    public static final int token_expire_code = 10004;
    public static final String token_expire_code_desc = "token过期";
    public static final int repeat_login_code = 10005;
    public static final String repeat_login_code_desc = "不要重复登陆";

}
