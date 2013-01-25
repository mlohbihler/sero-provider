package com.serotonin.provider;

import javax.servlet.ServletContext;

public interface ServletContextProvider extends Provider {
    ServletContext getServletContext();
}
