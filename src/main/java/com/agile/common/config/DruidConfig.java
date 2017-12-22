package com.agile.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
public class DruidConfig implements EnvironmentAware {
    private Environment env;

    @Bean(initMethod = "init",destroyMethod = "close",name = "dataSource1")
    DruidDataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(env.getProperty("agile.druid.driver_class_name"));
        druidDataSource.setUrl(env.getProperty("agile.druid.jdbc_url_prefix")+env.getProperty("agile.druid.data_base_ip")+":"+env.getProperty("agile.druid.data_base_post")+"/"+env.getProperty("agile.druid.data_base_name")+"?"+env.getProperty("agile.druid.data_base_url_param"));
        druidDataSource.setUsername(env.getProperty("agile.druid.data_base_username"));
        druidDataSource.setPassword(env.getProperty("agile.druid.data_base_password"));
        druidDataSource.setInitialSize(env.getProperty("agile.druid.init_size",int.class));
        druidDataSource.setMinIdle(env.getProperty("agile.druid.min_idle",int.class));
        druidDataSource.setMaxActive(env.getProperty("agile.druid.max_active",int.class));
        druidDataSource.setMaxWait(env.getProperty("agile.druid.max_wait",int.class));
        druidDataSource.setRemoveAbandoned(env.getProperty("agile.druid.remove_abandoned",boolean.class));
        druidDataSource.setRemoveAbandonedTimeout(env.getProperty("agile.druid.remove_abandoned_timeout",int.class));
        druidDataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("agile.druid.time_between_eviction_runs_millis",int.class));
        druidDataSource.setMinEvictableIdleTimeMillis(env.getProperty("agile.druid.min_evictable_idle_time_millis",int.class));
        druidDataSource.setValidationQuery(env.getProperty("agile.druid.validation_query"));
        druidDataSource.setTestWhileIdle(env.getProperty("agile.druid.test_while_idle",boolean.class));
        druidDataSource.setTestOnBorrow(env.getProperty("agile.druid.test_on_borrow",boolean.class));
        druidDataSource.setTestOnReturn(env.getProperty("agile.druid.test_on_return",boolean.class));
        druidDataSource.setFilters(env.getProperty("agile.druid.filters"));
        druidDataSource.setPoolPreparedStatements(env.getProperty("agile.druid.pool_prepared_statements",boolean.class));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(env.getProperty("agile.druid.max_pool_prepared_statement_per_connection_size",int.class));
        druidDataSource.setFilters(env.getProperty("agile.druid.max_pool_prepared_statement_per_connection_size"));
        druidDataSource.setUseGlobalDataSourceStat(env.getProperty("agile.druid.global_data_source_stat",boolean.class));
        return druidDataSource;

    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        env = environment;
    }
}
