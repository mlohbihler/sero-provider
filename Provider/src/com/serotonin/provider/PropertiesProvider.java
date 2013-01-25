package com.serotonin.provider;

import com.serotonin.util.properties.AbstractProperties;

public interface PropertiesProvider<T extends AbstractProperties> extends Provider {
    T getProperties();
}
