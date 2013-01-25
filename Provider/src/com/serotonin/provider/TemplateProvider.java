package com.serotonin.provider;

import freemarker.template.Configuration;

public interface TemplateProvider extends Provider {
    Configuration getConfiguration();
}
