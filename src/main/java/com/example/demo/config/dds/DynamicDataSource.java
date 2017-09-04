package com.example.demo.config.dds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 通过路由变更数据源
 * Created by Jet.chen on 2017/9/4.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        // 数据源信息得从线程池中获取
        return DynamicDataSourceThreadLocal.getDataSource();
    }
}
