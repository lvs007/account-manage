package com.liang.account.controller.pc;

import com.liang.account.bo.Account;
import com.liang.account.service.AccountService;
import liang.common.exception.ParameterException;
import liang.mvc.annotation.Login;
import liang.mvc.commons.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                           @RequestParam String nickName, ModelMap modelMap) {
        if (!StringUtils.equals(password1, password2)) {
            throw ParameterException.throwException("密码不一致");
        }
        Account account = accountService.register(userName, password1, nickName);
        ResponseData responseData = new ResponseData();
        if (account == null) {
            responseData.setMessage("注册失败");
        } else {
            responseData.setMessage("注册成功");
        }
        modelMap.put("response", responseData);
        return "index";
    }

    public String registerPage() {
        return "register";
    }
}