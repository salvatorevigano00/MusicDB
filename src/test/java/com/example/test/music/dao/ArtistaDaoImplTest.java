package com.example.test.music.dao;

import com.example.music.dao.ArtistaDaoImpl;
import com.example.music.model.Artista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Classe di test per {@link ArtistaDaoImpl}.
 * 
 * Verifico le operazioni CRUD (Create, Read, Update, Delete) sugli oggetti Artista.
 */
public class ArtistaDaoImplTest {

    private ArtistaDaoImpl artistaDao;

    /**
     * Configuro l'ambiente di test prima di ogni metodo di test.
     * Inizializzo l'oggetto {@link ArtistaDaoImpl}.
     */
    @BeforeEach
    void setUp() {
        artistaDao = new ArtistaDaoImpl();
    }

    /**
     * Testo il salvataggio di un artista e la successiva ricerca per ID.
     * Mi assicuro che l'artista venga salvato correttamente e che possa essere recuperato tramite il suo ID.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio o ricerca.
     */
    @Test
    void testSaveAndFindById() throws SQLException {
        Artista artista = new Artista(null, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), null);
        Artista artistaSalvato = artistaDao.save(artista);
        assertNotNull(artistaSalvato.getId_artista());
        Artista artistaTrovato = artistaDao.findById(artistaSalvato.getId_artista());
        assertEquals(artistaSalvato.getNome(), artistaTrovato.getNome());
    }

    /**
     * Testo l'aggiornamento di un artista esistente.
     * Mi assicuro che l'artista venga aggiornato correttamente nel database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    @Test
    void testUpdate() throws SQLException {
        Artista artista = new Artista(null, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), null);
        Artista artistaSalvato = artistaDao.save(artista);
        artistaSalvato.setNome("Artista Aggiornato");
        Artista artistaAggiornato = artistaDao.update(artistaSalvato.getId_artista(), artistaSalvato);
        assertEquals("Artista Aggiornato", artistaAggiornato.getNome());
    }

    /**
     * Testo la cancellazione di un artista tramite il suo ID.
     * Mi assicuro che l'artista venga cancellato correttamente dal database.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    @Test
    void testDeleteById() throws SQLException {
        Artista artista = new Artista(null, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), null);
        Artista artistaSalvato = artistaDao.save(artista);
        artistaDao.deleteById(artistaSalvato.getId_artista());
        Artista artistaTrovato = artistaDao.findById(artistaSalvato.getId_artista());
        assertNull(artistaTrovato);
    }

    /**
     * Testo il recupero di tutti gli artisti dal database.
     * Mi assicuro che la lista di artisti restituita non sia vuota.
     *
     * @throws SQLException Se si verifica un errore durante l'operazione di recupero.
     */
    @Test
    void testFindAll() throws SQLException {
        Set<Artista> artisti = artistaDao.findAll();
        assertNotNull(artisti);
        assertFalse(artisti.isEmpty());
    }
}