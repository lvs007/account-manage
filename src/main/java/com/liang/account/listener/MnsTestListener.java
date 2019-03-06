package com.liang.account.listener;

import com.liang.mq.consumer.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created by liangzhiyan on 2017/4/28.
 */
@Service
public class MnsTestListener implements MessageListener<String> {
    @Override
    public Class getType() {
        return String.class;
    }

    @Override
    public void consumerMessage(String s) {
        System.out.println(Thread.currentThread().getId() + ",thread name" + Thread.currentThread().getName() + ",mns message : " + s);
    }
}
