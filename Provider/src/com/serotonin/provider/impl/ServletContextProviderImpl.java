package com.serotonin.provider.impl;

import javax.servlet.ServletContext;

import com.serotonin.provider.ServletContextProvider;

public class ServletContextProviderImpl implements ServletContextProvider {
    private final ServletContext servletContext;

    public ServletContextProviderImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }
}
