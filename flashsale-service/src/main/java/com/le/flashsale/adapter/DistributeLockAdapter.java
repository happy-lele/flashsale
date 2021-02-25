
package com.le.flashsale.adapter;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * <p>
 * Date 2020/11/17 1:37 下午
 * Author le
 */
public interface DistributeLockAdapter {

    boolean lock(String lockKey);

    boolean lock(String lockKey, long waitTime, long leaseTime, TimeUnit unit);

    void unlock(String lockKey);

}
