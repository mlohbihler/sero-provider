package com.serotonin.provider.impl;

import java.io.File;

import javax.servlet.ServletContext;

public class ServletContextPathProvider extends AbstractPathProvider {
    private final ServletContext servletContext;

    public ServletContextPathProvider(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public File getRealPath(String path) {
        return new File(servletContext.getRealPath(path));
    }
}
