package com.example.test.music.model;

import com.example.music.model.Artista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Classe di test per {@link Artista}.
 * Verifico i costruttori, i getter/setter, i metodi equals/hashCode e toString della classe Artista.
 */
public class ArtistaTest {

    /**
     * Testo il costruttore predefinito di {@link Artista}.
     * Mi assicuro che tutti i campi dell'oggetto Artista creato siano null.
     */
    @Test
    void testCostruttorePredefinito() {
        Artista artista = new Artista();
        assertNull(artista.getId_artista());
        assertNull(artista.getAnno_inizio());
        assertNull(artista.getNome());
        assertNull(artista.getNazione());
        assertNull(artista.getData_inserimento());
        assertNull(artista.getData_aggiornamento());
    }

    /**
     * Testo il costruttore parametrico di {@link Artista}.
     * Mi assicuro che i campi dell'oggetto Artista creato siano inizializzati correttamente con i valori forniti.
     */
    @Test
    void testCostruttoreParametrico() {
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        Artista artista = new Artista(1, 1980, "Artista di Prova", "Nazione di Prova", dataInserimento, dataAggiornamento);
        assertEquals(1, artista.getId_artista());
        assertEquals(1980, artista.getAnno_inizio());
        assertEquals("Artista di Prova", artista.getNome());
        assertEquals("Nazione di Prova", artista.getNazione());
        assertEquals(dataInserimento, artista.getData_inserimento());
        assertEquals(dataAggiornamento, artista.getData_aggiornamento());
    }

    /**
     * Testo i getter e i setter di {@link Artista}.
     * Mi assicuro che i valori dei campi possano essere impostati e recuperati correttamente.
     */
    @Test
    void testGetterESetter() {
        Artista artista = new Artista();
        LocalDateTime dataInserimento = LocalDateTime.now();
        LocalDateTime dataAggiornamento = LocalDateTime.now();
        artista.setId_artista(1);
        artista.setAnno_inizio(1980);
        artista.setNome("Artista di Prova");
        artista.setNazione("Nazione di Prova");
        artista.setData_inserimento(dataInserimento);
        artista.setData_aggiornamento(dataAggiornamento);
        assertEquals(1, artista.getId_artista());
        assertEquals(1980, artista.getAnno_inizio());
        assertEquals("Artista di Prova", artista.getNome());
        assertEquals("Nazione di Prova", artista.getNazione());
        assertEquals(dataInserimento, artista.getData_inserimento());
        assertEquals(dataAggiornamento, artista.getData_aggiornamento());
    }

    /**
     * Testo i metodi equals e hashCode di {@link Artista}.
     * Mi assicuro che due oggetti Artista con lo stesso ID siano considerati uguali, anche se altri campi sono diversi.
     * Mi assicuro che due oggetti Artista con ID diversi non siano considerati uguali.
     * Mi assicuro che gli hashCode di due oggetti Artista uguali siano uguali.
     * Mi assicuro che gli hashCode di due oggetti Artista diversi siano diversi.
     */
    @Test
    void testEqualsEHashCode() {
        Artista artista1 = new Artista(1, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), LocalDateTime.now());
        Artista artista2 = new Artista(1, 1990, "Artista Diverso", "Nazione Diversa", LocalDateTime.now(), LocalDateTime.now());
        Artista artista3 = new Artista(2, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), LocalDateTime.now());
        assertEquals(artista1, artista2);
        assertNotEquals(artista1, artista3);
        assertEquals(artista1.hashCode(), artista2.hashCode());
        assertNotEquals(artista1.hashCode(), artista3.hashCode());
    }

    /**
     * Testo il metodo toString di {@link Artista}.
     * Mi assicuro che la stringa restituita contenga il nome e la nazione dell'artista.
     */
    @Test
    void testToString() {
        Artista artista = new Artista(1, 1980, "Artista di Prova", "Nazione di Prova", LocalDateTime.now(), LocalDateTime.now());
        String toString = artista.toString();
        assertTrue(toString.contains("Artista di Prova"));
        assertTrue(toString.contains("Nazione di Prova"));
    }
}