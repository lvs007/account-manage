package com.liang.account.cache.impl;

import com.liang.account.bo.UserToken;
import com.liang.account.cache.AbstractLocalCache;
import com.liang.account.cache.UserTokenCache;
import com.liang.account.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class UserTokenCacheLocalImpl extends AbstractLocalCache<String, UserToken> implements UserTokenCache {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public int getInitialCapacity() {
        return 1000000;
    }

    @Override
    public long getMaximumSize() {
        return 5000000;
    }

    @Override
    public long getDuration() {
        return 30 * 24 * 60 * 60;
    }

    protected UserToken loadData(String key) {
        UserToken userToken = userTokenService.getUserTokenByToken(key);
        if (userToken == null) {
            userToken = userTokenService.getUserToken(key);
        }
        return userToken;
    }
}
