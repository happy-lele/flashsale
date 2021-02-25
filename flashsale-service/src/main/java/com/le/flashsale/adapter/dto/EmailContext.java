
package com.le.flashsale.adapter.dto;

import java.util.List;

/**
 * Date 2020/11/17 11:24 下午
 * Author le
 */
public class EmailContext {

    // 发送人
    private String mailFrom;

    //主题
    private String subject;

    //内容
    private String content;

    //接收人列表
    private List<String> mailTo;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMailTo() {
        return mailTo;
    }

    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }
}
