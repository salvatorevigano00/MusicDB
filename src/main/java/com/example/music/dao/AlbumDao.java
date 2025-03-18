package com.example.music.dao;

import java.sql.SQLException;
import java.util.Set;

import com.example.music.model.Album;

/**
 * Interfaccia per l'accesso ai dati dell'entità Album.
 * Definisco i metodi per eseguire operazioni CRUD (Create, Read, Update, Delete)
 * sugli oggetti Album nel database.
 *
 * Utilizzo questa interfaccia per separare la logica di accesso ai dati
 * dalla logica di business, promuovendo un'architettura a livelli.
 */
public interface AlbumDao {
    /**
     * Salvo un nuovo album nel database.
     *
     * @param album L'oggetto Album da salvare.
     * @return L'oggetto Album salvato, con l'ID generato dal database.
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio.
     */
    Album save(Album album) throws SQLException;

    /**
     * Trovo un album nel database tramite il suo ID.
     *
     * @param album_id L'ID dell'album da trovare.
     * @return L'oggetto Album corrispondente all'ID specificato, o null se non trovato.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Album findById(Integer album_id) throws SQLException;

    /**
     * Aggiorno un album esistente nel database.
     *
     * @param album_id L'ID dell'album da aggiornare.
     * @param album L'oggetto Album con i nuovi dati.
     * @return L'oggetto Album aggiornato.
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    Album update(Integer album_id, Album album) throws SQLException;

    /**
     * Cancello un album dal database tramite il suo ID.
     *
     * @param album_id L'ID dell'album da cancellare.
     * @return L'oggetto Album cancellato.
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    Album deleteById(Integer album_id) throws SQLException;

    /**
     * Trovo tutti gli album nel database.
     *
     * @return Un insieme (Set) di oggetti Album presenti nel database.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Set<Album> findAll() throws SQLException;
}