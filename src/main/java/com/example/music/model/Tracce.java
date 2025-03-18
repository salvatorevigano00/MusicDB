package com.example.music.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe che rappresenta la tabella "tracce" nel database.
 *
 * Questa classe segue la convenzione POJO (Plain Old Java Object),
 * che è una convenzione Java Beans. Un Bean deve avere almeno:
 * - Un costruttore di default
 * - I metodi getter e setter per ciascun campo
 *
 * Viene utilizzata nel contesto di ORM (Object-Relational Mapping),
 * che mappa questa classe ai dati della tabella "tracce".
 *
 * Le proprietà principali della classe sono:
 * - id_traccia: identificativo univoco della traccia.
 * - nome_traccia: nome della traccia.
 * - album: riferimento all'album a cui appartiene la traccia.
 * - artista: riferimento all'artista che ha realizzato la traccia.
 * - data_inserimento: data e ora di inserimento del record.
 * - data_aggiornamento: data e ora dell'ultimo aggiornamento del record.
 *
 * Questa classe si collega con la tabella "album" e la tabella "artisti"
 * per stabilire le relazioni tra tracce, album e artisti.
 *
 * @see Artista
 */

public class Tracce{
    private Integer id_traccia;
    private String nome_traccia;
    private Album album;
    private Artista artista;
    private LocalDateTime data_inserimento, data_aggiornamento;

    /**
     * Costruttore vuoto per la classe Tracce.
     */
    public Tracce() {

    }

    /**
     * Costruttore completo per la classe Tracce.
     *
     * @param id_traccia L'identificativo univoco della traccia.
     * @param nome_traccia Il nome della traccia.
     * @param album L'album a cui appartiene la traccia.
     * @param artista L'artista che ha realizzato la traccia.
     * @param data_inserimento La data e ora di inserimento del record.
     * @param data_aggiornamento La data e ora dell'ultimo aggiornamento del record.
     */
    public Tracce(Integer id_traccia, String nome_traccia, Album album, Artista artista, LocalDateTime data_inserimento,
                  LocalDateTime data_aggiornamento) {
        super();
        this.id_traccia = id_traccia;
        this.nome_traccia = nome_traccia;
        this.album = album;
        this.artista = artista;
        this.data_inserimento = data_inserimento;
        this.data_aggiornamento = data_aggiornamento;
    }

    /**
     * Ottengo l'identificativo univoco della traccia.
     *
     * @return L'identificativo univoco della traccia.
     */
    public Integer getId_traccia() {
        return id_traccia;
    }

    /**
     * Imposto l'identificativo univoco della traccia.
     *
     * @param id_traccia L'identificativo univoco della traccia.
     */
    public void setId_traccia(Integer id_traccia) {
        this.id_traccia = id_traccia;
    }

    /**
     * Ottengo il nome della traccia.
     *
     * @return Il nome della traccia.
     */
    public String getNome_traccia() {
        return nome_traccia;
    }

    /**
     * Imposto il nome della traccia.
     *
     * @param nome_traccia Il nome della traccia.
     */
    public void setNome_traccia(String nome_traccia) {
        this.nome_traccia = nome_traccia;
    }

    /**
     * Ottengo l'album a cui appartiene la traccia.
     *
     * @return L'album a cui appartiene la traccia.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Imposto l'album a cui appartiene la traccia.
     *
     * @param album L'album a cui appartiene la traccia.
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * Ottengo l'artista che ha realizzato la traccia.
     *
     * @return L'artista che ha realizzato la traccia.
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     * Imposto l'artista che ha realizzato la traccia.
     *
     * @param artista L'artista che ha realizzato la traccia.
     */
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    /**
     * Ottengo la data e ora di inserimento del record.
     *
     * @return La data e ora di inserimento del record.
     */
    public LocalDateTime getData_inserimento() {
        return data_inserimento;
    }

    /**
     * Imposto la data e ora di inserimento del record.
     *
     * @param data_inserimento La data e ora di inserimento del record.
     */
    public void setData_inserimento(LocalDateTime data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    /**
     * Ottengo la data e ora dell'ultimo aggiornamento del record.
     *
     * @return La data e ora dell'ultimo aggiornamento del record.
     */
    public LocalDateTime getData_aggiornamento() {
        return data_aggiornamento;
    }

    /**
     * Imposto la data e ora dell'ultimo aggiornamento del record.
     *
     * @param data_aggiornamento La data e ora dell'ultimo aggiornamento del record.
     */
    public void setData_aggiornamento(LocalDateTime data_aggiornamento) {
        this.data_aggiornamento = data_aggiornamento;
    }

    /**
     * Calcolo l'hash code della traccia basandomi sull'identificativo univoco.
     *
     * @return L'hash code della traccia.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_traccia);
    }

    /**
     * Confronto due oggetti Tracce per verificare se sono uguali.
     *
     * @param obj L'oggetto da confrontare.
     * @return true se gli oggetti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tracce other = (Tracce) obj;
        return Objects.equals(id_traccia, other.id_traccia);
    }

    /**
     * Restituisco una rappresentazione in formato stringa della traccia.
     *
     * @return Una rappresentazione in formato stringa della traccia.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tracce [id_traccia=");
        builder.append(id_traccia);
        builder.append(", nome_traccia=");
        builder.append(nome_traccia);
        builder.append(", album=");
        builder.append(album);
        builder.append(", artista=");
        builder.append(artista);
        builder.append(", data_inserimento=");
        builder.append(data_inserimento);
        builder.append(", data_aggiornamento=");
        builder.append(data_aggiornamento);
        builder.append("]");
        return builder.toString();
    }
}