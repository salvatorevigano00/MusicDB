package com.example.test.music.config;

import org.junit.jupiter.api.Test;

import com.example.music.config.MySQLConnection;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Classe di test per {@link MySQLConnection}.
 * Verifico la corretta apertura e chiusura delle connessioni al database MySQL.
 */
public class MySQLConnectionTest {

    /**
     * Testo l'apertura di una connessione al database con successo.
     * Mi assicuro che la connessione venga aperta correttamente e non sia chiusa.
     * Chiudo la connessione dopo il test per rilasciare le risorse.
     *
     * @throws SQLException Se si verifica un errore durante l'apertura della connessione.
     */
    @Test
    void testOpenConnectionSuccess() throws SQLException {
        Connection connection = MySQLConnection.open();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
        connection.close();
    }

    /**
     * Testo la chiusura di una connessione al database con successo.
     * Mi assicuro che la connessione venga chiusa correttamente.
     *
     * @throws SQLException Se si verifica un errore durante la chiusura della connessione.
     */
    @Test
    void testCloseConnectionSuccess() throws SQLException {
        Connection connection = MySQLConnection.open();
        connection.close();
        assertTrue(connection.isClosed());
    }

    /**
     * Testo il fallimento dell'apertura di una connessione con credenziali errate.
     * Creo una classe di connessione fittizia, `FakeConnection`, con credenziali errate.
     * Mi assicuro che venga lanciata una {@link SQLException} quando si tenta di aprire la connessione
     * con credenziali errate.
     */
    @Test
    void testOpenConnectionFailureWrongCredentials() {
        class FakeConnection {
            private static final String URL = "jdbc:mysql://localhost:3306/music";
            private static final String USERNAME = "wrong_user";
            private static final String PASSWORD = "wrong_password";

            public static Connection open() throws SQLException {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        }

        assertThrows(SQLException.class, FakeConnection::open);
    }
}