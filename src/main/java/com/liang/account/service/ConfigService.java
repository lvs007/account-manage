package com.liang.account.service;

import liang.common.util.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Service
public class ConfigService {

    @Autowired
    private PropertiesManager propertiesManager;

    public String getMd5Salt() {
        return propertiesManager.getString("md5.salt", "xxfuckxx");
    }

    public int getTokenEffectiveTime() {
        return propertiesManager.getInt("token.effective.time", 30);
    }
}
