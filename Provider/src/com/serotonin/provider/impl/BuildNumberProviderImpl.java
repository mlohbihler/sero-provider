package com.serotonin.provider.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.serotonin.io.StreamUtils;
import com.serotonin.provider.BuildNumberProvider;
import com.serotonin.provider.HomePathProvider;
import com.serotonin.provider.Providers;

public class BuildNumberProviderImpl implements BuildNumberProvider {
    private String buildNumber;
    private String label;

    public BuildNumberProviderImpl(String label, String[] filenames) {
        this.label = label;

        HomePathProvider homePathProvider = Providers.get(HomePathProvider.class);

        // Get the build number if it is there.
        try {
            for (String filename : filenames) {
                try {
                    if (!StringUtils.isBlank(filename)) {
                        buildNumber = StreamUtils.readFile(homePathProvider.getRealPath(filename).getPath());
                        break;
                    }
                }
                catch (FileNotFoundException e) {
                    // Ignore
                }
            }
        }
        catch (IOException e1) {
            buildNumber = e1.getMessage();
        }

        if (buildNumber == null)
            buildNumber = "(unknown)";
        else
            buildNumber = buildNumber.trim();
    }

    @Override
    public String getBuildNumber() {
        return buildNumber;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
