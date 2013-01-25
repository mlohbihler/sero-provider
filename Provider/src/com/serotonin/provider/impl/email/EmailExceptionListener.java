package com.serotonin.provider.impl.email;

public interface EmailExceptionListener {
    void emailException(EmailWorkItem e, Exception ex) throws Exception;
}
