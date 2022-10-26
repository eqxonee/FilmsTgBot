package org.example.Model.Tables;

import org.example.Model.Entities.StyleFilm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableStyleFilms {
    private Connection connection;

    public TableStyleFilms(Connection connection) {
        this.connection = connection;
    }

    public List<StyleFilm> getAll() throws SQLException {
        List<StyleFilm> styleFilms = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM style_films ORDER BY id ASC");

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String styleFilm = resultSet.getString("styleFilm");

            styleFilms.add(new StyleFilm(id, styleFilm));
        }
        resultSet.close();

        statement.close();

        return styleFilms;
    }
}
