package org.example.Model.Tables;

import org.example.Model.Entities.Film;

import java.util.List;

public interface TableFilms {
    void addNew(Film film) throws Exception;
    Film getFilmByName(String name) throws Exception;
    void deleteByFilmName(String name) throws Exception;
    List<Film> getAllHorror() throws Exception;
    List<Film> getAllMystic() throws Exception;
    List<Film> getAllHistory() throws Exception;
    List<Film> getAllComedy() throws Exception;
    List<Film> getAllMilitary() throws Exception;

}
