package org.example.Model.Tables;

import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TableFilmsHiberImpl implements TableFilms{

    private SessionFactory sessionFactory;

    public TableFilmsHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addNew(Film film) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(film);

        transaction.commit();
        session.close();
    }

    @Override
    public Film getFilmByName(String name) throws Exception {
        return sessionFactory.openSession().get(Film.class, name);
    }

    @Override
    public void deleteByFilmName(String name) throws Exception {

        Film film = Film.builder().name(name).build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(film);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Film> getAllHorror() throws Exception {
        int styleId = 1;
        return (List<Film>)sessionFactory.openSession().createQuery("FROM Film f ORDER BY f.id").list();
    }

    @Override
    public List<Film> getAllMystic() throws Exception {
        int styleId = 2;
        return (List<Film>) sessionFactory.openSession().get(Film.class,styleId);
    }

    @Override
    public List<Film> getAllHistory() throws Exception {
        int styleId = 3;
        return (List<Film>) sessionFactory.openSession().get(Film.class,styleId);
    }

    @Override
    public List<Film> getAllComedy() throws Exception {
        int styleId = 4;
        return (List<Film>) sessionFactory.openSession().get(Film.class,styleId);
    }

    @Override
    public List<Film> getAllMilitary() throws Exception {
        int styleId = 5;
        return (List<Film>) sessionFactory.openSession().get(Film.class,styleId);
    }
}
