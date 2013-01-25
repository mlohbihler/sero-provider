package com.serotonin.provider.impl.email;

import com.serotonin.web.mail.EmailContent;

public class BasicEmailWorkItem extends EmailWorkItem {
    private final String plainContent;
    private final String htmlContent;

    public BasicEmailWorkItem(String replyTo, String[] toAddr, String subject, String plainContent, String htmlContent) {
        super(replyTo, toAddr, subject);
        this.plainContent = plainContent;
        this.htmlContent = htmlContent;
    }

    @Override
    public EmailContent getContent() {
        return new EmailContent(plainContent, htmlContent);
    }
}
