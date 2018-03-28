package com.liang.account.common;

import com.liang.account.constant.ErrorConstant;
import liang.mvc.commons.ResponseData;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class ResponseDataBuilder {

    public static ResponseData notExistUser() {
        return buildErrorResponseData(ErrorConstant.not_exist_user_code, ErrorConstant.not_exist_user_code_desc);
    }

    public static ResponseData passwordError() {
        return buildErrorResponseData(ErrorConstant.password_error_code, ErrorConstant.password_error_code_desc);
    }

    public static ResponseData userNotLogin() {
        return buildErrorResponseData(ErrorConstant.user_not_login, ErrorConstant.user_not_login_desc);
    }

    public static ResponseData tokenExpire() {
        return buildErrorResponseData(ErrorConstant.token_expire_code, ErrorConstant.token_expire_code_desc);
    }

    public static ResponseData repeatLogin() {
        return buildErrorResponseData(ErrorConstant.repeat_login_code, ErrorConstant.repeat_login_code_desc);
    }

    public static ResponseData buildErrorResponseData(int errorCode, String message) {
        ResponseData ResponseData = new ResponseData();
        ResponseData.setErrorId(errorCode);
        ResponseData.setMessage(message);
        ResponseData.setSuccess(false);
        return ResponseData;
    }

    public static ResponseData buildSuccessResponseData(Object result) {
        ResponseData ResponseData = new ResponseData();
        ResponseData.setErrorId(ErrorConstant.success_code);
        ResponseData.setMessage(ErrorConstant.success_code_desc);
        ResponseData.setSuccess(true);
        ResponseData.addData(result);
        return ResponseData;
    }
}
