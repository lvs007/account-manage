package com.liang.account.controller.pc;

import com.liang.account.bo.Account;
import com.liang.account.service.AccountService;
import java.io.IOException;
import liang.common.exception.ParameterException;
import liang.mvc.commons.ResponseData;
import liang.mvc.commons.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Controller
public class PcAccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping(method = RequestMethod.POST)
  public String register(@RequestParam String userName,
      @RequestParam String password1,
      @RequestParam String password2,
      @RequestParam String nickName, ModelMap modelMap) throws IOException {
    if (!StringUtils.equals(password1, password2)) {
      throw ParameterException.throwException("密码不一致");
    }
    Account account = accountService.register(userName, password1, nickName);
    ResponseData responseData = new ResponseData();
    if (account == null) {
      responseData.setMessage("注册失败,用户名已经存在");
    } else {
      String callBackUrl = SpringContextHolder.getRequest().getParameter("callBackUrl");
      return "redirect:/v1/pc-login/login?userName=" + userName + "&password=" + password1
          + "&callBackUrl=" + callBackUrl;
    }
    modelMap.put("response", responseData);
    return "index";
  }

  public String registerPage(ModelMap modelMap) {
    modelMap.put("callBackUrl", SpringContextHolder.getRequest().getParameter("callBackUrl"));
    return "register";
  }
}
