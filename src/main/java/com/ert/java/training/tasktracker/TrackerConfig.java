package com.ert.java.training.tasktracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ert.java.training.tasktracker")
public class TrackerConfig {

    @Bean(name = "sessionFactory")
    public SessionFactory SessionFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return factory;
    }

}
