
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.email;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.le.flashsale.adapter.EmailAdapter;
import com.le.flashsale.adapter.dto.EmailContext;

/**
 * JavaMail的发送电子邮件实现类
 * <p>
 * Date 2020/11/17 11:28 下午
 * Author le
 */
@EnableAsync
@Service
public class JavaMailEmailAdapter implements EmailAdapter {

    private Logger logger = LoggerFactory.getLogger(JavaMailEmailAdapter.class);

    @Resource
    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(EmailContext emailContext) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
            messageHelper.setFrom(emailContext.getMailFrom());
            messageHelper.setTo(emailContext.getMailTo().toArray(new String[emailContext.getMailTo().size()]));
            messageHelper.setSubject(emailContext.getSubject());
            messageHelper.setText(emailContext.getContent(), true);

            javaMailSender.send(message);

            logger.info("邮件发送成功!");
        } catch (Exception e) {
            logger.error("邮件发生异常： ", e);
        }
    }
}
