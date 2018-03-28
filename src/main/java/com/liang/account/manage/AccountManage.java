package com.liang.account.manage;

import com.liang.account.bo.Account;
import com.liang.account.cache.AccountCache;
import com.liang.account.service.AccountService;
import com.liang.account.service.valid.ParameterValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class AccountManage {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountCache accountCache;

    @Autowired
    private ParameterValidService paramterValidService;

    public Account getAccount(String userName){
        return accountCache.get(userName);
    }

    public Account register(String userName, String password, String nickName){
        paramterValidService.validRegisterParam(userName, password, nickName);
        return accountService.register(userName, password, nickName);
    }

}
