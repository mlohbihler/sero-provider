package com.serotonin.provider;

import com.amazonaws.services.s3.AmazonS3;

public interface S3Provider extends Provider {
    AmazonS3 getS3Client();
}
