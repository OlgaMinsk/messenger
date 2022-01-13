package com.innowisegroup.messenger.config;

import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


@Configuration
@ComponentScan("com.innowisegroup.messenger")
@PropertySource("classpath:postgresql.properties")
//@EnableTransactionManagement
public class AppConfiguration {

    private static final String PROPERTY_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String PROPERTY_QUERY_CACHE = "hibernate.cache.use_query_cache";
    private static final String PROPERTY_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    private static final String PROPERTY_CACHE_EH_CACHE_REGION_FACTORY = "org.hibernate.cache.ehcache.EhCacheRegionFactory";
    private static final String PROPERTY_TRUE = "true";
    private static final String PROPERTY_FALSE = "false";
    private static final String ERROR_DRIVER_LOADING = "Driver loading error";
    @Value("${jdbc.className}")
    private String className;
    @Value("${jdbc.urlForDB}")
    private String url;
    @Value("${jdbc.user}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Message.class);
            Properties property = configuration.getProperties();
            property.setProperty(PROPERTY_SECOND_LEVEL_CACHE, PROPERTY_TRUE);
            property.setProperty(PROPERTY_QUERY_CACHE, PROPERTY_TRUE);
            property.setProperty(PROPERTY_CACHE_REGION_FACTORY_CLASS, PROPERTY_CACHE_EH_CACHE_REGION_FACTORY);


            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changeLog-master.yaml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(className);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(ERROR_DRIVER_LOADING, e);
        }
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(userName);
        comboPooledDataSource.setPassword(password);
        return comboPooledDataSource;
    }

 /*   private static final String PACKAGE_SCAN = "com.innowisegroup.messenger.model";



    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        return hibernateProperties;
    }


//

    @Bean
    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        BasicDataSource dataSource = new BasicDataSource();
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(className);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(url);
        dataSource.setUser(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }


  */
}
