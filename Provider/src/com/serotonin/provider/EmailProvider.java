package com.serotonin.provider;

import com.serotonin.provider.impl.email.EmailWorkItem;

public interface EmailProvider extends Provider {
    void send(EmailWorkItem emailWorkItem);
}
