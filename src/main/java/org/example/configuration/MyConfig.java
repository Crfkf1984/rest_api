package org.example.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan("org.example")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource combo = new ComboPooledDataSource();
        try {
            combo.setDriverClass("com.mysql.cj.jdbc.Driver");
            combo.setJdbcUrl("jdbc:mysql://localhost:3306/springmvc?useSSL=false&serverTimezone=UTC");
            combo.setUser("root");
            combo.setPassword("Crfkf1984!");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return combo;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean local = new LocalSessionFactoryBean();
        local.setDataSource(dataSource());
        local.setPackagesToScan("org.example.entity");
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        prop.setProperty("hibernate.show_sql","true");
        local.setHibernateProperties(prop);
        return local;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transaction = new HibernateTransactionManager();
        transaction.setSessionFactory(sessionFactory().getObject());
        return transaction;
    }
}
