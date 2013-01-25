package com.serotonin.provider;

import org.apache.hadoop.conf.Configuration;

public interface HBaseProvider extends Provider {
    Configuration getConfiguration();
}
