package com.example.music.dao;

import java.sql.SQLException;
import java.util.Set;

import com.example.music.model.Artista;

/**
 * Interfaccia per l'accesso ai dati dell'entità Artista.
 * Definisco i metodi per eseguire operazioni CRUD (Create, Read, Update, Delete)
 * sugli oggetti Artista nel database.
 *
 * Utilizzo questa interfaccia per separare la logica di accesso ai dati
 * dalla logica di business, promuovendo un'architettura a livelli.
 */
public interface ArtistaDao {
    /**
     * Salvo un nuovo artista nel database.
     *
     * @param artista L'oggetto Artista da salvare.
     * @return L'oggetto Artista salvato, con l'ID generato dal database.
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio.
     */
    Artista save(Artista artista) throws SQLException;

    /**
     * Trovo un artista nel database tramite il suo ID.
     *
     * @param id_artista L'ID dell'artista da trovare.
     * @return L'oggetto Artista corrispondente all'ID specificato, o null se non trovato.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Artista findById(Integer id_artista) throws SQLException;

    /**
     * Aggiorno un artista esistente nel database.
     *
     * @param id_artista L'ID dell'artista da aggiornare.
     * @param artista L'oggetto Artista con i nuovi dati.
     * @return L'oggetto Artista aggiornato.
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    Artista update(Integer id_artista, Artista artista) throws SQLException;

    /**
     * Cancello un artista dal database tramite il suo ID.
     *
     * @param id_artista L'ID dell'artista da cancellare.
     * @return L'oggetto Artista cancellato.
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    Artista deleteById(Integer id_artista) throws SQLException;

    /**
     * Trovo tutti gli artisti nel database.
     *
     * @return Un insieme (Set) di oggetti Artista presenti nel database.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Set<Artista> findAll() throws SQLException;
}