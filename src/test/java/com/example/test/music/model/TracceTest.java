package com.example.test.music.model;

import com.example.music.model.Album;
import com.example.music.model.Artista;
import com.example.music.model.Tracce;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Classe di test per {@link Tracce}.
 * Verifico i costruttori, i getter/setter, i metodi equals/hashCode e toString della classe Tracce.
 */
public class TracceTest {

    /**
     * Testo il costruttore predefinito di {@link Tracce}.
     * Mi assicuro che tutti i campi dell'oggetto Tracce creato siano null.
     */
    @Test
    void testCostruttorePredefinito() {
        Tracce traccia = new Tracce();
        assertNull(traccia.getId_traccia());
        assertNull(traccia.getNome_traccia());
        assertNull(traccia.getAlbum());
        assertNull(traccia.getArtista());
        assertNull(traccia.getData_inserimento());
        assertNull(traccia.getData_aggiornamento());
    }

    /**
     * Testo il costruttore parametrico di {@link Tracce}.
     * Mi assicuro che i campi dell'oggetto Tracce creato siano inizializzati correttamente con i valori forniti.
     */
    @Test
    void testCostruttoreParametrico() {
        Album album = new Album();
        Artista artista = new Artista();
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        Tracce traccia = new Tracce(1, "Traccia di Prova", album, artista, dataInserimento, dataAggiornamento);
        assertEquals(1, traccia.getId_traccia());
        assertEquals("Traccia di Prova", traccia.getNome_traccia());
        assertEquals(album, traccia.getAlbum());
        assertEquals(artista, traccia.getArtista());
        assertEquals(dataInserimento, traccia.getData_inserimento());
        assertEquals(dataAggiornamento, traccia.getData_aggiornamento());
    }

    /**
     * Testo i getter e i setter di {@link Tracce}.
     * Mi assicuro che i valori dei campi possano essere impostati e recuperati correttamente.
     */
    @Test
    void testGetterESetter() {
        Tracce traccia = new Tracce();
        Album album = new Album();
        Artista artista = new Artista();
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        traccia.setId_traccia(1);
        traccia.setNome_traccia("Traccia di Prova");
        traccia.setAlbum(album);
        traccia.setArtista(artista);
        traccia.setData_inserimento(dataInserimento);
        traccia.setData_aggiornamento(dataAggiornamento);
        assertEquals(1, traccia.getId_traccia());
        assertEquals("Traccia di Prova", traccia.getNome_traccia());
        assertEquals(album, traccia.getAlbum());
        assertEquals(artista, traccia.getArtista());
        assertEquals(dataInserimento, traccia.getData_inserimento());
        assertEquals(dataAggiornamento, traccia.getData_aggiornamento());
    }

    /**
     * Testo i metodi equals e hashCode di {@link Tracce}.
     * Mi assicuro che due oggetti Tracce con lo stesso ID siano considerati uguali, anche se altri campi sono diversi.
     * Mi assicuro che due oggetti Tracce con ID diversi non siano considerati uguali.
     * Mi assicuro che gli hashCode di due oggetti Tracce uguali siano uguali.
     * Mi assicuro che gli hashCode di due oggetti Tracce diversi siano diversi.
     */
    @Test
    void testEqualsEHashCode() {
        Tracce traccia1 = new Tracce(1, "Traccia di Prova", new Album(), new Artista(), LocalDateTime.now(), LocalDateTime.now());
        Tracce traccia2 = new Tracce(1, "Traccia Diversa", new Album(), new Artista(), LocalDateTime.now(), LocalDateTime.now());
        Tracce traccia3 = new Tracce(2, "Traccia di Prova", new Album(), new Artista(), LocalDateTime.now(), LocalDateTime.now());
        assertEquals(traccia1, traccia2);
        assertNotEquals(traccia1, traccia3);
        assertEquals(traccia1.hashCode(), traccia2.hashCode());
        assertNotEquals(traccia1.hashCode(), traccia3.hashCode());
    }

    /**
     * Testo il metodo toString di {@link Tracce}.
     * Mi assicuro che la stringa restituita contenga il nome della traccia.
     */
    @Test
    void testToString() {
        Tracce traccia = new Tracce(1, "Traccia di Prova", new Album(), new Artista(), LocalDateTime.now(), LocalDateTime.now()); // Assicuro che Album possa essere istanziato o simulo un Album
        String toString = traccia.toString();
        assertTrue(toString.contains("Traccia di Prova"));
    }
}