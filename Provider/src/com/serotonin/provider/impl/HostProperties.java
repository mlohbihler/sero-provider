package com.serotonin.provider.impl;

abstract public class HostProperties extends PropertiesExposer {
    //
    //
    // Host integration
    //
    public String getProjectsDir() {
        return getProps().getString("host.projects.dir", "./WEB-INF/projects");
    }

    public String getPythonPath() {
        return getProps().getString("host.python.path", "python");
    }

    public String getEngineScriptsDir() {
        return getProps().getString("host.scripts.dir", "./WEB-INF/scripts");
    }

    public String getLogsDir() {
        return getProps().getString("host.logs.dir", getDefaultLogsDir());
    }

    abstract protected String getDefaultLogsDir();
}
