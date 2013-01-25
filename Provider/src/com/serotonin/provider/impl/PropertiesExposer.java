package com.serotonin.provider.impl;

import com.serotonin.provider.PropertiesProvider;
import com.serotonin.provider.Provider;
import com.serotonin.provider.Providers;
import com.serotonin.util.properties.AbstractProperties;

abstract public class PropertiesExposer implements Provider {
    public boolean available() {
        PropertiesProvider<?> p = Providers.get(PropertiesProvider.class);
        if (p != null)
            return p.getProperties() != null;
        return false;
    }

    protected AbstractProperties getProps() {
        return Providers.get(PropertiesProvider.class).getProperties();
    }
}
