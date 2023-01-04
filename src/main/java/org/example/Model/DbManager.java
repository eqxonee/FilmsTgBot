package org.example.Model;

import org.example.Model.Tables.TableFilms;
import org.example.Model.Tables.TableFilmsJdbcImpl;
import org.example.Model.Tables.TableStyleFilms;
import org.example.Model.Tables.TableStyleFilmsJdbcImpl;

public class DbManager {

    private TableStyleFilms tableStyleFilms;
    private TableFilms tableFilms;

    public DbManager(TableStyleFilms tableStyleFilms, TableFilms tableFilms) {
        this.tableStyleFilms = tableStyleFilms;
        this.tableFilms = tableFilms;
    }

    public TableStyleFilms getTableStyleFilms() {
        return tableStyleFilms;
    }

    public TableFilms getTableFilms() {
        return tableFilms;
    }
}
