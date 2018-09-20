package com.liang.account.controller.api;

import com.liang.account.service.AccountService;
import liang.common.exception.ParameterException;
import liang.mvc.annotation.Login;
import liang.mvc.commons.SpringContextHolder;
import liang.mvc.filter.LoginUtils;
import liang.mvc.filter.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Login
    @ResponseBody
    public Object getAccount(@RequestParam long id) {
        HttpServletRequest request = SpringContextHolder.getRequest();
        UserInfo userInfo = LoginUtils.getCurrentUser(request);
        if (userInfo.getId() != id) {
            throw ParameterException.throwException("不能获取别人的信息");
        }
        return accountService.getAccount(id);
    }

    @PostMapping
    @ResponseBody
    public Object register(@RequestParam String userName,
                           @RequestParam String password1,
                           @RequestParam String password2,
                           @RequestParam String nickName) {
        if (!StringUtils.equals(password1, password2)) {
            throw ParameterException.throwException("密码不一致");
        }
        return accountService.register(userName, password1, nickName);
    }
}
