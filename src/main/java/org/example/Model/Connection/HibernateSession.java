package org.example.Model.Connection;

import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSession {

    private static HibernateSession instance = null;

    private SessionFactory sessionFactory;

    private HibernateSession() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Film.class);
            configuration.addAnnotatedClass(StyleFilm.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.println("HibernateSession init error:" + e);
        }
    }

    public static HibernateSession getInstance() {
        if (instance == null) {
            instance = new HibernateSession();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
