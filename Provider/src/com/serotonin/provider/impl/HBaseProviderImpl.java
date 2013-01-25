package com.serotonin.provider.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import com.serotonin.provider.HBaseProvider;
import com.serotonin.provider.PropertiesProvider;
import com.serotonin.provider.Providers;
import com.serotonin.util.properties.AbstractProperties;

public class HBaseProviderImpl implements HBaseProvider {
    private static final Log LOG = LogFactory.getLog(HBaseProviderImpl.class);

    private String quorum = null;
    private String quorumKey = "hbase.zookeeper.quorum";

    private Configuration configuration;

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public String getQuorumKey() {
        return quorumKey;
    }

    public void setQuorumKey(String quorumKey) {
        this.quorumKey = quorumKey;
    }

    public boolean initialize() {
        if (quorum == null) {
            AbstractProperties props = Providers.get(PropertiesProvider.class).getProperties();
            quorum = props.getString(quorumKey);
        }

        if (StringUtils.isBlank(quorum)) {
            LOG.warn("Property " + quorumKey + " not defined. HBase services will not be available.");
            return false;
        }

        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", quorum);
        return true;
    }

    public void terminate() {
        // no op
    }

    @Override
    public Configuration getConfiguration() {
        if (configuration == null)
            throw new RuntimeException(
                    "HBase services are not available. HBaseProviderImpl may not have been initialized");
        return configuration;
    }
}
