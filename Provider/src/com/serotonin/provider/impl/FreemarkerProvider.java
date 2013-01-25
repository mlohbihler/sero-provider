package com.serotonin.provider.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.provider.TemplateProvider;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class FreemarkerProvider implements TemplateProvider {
    private static final Log LOG = LogFactory.getLog(FreemarkerProvider.class);

    private final Configuration configuration;

    public FreemarkerProvider(File ftlDir) {
        configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(ftlDir);
        }
        catch (IOException e) {
            LOG.error("Exception defining Freemarker template directory", e);
        }
        configuration.setObjectWrapper(new DefaultObjectWrapper());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
