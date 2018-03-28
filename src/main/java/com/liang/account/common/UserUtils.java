package com.liang.account.common;

import com.liang.account.bo.UserToken;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class UserUtils {

    /**
     * 用户的token是否过期，过期返回true，未过期返回false
     *
     * @param userToken
     * @return
     */
    public static boolean isExpire(UserToken userToken) {
        if (userToken.getEndTime() > System.currentTimeMillis()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取token的有效截止时间
     *
     * @param effectiveTime
     * @return
     */
    public static long getEndTime(int effectiveTime) {
        return DateUtils.addDays(Calendar.getInstance().getTime(), effectiveTime).getTime();
    }

    /**
     * 生成一个新的token
     *
     * @return
     */
    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
