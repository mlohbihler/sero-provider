package com.serotonin.provider.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.serotonin.provider.PropertiesProvider;
import com.serotonin.provider.Providers;
import com.serotonin.provider.S3Provider;
import com.serotonin.util.properties.AbstractProperties;

public class S3ProviderImpl implements S3Provider {
    private String accessKey = null;
    private String secretKey = null;

    private String accessKeyKey = "s3.accessKey";
    private String secretKeyKey = "s3.secretKey";

    private AmazonS3 s3Client;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKeyKey() {
        return accessKeyKey;
    }

    public void setAccessKeyKey(String accessKeyKey) {
        this.accessKeyKey = accessKeyKey;
    }

    public String getSecretKeyKey() {
        return secretKeyKey;
    }

    public void setSecretKeyKey(String secretKeyKey) {
        this.secretKeyKey = secretKeyKey;
    }

    public void initialize() {
        AbstractProperties props = Providers.get(PropertiesProvider.class).getProperties();
        if (accessKey == null)
            accessKey = props.getString(accessKeyKey);
        if (secretKey == null)
            secretKey = props.getString(secretKeyKey);

        AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = new AmazonS3Client(creds);
    }

    @Override
    public AmazonS3 getS3Client() {
        return s3Client;
    }
}
