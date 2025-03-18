package com.example.test.music.dao;

import com.example.music.dao.TracceDaoImpl;
import com.example.music.model.Album;
import com.example.music.model.Artista;
import com.example.music.model.Tracce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Classe di test per {@link TracceDaoImpl}.
 * 
 * Verifico le operazioni CRUD (Create, Read, Update, Delete) sugli oggetti Tracce.
 */
public class TracceDaoImplTest {

    private TracceDaoImpl tracceDao;
    private Album albumTest;
    private Artista artistaTest;

    /**
     * Configuro l'ambiente di test prima di ogni metodo di test.
     * Inizializzo l'oggetto {@link TracceDaoImpl}, l'oggetto {@link Album} di test e l'oggetto {@link Artista} di test.
     * Mi assicuro che l'album e l'artista di test esistano nel database.
     *
     * @throws SQLException Se si verifica un errore durante la configurazione.
     */
    @BeforeEach
    void setUp() throws SQLException {
        tracceDao = new TracceDaoImpl();
        albumTest = new Album(1,"albumTest","genereTest",java.time.LocalDate.now(),LocalDateTime.now(),LocalDateTime.now(),new Artista());
        artistaTest = new Artista(1, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * Testo il salvataggio di una traccia e la successiva ricerca per ID.
     * Mi assicuro che la traccia venga salvata correttamente e che possa essere recuperata tramite il suo ID.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio o ricerca.
     */
    @Test
    void testSaveAndFindById() throws SQLException {
        Tracce traccia = new Tracce(null, "Traccia di Prova", albumTest, artistaTest, LocalDateTime.now(), null);
        Tracce tracciaSalvata = tracceDao.save(traccia);
        assertNotNull(tracciaSalvata.getId_traccia());
        Tracce tracciaTrovata = tracceDao.findById(tracciaSalvata.getId_traccia());
        assertEquals(tracciaSalvata.getNome_traccia(), tracciaTrovata.getNome_traccia());
    }

    /**
     * Testo l'aggiornamento di una traccia esistente.
     * Mi assicuro che la traccia venga aggiornata correttamente nel database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    @Test
    void testUpdate() throws SQLException {
        Tracce traccia = new Tracce(null, "Traccia di Prova", albumTest, artistaTest, LocalDateTime.now(), null);
        Tracce tracciaSalvata = tracceDao.save(traccia);
        tracciaSalvata.setNome_traccia("Traccia Aggiornata");
        Tracce tracciaAggiornata = tracceDao.update(tracciaSalvata.getId_traccia(), tracciaSalvata);
        assertEquals("Traccia Aggiornata", tracciaAggiornata.getNome_traccia());
    }

    /**
     * Testo la cancellazione di una traccia tramite il suo ID.
     * Mi assicuro che la traccia venga cancellata correttamente dal database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    @Test
    void testDeleteById() throws SQLException {
        Tracce traccia = new Tracce(null, "Traccia di Prova", albumTest, artistaTest, LocalDateTime.now(), null);
        Tracce tracciaSalvata = tracceDao.save(traccia);
        tracceDao.deleteById(tracciaSalvata.getId_traccia());
        Tracce tracciaTrovata = tracceDao.findById(tracciaSalvata.getId_traccia());
        assertNull(tracciaTrovata);
    }

    /**
     * Testo il recupero di tutte le tracce dal database.
     * Mi assicuro che la lista di tracce restituita non sia vuota.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di recupero.
     */
    @Test
    void testFindAll() throws SQLException {
        Set<Tracce> tracce = tracceDao.findAll();
        assertNotNull(tracce);
        assertFalse(tracce.isEmpty());
    }
}