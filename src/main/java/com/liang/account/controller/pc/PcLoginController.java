package com.liang.account.controller.pc;

import static com.liang.account.constant.CommonConstants.CALL_BACK_URL;

import com.liang.account.constant.CommonConstants;
import com.liang.account.manage.LoginManage;
import com.liang.common.util.Encodes;
import com.liang.mvc.annotation.Login;
import com.liang.mvc.commons.ResponseData;
import com.liang.mvc.commons.SpringContextHolder;
import com.liang.mvc.filter.LoginUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
@Controller
public class PcLoginController {

  private static final String LOGIN_DEFAULT_CALLBACK_URL = "/v1/pc-login/index?success=";
  private static final String LOGIN__URL = "/v1/pc-login/login-page?success=";

  @Autowired
  private LoginManage loginManage;

  public String login(@RequestParam String userName, @RequestParam String password) {
    HttpServletRequest request = SpringContextHolder.getRequest();
    HttpServletResponse response = SpringContextHolder.getResponse();
    HttpSession httpSession = SpringContextHolder.getHttpSession();
    String callBackUrl = request.getParameter(CALL_BACK_URL);
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
      callBackUrl = LOGIN__URL + CommonConstants.FALSE + "&reason=" + Encodes.urlEncode(responseData
          .getMessage()) + "&" + CALL_BACK_URL + "=" + callBackUrl;
    }
    return "redirect:" + callBackUrl;
  }

  @Login
  @ResponseBody
  public Object logOut(@RequestParam String token) {
    return loginManage.logOut(token);
  }

  public String loginPage(ModelMap modelMap) {
    HttpServletRequest request = SpringContextHolder.getRequest();
    String callBackUrl = request.getParameter(CALL_BACK_URL);
    if (StringUtils.isNotBlank(callBackUrl)) {
      modelMap.put(CALL_BACK_URL, callBackUrl);
    }
    String reason = request.getParameter("reason");
    if (StringUtils.isNotBlank(reason)) {
      modelMap.put("reason", reason);
    }
    return "login";
  }

  public ModelAndView index() {
    HttpServletRequest request = SpringContextHolder.getRequest();
    String success = request.getParameter("success");
    String reason = request.getParameter("reason");
    ResponseData responseData = new ResponseData();
    if (StringUtils.equalsIgnoreCase(success, "true")) {
      responseData.setMessage("登陆成功");
    } else {
      responseData.setMessage("登陆失败," + reason);
    }
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("response", responseData);
    return modelAndView;
  }
}
