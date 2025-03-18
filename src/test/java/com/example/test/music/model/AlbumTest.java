package com.example.test.music.model;

import com.example.music.model.Album;
import com.example.music.model.Artista;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link Album}.
 * Verifico i costruttori, i getter/setter, i metodi equals/hashCode e toString della classe Album.
 */
public class AlbumTest {

    /**
     * Testo il costruttore predefinito di {@link Album}.
     * Mi assicuro che tutti i campi dell'oggetto Album creato siano null.
     */
    @Test
    void testCostruttorePredefinito() {
        Album album = new Album();
        assertNull(album.getId_album());
        assertNull(album.getNome_album());
        assertNull(album.getGenere());
        assertNull(album.getData_uscita());
        assertNull(album.getData_inserimento());
        assertNull(album.getData_aggiornamento());
        assertNull(album.getArtista());
    }

    /**
     * Testo il costruttore parametrico di {@link Album}.
     * Mi assicuro che i campi dell'oggetto Album creato siano inizializzati correttamente con i valori forniti.
     */
    @Test
    void testCostruttoreParametrico() {
        Artista artista = new Artista();
        LocalDate dataUscita = LocalDate.now();
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        Album album = new Album(1, "Test Album", "Test Genre", dataUscita, dataInserimento, dataAggiornamento, artista);
        assertEquals(1, album.getId_album());
        assertEquals("Test Album", album.getNome_album());
        assertEquals("Test Genre", album.getGenere());
        assertEquals(dataUscita, album.getData_uscita());
        assertEquals(dataInserimento, album.getData_inserimento());
        assertEquals(dataAggiornamento, album.getData_aggiornamento());
        assertEquals(artista, album.getArtista());
    }

    /**
     * Testo i getter e i setter di {@link Album}.
     * Mi assicuro che i valori dei campi possano essere impostati e recuperati correttamente.
     */
    @Test
    void testGettersAndSetters() {
        Album album = new Album();
        Artista artista = new Artista();
        LocalDate dataUscita = LocalDate.now();
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        album.setId_album(1);
        album.setNome_album("Test Album");
        album.setGenere("Test Genre");
        album.setData_uscita(dataUscita);
        album.setData_inserimento(dataInserimento);
        album.setData_aggiornamento(dataAggiornamento);
        album.setArtista(artista);
        assertEquals(1, album.getId_album());
        assertEquals("Test Album", album.getNome_album());
        assertEquals("Test Genre", album.getGenere());
        assertEquals(dataUscita, album.getData_uscita());
        assertEquals(dataInserimento, album.getData_inserimento());
        assertEquals(dataAggiornamento, album.getData_aggiornamento());
        assertEquals(artista, album.getArtista());
    }

    /**
     * Testo i metodi equals e hashCode di {@link Album}.
     * Mi assicuro che due oggetti Album con lo stesso ID siano considerati uguali, anche se altri campi sono diversi.
     * Mi assicuro che due oggetti Album con ID diversi non siano considerati uguali.
     * Mi assicuro che gli hashCode di due oggetti Album uguali siano uguali.
     * Mi assicuro che gli hashCode di due oggetti Album diversi siano diversi.
     */
    @Test
    void testEqualsAndHashCode() {
        Album album1 = new Album(1, "Test Album", "Test Genre", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), new Artista());
        Album album2 = new Album(1, "Different Album", "Different Genre", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), new Artista());
        Album album3 = new Album(2, "Test Album", "Test Genre", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), new Artista());
        assertEquals(album1, album2);
        assertNotEquals(album1, album3);
        assertEquals(album1.hashCode(), album2.hashCode());
        assertNotEquals(album1.hashCode(), album3.hashCode());
    }

    /**
     * Testo il metodo toString di {@link Album}.
     * Mi assicuro che la stringa restituita contenga il nome e il genere dell'album.
     */
    @Test
    void testToString() {
        Album album = new Album(1, "Test Album", "Test Genre", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), new Artista());
        String toString = album.toString();
        assertTrue(toString.contains("Test Album"));
        assertTrue(toString.contains("Test Genre"));
    }
}