
package com.le.flashsale.common.service;

import com.le.flashsale.order.dto.NotifyDTO;

/**
 * Date 2020/11/17 11:50 下午
 * Author le
 */
public interface EmailService {

    void sendMail(NotifyDTO notifyDTO);
}
