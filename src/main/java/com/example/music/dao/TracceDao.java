package com.example.music.dao;

import java.sql.SQLException;
import java.util.Set;

import com.example.music.model.Tracce;

/**
 * Interfaccia per l'accesso ai dati dell'entità Tracce.
 * Definisco i metodi per eseguire operazioni CRUD (Create, Read, Update, Delete)
 * sugli oggetti Tracce nel database.
 *
 * Utilizzo questa interfaccia per separare la logica di accesso ai dati
 * dalla logica di business, promuovendo un'architettura a livelli.
 */
public interface TracceDao {
    /**
     * Salvo una nuova traccia nel database.
     *
     * @param traccia L'oggetto Tracce da salvare.
     * @return L'oggetto Tracce salvato, con l'ID generato dal database.
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio.
     */
    Tracce save(Tracce traccia) throws SQLException;

    /**
     * Trovo una traccia nel database tramite il suo ID.
     *
     * @param id_traccia L'ID della traccia da trovare.
     * @return L'oggetto Tracce corrispondente all'ID specificato, o null se non trovato.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Tracce findById(Integer id_traccia) throws SQLException;

    /**
     * Aggiorno una traccia esistente nel database.
     *
     * @param id_traccia L'ID della traccia da aggiornare.
     * @param traccia L'oggetto Tracce con i nuovi dati.
     * @return L'oggetto Tracce aggiornato.
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    Tracce update(Integer id_traccia, Tracce traccia) throws SQLException;

    /**
     * Cancello una traccia dal database tramite il suo ID.
     *
     * @param id_traccia L'ID della traccia da cancellare.
     * @return L'oggetto Tracce cancellato.
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    Tracce deleteById(Integer id_traccia) throws SQLException;

    /**
     * Trovo tutte le tracce nel database.
     *
     * @return Un insieme (Set) di oggetti Tracce presenti nel database.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    Set<Tracce> findAll() throws SQLException;
}