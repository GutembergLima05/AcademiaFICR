package com.example.projetoacademia.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final String URL = "jdbc:postgresql://babar.db.elephantsql.com:5432/ydrmlyru";

    private static final String USER = "ydrmlyru";

    private static final String PASSWORD = "7S19DzrWUS_QdP6P9G_tFlL-szVTFNUV";


    public static Connection conectarDB() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conn;
    }
}
