package com.serotonin.provider.impl;

import com.serotonin.epoll.ProcessEPoll;
import com.serotonin.provider.ProcessEPollProvider;
import com.serotonin.provider.Providers;
import com.serotonin.provider.TimerProvider;

public class ProcessEPollProviderImpl implements ProcessEPollProvider {
    private ProcessEPoll processEPoll;

    public void initialize() {
        processEPoll = new ProcessEPoll();
        Providers.get(TimerProvider.class).getTimer().execute(processEPoll, processEPoll.getClass().getSimpleName());
    }

    public void terminate() {
        processEPoll.terminate();
    }

    @Override
    public ProcessEPoll getProcessEPoll() {
        if (processEPoll == null)
            throw new RuntimeException("ProcessEPollProviderImpl has not been initialized");
        return processEPoll;
    }
}
