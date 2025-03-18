package com.example.music.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe che rappresenta la tabella "artisti" nel database.
 *
 * Questa classe utilizza la convenzione POJO (Plain Old Java Object),
 * che è una convenzione Java Beans. Un Beans deve avere almeno:
 * - Un costruttore di default
 * - I metodi getter e setter per ciascun campo
 *
 * Viene utilizzata nel contesto di ORM (Object-Relational Mapping),
 * che mappa questa classe ai dati della tabella "artisti".
 *
 * Le proprietà principali della classe sono:
 * - id_artista: identificativo univoco dell'artista.
 * - nome: nome dell'artista.
 * - nazione: nazione di origine dell'artista.
 * - anno_inizio: anno di inizio carriera dell'artista.
 * - data_inserimento: data e ora di inserimento del record.
 * - data_aggiornamento: data e ora dell'ultimo aggiornamento del record.
 *
 */

public class Artista {
    private Integer id_artista, anno_inizio;
    private String nome, nazione;
    private LocalDateTime data_inserimento, data_aggiornamento;

    /**
     * Costruttore vuoto per la classe Artista.
     */
    public Artista() {}

    /**
     * Costruttore completo per la classe Artista.
     *
     * @param id_artista L'identificativo univoco dell'artista.
     * @param anno_inizio L'anno di inizio carriera dell'artista.
     * @param nome Il nome dell'artista.
     * @param nazione La nazione di origine dell'artista.
     * @param data_inserimento La data e ora di inserimento del record.
     * @param data_aggiornamento La data e ora dell'ultimo aggiornamento del record.
     */
    public Artista(Integer id_artista, Integer anno_inizio, String nome, String nazione, LocalDateTime data_inserimento,
                   LocalDateTime data_aggiornamento) {
        this.id_artista = id_artista;
        this.anno_inizio = anno_inizio;
        this.nome = nome;
        this.nazione = nazione;
        this.data_inserimento = data_inserimento;
        this.data_aggiornamento = data_aggiornamento;
    }

    /**
     * Ottengo l'identificativo univoco dell'artista.
     *
     * @return L'identificativo univoco dell'artista.
     */
    public Integer getId_artista() {
        return id_artista;
    }

    /**
     * Imposto l'identificativo univoco dell'artista.
     *
     * @param id_artista L'identificativo univoco dell'artista.
     */
    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }

    /**
     * Ottengo l'anno di inizio carriera dell'artista.
     *
     * @return L'anno di inizio carriera dell'artista.
     */
    public Integer getAnno_inizio() {
        return anno_inizio;
    }

    /**
     * Imposto l'anno di inizio carriera dell'artista.
     *
     * @param anno_inizio L'anno di inizio carriera dell'artista.
     */
    public void setAnno_inizio(Integer anno_inizio) {
        this.anno_inizio = anno_inizio;
    }

    /**
     * Ottengo il nome dell'artista.
     *
     * @return Il nome dell'artista.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposto il nome dell'artista.
     *
     * @param nome Il nome dell'artista.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ottengo la nazione di origine dell'artista.
     *
     * @return La nazione di origine dell'artista.
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Imposto la nazione di origine dell'artista.
     *
     * @param nazione La nazione di origine dell'artista.
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
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
     * Calcolo l'hash code dell'artista basandomi sull'identificativo univoco.
     *
     * @return L'hash code dell'artista.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_artista);
    }

    /**
     * Confronto due oggetti Artista per verificare se sono uguali.
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
        Artista other = (Artista) obj;
        return Objects.equals(id_artista, other.id_artista);
    }

    /**
     * Restituisco una rappresentazione in formato stringa dell'artista.
     *
     * @return Una rappresentazione in formato stringa dell'artista.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Artista [id_artista=");
        builder.append(id_artista);
        builder.append(", anno_inizio=");
        builder.append(anno_inizio);
        builder.append(", nome=");
        builder.append(nome);
        builder.append(", nazione=");
        builder.append(nazione);
        builder.append(", data_inserimento=");
        builder.append(data_inserimento);
        builder.append(", data_aggiornamento=");
        builder.append(data_aggiornamento);
        builder.append("]");
        return builder.toString();
    }
}