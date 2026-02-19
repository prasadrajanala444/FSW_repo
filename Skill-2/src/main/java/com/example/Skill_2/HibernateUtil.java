package com.example.Skill_2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Load configuration & build session factory
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Hibernate SessionFactory creation failed!");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
