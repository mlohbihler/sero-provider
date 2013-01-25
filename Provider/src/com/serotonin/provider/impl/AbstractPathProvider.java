package com.serotonin.provider.impl;

import java.io.File;

import com.serotonin.provider.HomePathProvider;

abstract public class AbstractPathProvider implements HomePathProvider {
    @Override
    public File getRealPathIfRelative(String path) {
        if (path.startsWith("."))
            return getRealPath(path.substring(1));
        return new File(path);
    }
}
