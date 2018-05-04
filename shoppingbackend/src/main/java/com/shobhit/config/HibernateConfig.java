package com.shobhit.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.shobhit.dto"})
@EnableTransactionManagement
public class HibernateConfig {

    //set values according to the Database
    private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
    private static final String DATABASE_DRIVER = "org.h2.Driver";
    private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
    private static final String DATABASE_USERNAME = "sa";
    private static final String DATABASE_PASSWORD = "";

    @Bean("dataSource")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        //Providing the database connection
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);
        return dataSource;
    }

    //sessionFactory will be available
    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperty());
        builder.scanPackages("com.shobhit.dto");
        return builder.buildSessionFactory();
    }

    //All hibernate properties will return in this method
    private Properties getHibernateProperty() {

        Properties properties = new Properties();
        properties.put("hibernate.dialect",DATABASE_DIALECT);
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");

        return properties;
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        return hibernateTransactionManager;
    }

}
