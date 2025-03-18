package com.example.music.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe MySQLConnection gestisce la connessione al database MySQL.
 * Fornisco un metodo statico per aprire una connessione al database 'music' locale.
 * Utilizzo le costanti per l'URL, l'username e la password del database.
 */
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hollow1710!";

    /**
     * Apro una connessione al database MySQL utilizzando l'URL, l'username e la password forniti.
     *
     * @return Una connessione al database MySQL.
     * @throws SQLException Se si verifica un errore durante l'apertura della connessione.
     */
    public static Connection open() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}