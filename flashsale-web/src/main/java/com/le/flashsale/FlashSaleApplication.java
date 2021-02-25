
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Date 2020/11/15 7:11 下午
 * Author le
 */
@SpringBootApplication
@ImportResource(value = {"classpath:mysql/mybatis-jdbc.xml"})
@MapperScan(basePackages = "com.le.flashsale.mapper")
// @ComponentScan(basePackages = {"com.le.flashsale.**"})
@EnableScheduling
public class FlashSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashSaleApplication.class,args);
    }
}
