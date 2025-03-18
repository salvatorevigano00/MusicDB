package com.example.test.music.dao;

import com.example.music.dao.AlbumDaoImpl;
import com.example.music.model.Album;
import com.example.music.model.Artista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Classe di test per {@link AlbumDaoImpl}.
 * Verifico le operazioni CRUD (Create, Read, Update, Delete) sugli oggetti Album.
 */
public class AlbumDaoImplTest {

    private AlbumDaoImpl albumDao;
    private Artista artistaTest;

    /**
     * Configuro l'ambiente di test prima di ogni metodo di test.
     * Inizializzo l'oggetto {@link AlbumDaoImpl} e l'oggetto {@link Artista} di test.
     * Mi assicuro che l'artista di test esista nel database.
     *
     * @throws SQLException Se si verifica un errore durante la configurazione.
     */
    @BeforeEach
    void setUp() throws SQLException {
        albumDao = new AlbumDaoImpl();
        // Assicuro che questo artista esista nel db o creo un metodo per crearlo.
        artistaTest = new Artista(1, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * Testo il salvataggio di un album e la successiva ricerca per ID.
     * Mi assicuro che l'album venga salvato correttamente e che possa essere recuperato tramite il suo ID.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio o ricerca.
     */
    @Test
    void testSaveAndFindById() throws SQLException {
        Album album = new Album(null, "Album di Prova", "ROCK", LocalDate.now(), LocalDateTime.now(), null, artistaTest);
        Album albumSalvato = albumDao.save(album);
        assertNotNull(albumSalvato.getId_album());
        Album albumTrovato = albumDao.findById(albumSalvato.getId_album());
        assertEquals(albumSalvato.getNome_album(), albumTrovato.getNome_album());
    }

    /**
     * Testo l'aggiornamento di un album esistente.
     * Mi assicuro che l'album venga aggiornato correttamente nel database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    @Test
    void testUpdate() throws SQLException {
        Album album = new Album(null, "Album di Prova", "POP", LocalDate.now(), LocalDateTime.now(), null, artistaTest);
        Album albumSalvato = albumDao.save(album);
        albumSalvato.setNome_album("Album Aggiornato");
        Album albumAggiornato = albumDao.update(albumSalvato.getId_album(), albumSalvato);
        assertEquals("Album Aggiornato", albumAggiornato.getNome_album());
    }

    /**
     * Testo la cancellazione di un album tramite il suo ID.
     * Mi assicuro che l'album venga cancellato correttamente dal database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    @Test
    void testDeleteById() throws SQLException {
        Album album = new Album(null, "Album di Prova", "BLUES", LocalDate.now(), LocalDateTime.now(), null, artistaTest);
        Album albumSalvato = albumDao.save(album);
        albumDao.deleteById(albumSalvato.getId_album());
        Album albumTrovato = albumDao.findById(albumSalvato.getId_album());
        assertNull(albumTrovato);
    }

    /**
     * Testo il recupero di tutti gli album dal database.
     * Mi assicuro che la lista di album restituita non sia vuota.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di recupero.
     */
    @Test
    void testFindAll() throws SQLException {
        Set<Album> albums = albumDao.findAll();
        assertNotNull(albums);
        assertFalse(albums.isEmpty());
    }
}