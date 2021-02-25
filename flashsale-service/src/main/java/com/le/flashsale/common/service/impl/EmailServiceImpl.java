
package com.le.flashsale.common.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.le.flashsale.adapter.EmailAdapter;
import com.le.flashsale.adapter.dto.EmailContext;
import com.le.flashsale.common.Constant;
import com.le.flashsale.common.gson.GsonUtils;
import com.le.flashsale.common.service.EmailService;
import com.le.flashsale.order.dto.NotifyDTO;
import com.le.flashsale.user.dto.UserDTO;
import com.le.flashsale.user.service.UserService;

/**
 * 邮件发送服务
 * <p>
 * Date 2020/11/17 11:50 下午
 * Author le
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Resource
    private EmailAdapter emailAdapter;

    @Resource
    private UserService userService;

    @Resource
    private Environment environment;

    @RabbitListener(queues = {"${mq.flashsale.send.email.queue}"}, containerFactory = "singleListenerContainer")
    @Override
    public void sendMail(NotifyDTO notifyDTO) {
        if (notifyDTO == null) {
            logger.error("秒杀成功的邮件通知发送失败！失败原因；notifyDTO is null");
        }
        if (Strings.isBlank(notifyDTO.getOrderNo()) || notifyDTO.getUserId() == null
                || notifyDTO.getOrderId() == null) {
            logger.error("秒杀成功的邮件通知发送失败！失败原因；入参为空！notifyDTO={}", GsonUtils.toJson(notifyDTO));
        }

        UserDTO userDTO = userService.queryUserInfoById(notifyDTO.getUserId());
        if (userDTO == null) {
            logger.error("秒杀成功的邮件通知发送失败！失败原因：根据userId查找不到对应的用户信息。 userId={}", notifyDTO.getUserId());
        }
        if (StringUtils.isBlank(userDTO.getEmail())) {
            logger.error("秒杀成功的邮件通知发送失败！失败原因：该用户没有配置email地址。userId={}", notifyDTO.getUserId());
        }

        // 获取用户配置的邮箱信息，规则要求，多个邮箱用逗号分割。
        List<String> mailTo =
                Arrays.stream(userDTO.getEmail().split(",")).filter(email -> Strings.isNotBlank(email)).distinct()
                        .collect(Collectors.toList());

        try {
            logger.info("秒杀成功的邮件通知--begin orderId={}", notifyDTO.getOrderId());

            //TODO:真正的发送邮件....
            EmailContext emailContext = new EmailContext();
            emailContext.setMailFrom(environment.getProperty("spring.mail.username"));
            emailContext.setMailTo(mailTo);
            emailContext.setSubject(Constant.EMAIL_SUBJECT);
            emailContext.setContent(String.format(Constant.EMAIL_CONTANT, notifyDTO.getOrderNo()));
            emailAdapter.sendMail(emailContext);
            logger.info("秒杀成功的邮件通知发送成功！--end orderId={}", notifyDTO.getOrderId());
        } catch (Exception e) {
            logger.error("秒杀成功的邮件通知发生异常：", e.fillInStackTrace());
        }

    }
}
