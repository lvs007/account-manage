package com.liang.account.controller.api;

import com.liang.account.constant.CommonConstants;
import com.liang.account.manage.LoginManage;
import com.liang.mvc.annotation.Login;
import com.liang.mvc.annotation.Sign;
import com.liang.mvc.commons.ResponseData;
import com.liang.mvc.commons.SpringContextHolder;
import com.liang.mvc.filter.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginManage loginManage;

    @ResponseBody
    public Object login(@RequestParam String userName,
                        @RequestParam String password) throws IOException {
        HttpServletRequest request = SpringContextHolder.getRequest();
        HttpServletResponse response = SpringContextHolder.getResponse();
        ResponseData responseData = loginManage.login(userName, password);
        if (responseData.isSuccess()) {
            String token = (String) responseData.getData().get("token");
            request.getSession(true).setAttribute(CommonConstants.TOKEN_NAME, token);
            LoginUtils.setToken(response, token);
        }
        return responseData;
    }

    @Login
    @ResponseBody
    public Object logOut(@RequestParam(name = "nb_token") String token) {
        return loginManage.logOut(token);
    }

    @Sign(signKeyPropertyName = "account.sign.key")
    @ResponseBody
    public Object getUserInfo(@RequestParam String token) {
        return loginManage.getUserInfo(token);
    }

}
