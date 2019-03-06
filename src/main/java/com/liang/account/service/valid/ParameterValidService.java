package com.liang.account.service.valid;

import com.liang.account.dao.AccountDao;
import com.liang.common.exception.ParameterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class ParameterValidService {

    @Autowired
    private AccountDao accountDao;

    public boolean validRegisterParam(String userName, String password, String nickName) {
        if (StringUtils.isBlank(userName)) {
            ParameterException.throwException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            ParameterException.throwException("密码不能为空");
        }
        if (StringUtils.length(userName) > 64) {
            ParameterException.throwException("用户名长度不能超过64个字");
        }
        int passwordLength = StringUtils.length(password);
        if (passwordLength > 64 || passwordLength < 6) {
            ParameterException.throwException("密码长度必须在6到64之间");
        }
        if (StringUtils.isNotBlank(nickName) && StringUtils.length(nickName) > 64) {
            ParameterException.throwException("昵称长度不能超过64个字");
        }
        return true;
    }
}
