package com.agile.common.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = {"com.agile.mvc.model.dao"},transactionManagerRef = "transactionManager",entityManagerFactoryRef = "entityManagerFactory")
public class JPAConfig implements EnvironmentAware {

    private Environment env;

    private final DataSource dataSource;

    @Autowired
    public JPAConfig(@Qualifier(value = "dataSource1") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.valueOf(env.getProperty("agile.jpa.db")));
        jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("agile.jpa.generate_ddl")));
        jpaVendorAdapter.setDatabasePlatform(env.getProperty("agile.jpa.database_platform"));

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.agile.mvc.model.entity");
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    private Properties jpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("agile.jpa.hbm2ddl"));
        properties.setProperty("hibernate.current_session_context_class",env.getProperty("agile.jpa.current_session_context_class"));
        properties.setProperty("hibernate.cache.use_second_level_cache",env.getProperty("agile.jpa.use_second_level_cache"));
        properties.setProperty("hibernate.cache.region.factory_class",env.getProperty("agile.jpa.cache_region_factory_class"));
        properties.setProperty("hibernate.cache.use_structured_entries",env.getProperty("agile.jpa.use_structured_entries"));
        properties.setProperty("net.sf.ehcache.configurationResourceName","/com/agile/configure/agile-ehcache.xml");
        properties.setProperty("hibernate.cache.use_query_cache",env.getProperty("agile.jpa.use_query_cache"));
        properties.setProperty("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.show_sql",env.getProperty("agile.jpa.show_sql"));
        properties.setProperty("hibernate.format_sql",env.getProperty("agile.jpa.format_sql"));
        properties.setProperty("hibernate.ejb.interceptor","com.agile.common.interceptor.JpaInterceptor");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        env = environment;
    }
}
