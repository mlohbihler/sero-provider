package com.serotonin.provider.impl;

abstract public class EmailProperties extends PropertiesExposer {
    public String getLogEmailRecipients() {
        if (!available())
            return null;
        return getProps().getString("log.smtp.recipients");
    }

    public String getSmtpHost() {
        return getProps().getString("smtp.host", "localhost");
    }

    public int getSmtpPort() {
        return getProps().getInt("smtp.port", 25);
    }

    public boolean isSmtpAuth() {
        return getProps().getBoolean("smtp.auth", false);
    }

    public String getSmtpUsername() {
        return getProps().getString("smtp.username");
    }

    public String getSmtpPassword() {
        return getProps().getString("smtp.password");
    }

    public String getSmtpFromAddress() {
        return getProps().getString("smtp.from.addr", getDefaultSmtpFromAddress());
    }

    abstract protected String getDefaultSmtpFromAddress();

    public String getSmtpFromPretty() {
        return getProps().getString("smtp.from.pretty", getDefaultSmtpFromPretty());
    }

    abstract protected String getDefaultSmtpFromPretty();
}
