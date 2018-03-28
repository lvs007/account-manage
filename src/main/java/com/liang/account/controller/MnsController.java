package com.liang.account.controller;

import com.liang.account.bo.Account;
import com.liang.account.service.AccountService;
import liang.cache.impl.RedisCache;
import liang.common.exception.ParameterException;
import liang.mq.consumer.MessageService;
import liang.mvc.commons.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by liangzhiyan on 2017/4/28.
 */
@Controller
public class MnsController {

    @Resource(name = "messageService")
    private MessageService messageService;

//    @Resource(name = "broadcastMessageService")
//    private MessageService broadcastMessageService;

    @Resource(name = "redisCache")
    private RedisCache redisCache;

    @Autowired
    private AccountService accountService;

    @ResponseBody
    public Object sendMsg() {
        HttpServletRequest request = SpringContextHolder.getRequest();
        String msg = request.getParameter("msg");
        if (StringUtils.isBlank(msg)) {
            return "false";
        }
        messageService.sendMsg(msg);
        return "true";
    }

//    @ResponseBody
//    public Object sendBsMsg(@RequestParam String msg) {
//        broadcastMessageService.sendMsg(msg);
//        return "true";
//    }

    @ResponseBody
    public Object testRedis() {
        return redisCache.setNx("test", "nihao", 10000);
    }

    @ResponseBody
    public Object testRedisGet() {
        return redisCache.get("test");
    }

    @ResponseBody
    public Object testNumber() {
        return 100;
    }

    @ResponseBody
    public Object testThrowEx() {
        throw new RuntimeException();
    }

    @ResponseBody
    public Object testSelect() {
        return accountService.listAll();
    }

    @ResponseBody
    public Object testArray() {
        Object result = accountService.listAllArray();
        return result;
    }

    @ResponseBody
    public Object testUpdate(@RequestParam String nickName, @RequestParam long id) {
        Account account = accountService.getAccount(id);
        if (account == null) {
            throw ParameterException.throwException("不存在这个用户，id=" + id);
        }
        account.setNickName(nickName);
        account.setUpdateTime(Calendar.getInstance().getTime());
        return accountService.update(account);
    }
}
