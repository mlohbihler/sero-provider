package com.serotonin.provider.impl;

import org.eclipse.jetty.client.HttpClient;

import com.serotonin.provider.HttpClientProvider;

public class HttpClientProviderImpl implements HttpClientProvider {
    private final HttpClient httpClient;

    public HttpClientProviderImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpClient getHttpClient() {
        return httpClient;
    }
}
