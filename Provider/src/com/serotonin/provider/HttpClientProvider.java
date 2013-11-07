package com.serotonin.provider;

import org.apache.http.client.HttpClient;

public interface HttpClientProvider extends Provider {
    HttpClient getHttpClient();

    HttpClient getHttpClient(int timeoutMS);

    HttpClient getHttpClient(int connectMS, int readMS);
}
