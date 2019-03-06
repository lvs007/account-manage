package com.liang.account.dao.impl;

import com.liang.account.bo.Account;
import com.liang.account.dao.AccountDao;
import com.liang.dao.jdbc.impl.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {
}
