package com.liang.account.cache.impl;

import com.liang.account.bo.Account;
import com.liang.account.cache.AbstractLocalCache;
import com.liang.account.cache.AccountCache;
import com.liang.account.service.AccountService;
import com.liang.account.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class AccountCacheLocalImpl extends AbstractLocalCache<String, Account> implements AccountCache{

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConfigService configService;

    protected Account loadData(String key) {
        return accountService.getAccount(key);
    }
}
