package com.serotonin.provider;

import java.io.File;

public interface HomePathProvider extends Provider {
    File getRealPath(String path);

    File getRealPathIfRelative(String path);
}
