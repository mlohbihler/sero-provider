package com.serotonin.provider;

public interface BuildNumberProvider extends Provider {
    String getBuildNumber();

    String getLabel();
}
