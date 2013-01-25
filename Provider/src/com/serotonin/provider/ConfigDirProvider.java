package com.serotonin.provider;

import java.io.File;

public interface ConfigDirProvider extends Provider {
    File getConfigDir();
}
