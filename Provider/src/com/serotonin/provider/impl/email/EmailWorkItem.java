package com.serotonin.provider.impl.email;

import com.serotonin.provider.EmailProvider;
import com.serotonin.provider.Providers;
import com.serotonin.web.mail.EmailContent;

abstract public class EmailWorkItem implements Runnable {
    private final String replyTo;
    private final String[] toAddr;
    private final String subject;

    public EmailWorkItem(String replyTo, String[] toAddr, String subject) {
        this.replyTo = replyTo;
        this.toAddr = toAddr;
        this.subject = subject;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String[] getToAddr() {
        return toAddr;
    }

    public String getSubject() {
        return subject;
    }

    abstract public EmailContent getContent() throws Exception;

    @Override
    public void run() {
        Providers.get(EmailProvider.class).send(this);
    }
}
