package com.liang.account.service;

import com.liang.account.bo.UserToken;
import com.liang.account.common.UserUtils;
import com.liang.account.dao.UserTokenDao;
import liang.dao.jdbc.common.SearchFilter;
import liang.dao.jdbc.common.SqlPath;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private ConfigService configService;

    public UserToken getUserToken(String userName) {
        return userTokenDao.findOne(SqlPath.where("userName", SearchFilter.Operator.EQ, userName));
    }

    public UserToken getUserTokenByToken(String token) {
        return userTokenDao.findOne(SqlPath.where("token", SearchFilter.Operator.EQ, token));
    }

    @Transactional
    public UserToken insertUserToken(String userName) {
        UserToken userToken = new UserToken();
        userToken.setUserName(userName);
        userToken.setCreateTime(System.currentTimeMillis());
        userToken.setUpdateTime(System.currentTimeMillis());
        userToken.setToken(UserUtils.generateToken());
        userToken.setEffectiveTime(configService.getTokenEffectiveTime());
        userToken.setEndTime(UserUtils.getEndTime(userToken.getEffectiveTime()));
        if (userTokenDao.insert(userToken)) {
            return userToken;
        }
        return null;
    }

    @Transactional
    public boolean delete(UserToken userToken) {
        return userTokenDao.delete(userToken);
    }

    @Transactional
    public boolean updateUserToken(UserToken userToken) {
        if (userToken == null) {
            return false;
        }
        userToken.setEffectiveTime(configService.getTokenEffectiveTime());
        userToken.setEndTime(UserUtils.getEndTime(userToken.getEffectiveTime()));
        userToken.setToken(UserUtils.generateToken());
        userToken.setUpdateTime(System.currentTimeMillis());
        return userTokenDao.update(userToken);
    }

    @Transactional
    public boolean expireUserToken(UserToken userToken) {
        if (userToken == null) {
            return false;
        }
        userToken.setEndTime(System.currentTimeMillis());
        userToken.setUpdateTime(System.currentTimeMillis());
        return userTokenDao.update(userToken);
    }
}
