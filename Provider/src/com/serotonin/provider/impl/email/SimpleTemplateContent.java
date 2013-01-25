package com.serotonin.provider.impl.email;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.provider.Providers;
import com.serotonin.provider.TemplateProvider;
import com.serotonin.web.mail.TemplateEmailContent;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SimpleTemplateContent extends TemplateEmailContent {
    private static final Log LOG = LogFactory.getLog(SimpleTemplateContent.class);

    public SimpleTemplateContent(String filename, Object model) throws TemplateException, IOException {
        super(getTemplate(filename, true), getTemplate(filename, false), model, null);
    }

    private static Template getTemplate(String filename, boolean plain) throws IOException {
        try {
            Configuration conf = Providers.get(TemplateProvider.class).getConfiguration();
            if (plain)
                return conf.getTemplate("plain/" + filename);
            return conf.getTemplate("html/" + filename);
        }
        catch (FileNotFoundException e) {
            LOG.info("Can't find email template file: " + e.getMessage());
            return null;
        }
    }
}
