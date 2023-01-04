package org.example.Model.Tables;

import org.example.Model.Entities.StyleFilm;
import org.hibernate.SessionFactory;

import java.util.List;

public class TableStyleFilmsHiberImpl implements TableStyleFilms{

    private SessionFactory sessionFactory;

    public TableStyleFilmsHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<StyleFilm> getAll() throws Exception {
        return (List<StyleFilm>) sessionFactory.openSession().createQuery("FROM StyleFilm ORDER BY id").list();
    }

    @Override
    public StyleFilm getByName(String styleFilmName) {
        return sessionFactory.openSession().get(StyleFilm.class, styleFilmName);
    }
}
