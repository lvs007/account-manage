package com.liang.account.cache.lock;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liangzhiyan on 2017/3/18.
 */
public class LoginLock {

    private static final int TRY_TIMES = 5;

    private static final LoadingCache<String, ReentrantLock> loginLockCache = CacheBuilder.newBuilder().
            initialCapacity(100000).maximumSize(1000000).expireAfterAccess(10, TimeUnit.MINUTES).
            build(new CacheLoader<String, ReentrantLock>() {
                @Override
                public ReentrantLock load(String key) throws Exception {
                    return new ReentrantLock();
                }
            });

    public static void lock(String key) {
        int tryTimes = 0;
        while (tryTimes < TRY_TIMES) {
            ReentrantLock reentrantLock = loginLockCache.getIfPresent(key);
            if (reentrantLock == null) {
                ++tryTimes;
            } else {
                reentrantLock.lock();
                return;
            }
        }
    }

    public static boolean tryLock(String key) {
        ReentrantLock reentrantLock = get(key);
        if (reentrantLock == null) {
            return false;
        }
        return reentrantLock.tryLock();
    }

    public static boolean tryLock(String key, long timeOut) throws InterruptedException {
        ReentrantLock reentrantLock = loginLockCache.getIfPresent(key);
        if (reentrantLock == null) {
            return false;
        }
        return reentrantLock.tryLock(timeOut, TimeUnit.MILLISECONDS);
    }

    public static void unLock(String key) {
        ReentrantLock reentrantLock = get(key);
        if (reentrantLock != null) {
            reentrantLock.unlock();
        }
    }

    public static ReentrantLock get(String key) {
        try {
            return loginLockCache.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}
