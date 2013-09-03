package com.serotonin.provider;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceProvider extends Provider {
    ExecutorService getExecutorService();
}
