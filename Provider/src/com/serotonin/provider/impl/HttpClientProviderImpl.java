package com.serotonin.provider.impl;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.serotonin.provider.HttpClientProvider;

public class HttpClientProviderImpl implements HttpClientProvider {
    @Override
    public HttpClient getHttpClient() {
        return getHttpClient(30000);
    }

    @Override
    public HttpClient getHttpClient(int timeoutMS) {
        return getHttpClient(timeoutMS, timeoutMS);
    }

    @Override
    public HttpClient getHttpClient(int connectMS, int readMS) {
        HttpClient client = createClient();
        client.getParams().setParameter("http.connection.timeout", connectMS);
        client.getParams().setParameter("http.connection-manager.timeout", connectMS);
        client.getParams().setParameter("http.socket.timeout", readMS);
        client.getParams().setParameter("http.protocol.head-body-timeout", readMS);
        return client;
    }

    public HttpClient createClient() {
        return new DefaultHttpClient();
    }
}
