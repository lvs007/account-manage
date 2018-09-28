package com.liang.account.service;

import com.liang.account.bo.Account;
import com.liang.account.cache.AccountCache;
import com.liang.account.constant.enums.AccountStatusEnum;
import com.liang.account.dao.AccountDao;
import javax.annotation.Resource;
import liang.dao.jdbc.common.SearchFilter;
import liang.dao.jdbc.common.SqlPath;
import liang.mvc.annotation.cache.LocalCacheAnnotation;
import liang.mvc.annotation.cache.RedisCacheAnnotation;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ConfigService configService;

    @Autowired
    private AccountCache accountCache;

    @RedisCacheAnnotation
    public Account getAccount(long id) {
        return accountDao.findById(id);
    }

    public Account getAccount(String userName) {
        return accountDao.findOne(SqlPath.where("userName", SearchFilter.Operator.EQ, userName));
    }

    @LocalCacheAnnotation
    public List<Account> listAll() {
        return accountDao.findAll(null);
    }

    @LocalCacheAnnotation
    public Account[] listAllArray() {
        List<Account> accountList = accountDao.findAll(null);
        return accountList.toArray(new Account[1]);
    }

    @Transactional
    public boolean update(Account account){
        return accountDao.update(account);
    }

    @Transactional
    public Account register(String userName, String password, String nickName) {
        if (accountCache.get(userName) != null) {
          return null;
        }
        Account account = buildInsertAccount(userName, password, nickName);
        accountDao.insert(account);
        return account;
    }

    private Account buildInsertAccount(String userName, String password, String nickName) {
        Account account = new Account();
        account.setStatus(AccountStatusEnum.ABLE.status);
        account.setCreateTime(Calendar.getInstance().getTime());
        account.setUpdateTime(Calendar.getInstance().getTime());
        account.setUserName(userName);
        account.setPassword(Md5Crypt.apr1Crypt(password, configService.getMd5Salt()));
        account.setNickName(nickName);
        return account;
    }
}
