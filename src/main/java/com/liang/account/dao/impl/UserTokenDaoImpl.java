package com.liang.account.dao.impl;

import com.liang.account.bo.UserToken;
import com.liang.account.dao.UserTokenDao;
import com.liang.dao.jdbc.impl.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Repository
public class UserTokenDaoImpl extends AbstractDao<UserToken> implements UserTokenDao{
}
