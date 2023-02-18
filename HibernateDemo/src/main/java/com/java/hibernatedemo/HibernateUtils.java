/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.java.hibernatedemo;

import com.java.pojo.Category;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author admin
 */
public class HibernateUtils {

    private static final SessionFactory factory;

    static {
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Category.class);
        ServiceRegistry service = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();

        factory = conf.buildSessionFactory(service);
    }

    /**
     * @return the factory
     */
    public static SessionFactory getFactory() {
        return factory;
    }

}
