package com.liang.account.manage;

import com.liang.account.bo.Account;
import com.liang.account.bo.UserToken;
import com.liang.account.cache.AccountCache;
import com.liang.account.cache.UserTokenCache;
import com.liang.account.cache.lock.LoginLock;
import com.liang.account.common.ResponseDataBuilder;
import com.liang.account.common.UserUtils;
import com.liang.account.service.ConfigService;
import com.liang.account.service.UserTokenService;
import com.liang.account.vo.UserInfo;
import liang.mvc.commons.ResponseData;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class LoginManage {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserTokenCache userTokenCache;

    @Autowired
    private AccountCache accountCache;

    @Autowired
    private ConfigService configService;

    public ResponseData getUserInfo(String token) {
        UserToken userToken = userTokenCache.get(token);
        if (userToken == null) {
            return ResponseDataBuilder.userNotLogin();
        }
        if (UserUtils.isExpire(userToken)) {
            return ResponseDataBuilder.tokenExpire();
        }
        Account account = accountCache.get(userToken.getUserName());
        return ResponseDataBuilder.buildSuccessResponseData(buildUserInfo(account, userToken));
    }

    public ResponseData login(String userName, String password) {
        Account account = accountCache.get(userName);
        if (account == null) {
            return ResponseDataBuilder.notExistUser();
        }
        password = Md5Crypt.apr1Crypt(password, configService.getMd5Salt());
        if (StringUtils.equals(account.getPassword(), password)) {
            UserToken userToken = userTokenCache.get(userName);
            if (userToken == null || UserUtils.isExpire(userToken)) {
                try {
                    if (LoginLock.tryLock(userName)) {
                        if (userToken == null) {
                            userToken = userTokenService.insertUserToken(userName);
                        } else if (UserUtils.isExpire(userToken)) {
                            userTokenService.updateUserToken(userToken);
                        }
                        userTokenCache.set(userToken.getToken(), userToken);
                        userTokenCache.set(userToken.getUserName(), userToken);
                    } else {
                        //获取锁失败，不要重复登录
                        return ResponseDataBuilder.repeatLogin();
                    }
                } finally {
                    LoginLock.unLock(userName);
                }
            }
            return ResponseDataBuilder.buildSuccessResponseData(buildUserInfo(account, userToken));
        } else {
            return ResponseDataBuilder.passwordError();
        }
    }

    public ResponseData logOut(String token) {
        UserToken userToken = userTokenCache.get(token);
        if (userToken != null) {
            userTokenService.expireUserToken(userToken);
            userTokenCache.set(userToken.getToken(), userToken);
            userTokenCache.set(userToken.getUserName(), userToken);
        }
        return ResponseDataBuilder.buildSuccessResponseData(true);
    }

    private UserInfo buildUserInfo(Account account, UserToken userToken) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(account.getId());
        userInfo.setUserName(account.getUserName());
        userInfo.setNickName(account.getNickName());
        userInfo.setUserType(account.getUserType());
        userInfo.setToken(userToken.getToken());
        return userInfo;
    }
}
