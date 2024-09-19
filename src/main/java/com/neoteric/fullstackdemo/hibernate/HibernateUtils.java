package com.neoteric.fullstackdemo.hibernate;

import com.neoteric.fullstackdemo.model.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory (){
        if(sessionFactory==null){

            Configuration configuration=new Configuration();

            Properties properties=new Properties();

            properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL,"jdbc:mysql://localhost:3306/bank");
            properties.put(Environment.USER,"root");
            properties.put(Environment.PASS,"root");
            properties.put(Environment.DIALECT,"org.hibernate.dailect.MySQLDialect");
            properties.put(Environment.SHOW_SQL,true);
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(AccountEntity.class);

            // ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ServiceRegistry registry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(registry);
        }
        return sessionFactory;
    }
}