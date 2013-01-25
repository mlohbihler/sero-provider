package com.serotonin.provider.impl;

import com.serotonin.provider.PropertiesProvider;
import com.serotonin.util.properties.ReloadingProperties;

public class ReloadingPropertiesProvider implements PropertiesProvider<ReloadingProperties> {
    private final ReloadingProperties props;

    public ReloadingPropertiesProvider(ReloadingProperties props) {
        this.props = props;
    }

    @Override
    public ReloadingProperties getProperties() {
        return props;
    }
}
