package com.liang.account.constant;


import com.liang.mvc.constants.MvcConstants;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
public class CommonConstants {

    private CommonConstants(){
        throw new IllegalStateException("Utility class");
    }

    public static final String TOKEN_NAME = MvcConstants.TOKEN;
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String CALL_BACK_URL = "callBackUrl";
}
