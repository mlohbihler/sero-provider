package com.serotonin.provider.impl.email;

import com.serotonin.web.mail.EmailContent;

public class TemplateEmailWorkItem extends EmailWorkItem {
    private final String templateName;
    private final Object model;

    public TemplateEmailWorkItem(String replyTo, String[] toAddr, String subject, String templateName, Object model) {
        super(replyTo, toAddr, subject);
        this.templateName = templateName;
        this.model = model;
    }

    @Override
    public EmailContent getContent() throws Exception {
        return new SimpleTemplateContent(templateName, model);
    }
}
