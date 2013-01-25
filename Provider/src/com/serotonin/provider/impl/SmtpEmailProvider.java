package com.serotonin.provider.impl;

import javax.mail.internet.InternetAddress;

import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;

import com.serotonin.provider.EmailProvider;
import com.serotonin.provider.Providers;
import com.serotonin.provider.impl.email.EmailExceptionListener;
import com.serotonin.provider.impl.email.EmailWorkItem;
import com.serotonin.web.mail.AddressUtils;
import com.serotonin.web.mail.EmailContent;
import com.serotonin.web.mail.EmailSender;

public class SmtpEmailProvider implements EmailProvider {
    private final EmailExceptionListener exceptionListener;

    public SmtpEmailProvider(EmailExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    @Override
    public void send(EmailWorkItem e) throws MailException {
        try {
            sendImpl(e);
        }
        catch (RuntimeException ex) {
            // If there is no exception listener, rethrow
            if (exceptionListener == null)
                throw ex;

            // Otherwise, provide to the listener. It may itself throw an exception (e.g. rethrow the original)
            try {
                exceptionListener.emailException(e, ex);
            }
            catch (RuntimeException ex2) {
                throw ex2;
            }
            catch (Exception ex2) {
                throw new RuntimeException(ex2);
            }
        }
    }

    private void sendImpl(EmailWorkItem e) throws MailException {
        EmailProperties props = Providers.get(EmailProperties.class);
        EmailSender sender = new EmailSender(props.getSmtpHost(), props.getSmtpPort(), props.isSmtpAuth(),
                props.getSmtpUsername(), props.getSmtpPassword());

        EmailContent content;
        InternetAddress from, replyTo;
        InternetAddress[] to;
        try {
            content = e.getContent();

            from = new InternetAddress(props.getSmtpFromAddress(), props.getSmtpFromPretty());
            replyTo = null;
            if (AddressUtils.isValidEmailAddress(e.getReplyTo()))
                replyTo = new InternetAddress(e.getReplyTo());

            to = new InternetAddress[e.getToAddr().length];
            for (int i = 0; i < to.length; i++)
                to[i] = new InternetAddress(e.getToAddr()[i]);
        }
        catch (Exception ex) {
            throw new MailParseException(ex);
        }

        sender.send(from, replyTo, to, null, null, e.getSubject(), content);
    }
}
