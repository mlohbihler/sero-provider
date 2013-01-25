package com.serotonin.provider.impl;

import java.util.Map;

import com.serotonin.provider.PropertiesProvider;
import com.serotonin.util.properties.BasicProperties;

public class BasicPropertiesProvider implements PropertiesProvider<BasicProperties> {
    private final BasicProperties props;

    public BasicPropertiesProvider(Map<String, String> properties) {
        props = new BasicProperties(properties);
    }

    @Override
    public BasicProperties getProperties() {
        return props;
    }
}
