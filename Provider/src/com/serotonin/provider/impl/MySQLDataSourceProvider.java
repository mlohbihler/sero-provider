package com.serotonin.provider.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.db.DeregisteringDataSource;
import com.serotonin.provider.DataSourceProvider;
import com.serotonin.provider.PropertiesProvider;
import com.serotonin.provider.Providers;
import com.serotonin.util.properties.AbstractProperties;

public class MySQLDataSourceProvider implements DataSourceProvider {
    private static final Log LOG = LogFactory.getLog(MySQLDataSourceProvider.class);

    private String url = null;
    private String schema = null;
    private String username = null;
    private String password = null;
    private int maxActive = -1;
    private int maxIdle = -1;

    private String urlKey = "db.url";
    private String schemaKey = "db.schema";
    private String usernameKey = "db.username";
    private String passwordKey = "db.password";
    private String maxActiveKey = "db.pool.maxActive";
    private String maxIdleKey = "db.pool.maxIdle";

    private DeregisteringDataSource dataSource;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getSchemaKey() {
        return schemaKey;
    }

    public void setSchemaKey(String schemaKey) {
        this.schemaKey = schemaKey;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public String getMaxActiveKey() {
        return maxActiveKey;
    }

    public void setMaxActiveKey(String maxActiveKey) {
        this.maxActiveKey = maxActiveKey;
    }

    public String getMaxIdleKey() {
        return maxIdleKey;
    }

    public void setMaxIdleKey(String maxIdleKey) {
        this.maxIdleKey = maxIdleKey;
    }

    public void initialize() {
        dataSource = new DeregisteringDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setMinIdle(3);
        dataSource.setInitialSize(3);
        dataSource.setMaxWait(-1);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(10000);
        dataSource.setMinEvictableIdleTimeMillis(60000);

        AbstractProperties props = Providers.get(PropertiesProvider.class).getProperties();
        if (url == null)
            url = props.getString(urlKey);
        if (schema == null)
            schema = props.getString(schemaKey);
        if (username == null)
            username = props.getString(usernameKey);
        if (password == null)
            password = props.getString(passwordKey);
        if (maxActive == -1)
            maxActive = props.getInt(maxActiveKey, 50);
        if (maxIdle == -1)
            maxIdle = props.getInt(maxIdleKey, 3);

        dataSource.setUrl(url);
        dataSource.setDefaultCatalog(schema);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxIdle(maxIdle);

        // Misc settings.
        dataSource.addConnectionProperty("useUnicode", "yes");
        dataSource.addConnectionProperty("characterEncoding", "UTF-8");
        dataSource.addConnectionProperty("zeroDateTimeBehavior", "convertToNull");

        // Test connection
        Connection conn = null;
        try {
            try {
                conn = dataSource.getConnection();
            }
            finally {
                if (conn != null)
                    conn.close();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Exception while connecting to data source at " + url + " (key=" + urlKey + ")",
                    e);
        }
    }

    public void terminate() {
        try {
            if (dataSource != null)
                dataSource.close();
        }
        catch (SQLException e) {
            LOG.warn("Failed to close data source with url " + url + " (key=" + urlKey + ")", e);
        }
    }

    @Override
    public DataSource getDataSource() {
        if (dataSource == null)
            throw new RuntimeException("MySQLDataSourceProvider has not been initialized");
        return dataSource;
    }
}
