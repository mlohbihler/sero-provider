package com.serotonin.provider.impl;

import java.util.concurrent.ExecutorService;

import com.serotonin.provider.ExecutorServiceProvider;

public class ExecutorServiceProviderImpl implements ExecutorServiceProvider {
    private final ExecutorService executorService;

    public ExecutorServiceProviderImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }
}
