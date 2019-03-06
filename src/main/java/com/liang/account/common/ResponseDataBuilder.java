package com.liang.account.common;

import com.liang.account.constant.ErrorConstant;
import com.liang.mvc.commons.ResponseData;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class ResponseDataBuilder {

    private ResponseDataBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseData notExistUser() {
        return buildErrorResponseData(ErrorConstant.NOT_EXIST_USER_CODE, ErrorConstant.NOT_EXIST_USER_CODE_DESC);
    }

    public static ResponseData passwordError() {
        return buildErrorResponseData(ErrorConstant.PASSWORD_ERROR_CODE, ErrorConstant.PASSWORD_ERROR_CODE_DESC);
    }

    public static ResponseData userNotLogin() {
        return buildErrorResponseData(ErrorConstant.USER_NOT_LOGIN, ErrorConstant.USER_NOT_LOGIN_DESC);
    }

    public static ResponseData tokenExpire() {
        return buildErrorResponseData(ErrorConstant.TOKEN_EXPIRE_CODE, ErrorConstant.TOKEN_EXPIRE_CODE_DESC);
    }

    public static ResponseData repeatLogin() {
        return buildErrorResponseData(ErrorConstant.REPEAT_LOGIN_CODE, ErrorConstant.REPEAT_LOGIN_CODE_DESC);
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
        ResponseData.setErrorId(ErrorConstant.SUCCESS_CODE);
        ResponseData.setMessage(ErrorConstant.SUCCESS_CODE_DESC);
        ResponseData.setSuccess(true);
        ResponseData.addData(result);
        return ResponseData;
    }
}
