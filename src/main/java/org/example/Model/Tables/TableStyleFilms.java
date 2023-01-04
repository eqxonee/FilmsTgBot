package org.example.Model.Tables;

import org.example.Model.Entities.StyleFilm;

import java.sql.SQLException;
import java.util.List;

public interface TableStyleFilms {

    List<StyleFilm> getAll() throws Exception;
    StyleFilm getByName(String name) throws SQLException;

}
