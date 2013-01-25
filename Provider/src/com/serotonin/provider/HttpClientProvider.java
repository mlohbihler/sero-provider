package com.serotonin.provider;

import org.eclipse.jetty.client.HttpClient;

public interface HttpClientProvider extends Provider {
    HttpClient getHttpClient();
}
