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
 * @see Album
 */

public class Artista {
    private Integer id_artista, anno_inizio;
    private String nome, nazione;
    private LocalDateTime data_inserimento, data_aggiornamento;

    public Artista() {}

    public Artista(Integer id_artista, Integer anno_inizio, String nome, String nazione, LocalDateTime data_inserimento,
			LocalDateTime data_aggiornamento) {
		this.id_artista = id_artista;
		this.anno_inizio = anno_inizio;
		this.nome = nome;
		this.nazione = nazione;
		this.data_inserimento = data_inserimento;
		this.data_aggiornamento = data_aggiornamento;
	}

	public Integer getId_artista() {
		return id_artista;
	}

	public void setId_artista(Integer id_artista) {
		this.id_artista = id_artista;
	}

	public Integer getAnno_inizio() {
		return anno_inizio;
	}

	public void setAnno_inizio(Integer anno_inizio) {
		this.anno_inizio = anno_inizio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public LocalDateTime getData_inserimento() {
		return data_inserimento;
	}

	public void setData_inserimento(LocalDateTime data_inserimento) {
		this.data_inserimento = data_inserimento;
	}

	public LocalDateTime getData_aggiornamento() {
		return data_aggiornamento;
	}

	public void setData_aggiornamento(LocalDateTime data_aggiornamento) {
		this.data_aggiornamento = data_aggiornamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_artista);
	}

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