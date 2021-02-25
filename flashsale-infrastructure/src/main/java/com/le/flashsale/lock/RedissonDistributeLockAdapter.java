
package com.le.flashsale.lock;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.le.flashsale.adapter.DistributeLockAdapter;

/**
 * Redisson的分布式锁实现
 * <p>
 * Date 2020/11/17 6:26 下午
 * Author le
 */
@Service
public class RedissonDistributeLockAdapter implements DistributeLockAdapter {

    private Logger LOG = LoggerFactory.getLogger(RedissonDistributeLockAdapter.class);

    @Resource
    private RedissonClient redissonClient;

    /**
     * 默认1000秒有效
     *
     * @param lockKey
     * @return
     * @throws Throwable
     */
    public boolean lock(String lockKey) {
        return lock(lockKey, 1, 60*60*2, TimeUnit.SECONDS);
    }

    public boolean lock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) {
        RLock rLock = redissonClient.getLock(lockKey);

        // 如果已经被加锁，则返回加锁失败
        if (rLock.isLocked()) {
            return false;
        }

        // 否则尝试加锁
        boolean isSuccess = false;
        try {
            isSuccess = redissonClient.getLock(lockKey).tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            LOG.error("redissonClient.getLock is error！", e);
        }

        return isSuccess;
    }

    public void unlock(String lockKey) {
        redissonClient.getLock(lockKey).unlock();
    }
}
