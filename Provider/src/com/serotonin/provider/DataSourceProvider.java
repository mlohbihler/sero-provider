package com.serotonin.provider;

import javax.sql.DataSource;

public interface DataSourceProvider extends Provider {
    DataSource getDataSource();
}
