package com.liang.account.controller.pc;

import com.liang.account.constant.CommonConstants;
import com.liang.account.manage.LoginManage;
import liang.mvc.annotation.Login;
import liang.mvc.commons.ResponseData;
import liang.mvc.commons.SpringContextHolder;
import liang.mvc.filter.LoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
@Controller
public class PcLoginController {

    private static final String LOGIN_DEFAULT_CALLBACK_URL = "/v1/pc-login/index?success=";

    @Autowired
    private LoginManage loginManage;

    public String login(@RequestParam String userName,
                        @RequestParam String password) throws IOException {
        HttpServletRequest request = SpringContextHolder.getRequest();
        HttpServletResponse response = SpringContextHolder.getResponse();
        HttpSession httpSession = SpringContextHolder.getHttpSession();
        String callBackUrl = request.getParameter("callBackUrl");
        ResponseData responseData = loginManage.login(userName, password);
        if (responseData.isSuccess()) {
            String token = (String) responseData.getData().get("token");
            request.getSession(true).setAttribute(CommonConstants.TOKEN_NAME, token);
            httpSession.setAttribute(CommonConstants.TOKEN_NAME, token);
            LoginUtils.setToken(response, token);
            if (StringUtils.isNotBlank(callBackUrl)) {
                if (StringUtils.contains(callBackUrl, "?")) {
                    callBackUrl += "&" + CommonConstants.TOKEN_NAME + "=" + token;
                } else {
                    callBackUrl += "?" + CommonConstants.TOKEN_NAME + "=" + token;
                }
            } else {
                callBackUrl = LOGIN_DEFAULT_CALLBACK_URL + CommonConstants.TRUE;
            }
        } else {
            callBackUrl = LOGIN_DEFAULT_CALLBACK_URL + CommonConstants.FALSE;
        }
        response.sendRedirect(callBackUrl);
        return "/redirect";
    }

    @Login
    @ResponseBody
    public Object logOut(@RequestParam String token) {
        return loginManage.logOut(token);
    }

    public String loginPage(ModelMap modelMap) {
        HttpServletRequest request = SpringContextHolder.getRequest();
        String callBackUrl = request.getParameter("callBackUrl");
        if (StringUtils.isNotBlank(callBackUrl)) {
            modelMap.put("callBackUrl", callBackUrl);
        }
        return "login";
    }

    public ModelAndView index() {
        HttpServletRequest request = SpringContextHolder.getRequest();
        String success = request.getParameter("success");
        ResponseData responseData = new ResponseData();
        if (StringUtils.equalsIgnoreCase(success, "true")) {
            responseData.setMessage("登陆成功");
        } else {
            responseData.setMessage("登陆失败");
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("response", responseData);
        return modelAndView;
    }
}
