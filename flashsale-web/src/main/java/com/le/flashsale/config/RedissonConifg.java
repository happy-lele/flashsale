
package com.le.flashsale.config;

import javax.annotation.Resource;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Date 2020/11/17 7:56 下午
 * Author le
 */
@Configuration
public class RedissonConifg {

    @Resource
    private Environment environment;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(environment.getProperty("redis.host"));
        return Redisson.create(config);
    }
}
