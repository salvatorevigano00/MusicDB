package com.example.music.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe che rappresenta un album musicale nel sistema.
 * 
 * Questa classe segue la convenzione POJO (Plain Old Java Object) ed è utilizzata per mappare
 * i dati di un album musicale da una tabella nel database. Un album è associato a un artista 
 * (oggetto di tipo {@link Artista}) e include informazioni come il nome, il genere, la data 
 * di uscita e le date di inserimento e aggiornamento.
 * 
 * Viene utilizzata anche nel contesto di ORM (Object-Relational Mapping) per mappare la 
 * tabella "album" del database su un oggetto Java.
 * 
 * Le proprietà principali della classe sono:
 * - id_album: identificativo univoco dell'album.
 * - nome_album: nome dell'album.
 * - genere: genere musicale dell'album.
 * - data_uscita: data di uscita dell'album.
 * - data_inserimento: data di inserimento del record nel sistema.
 * - data_aggiornamento: data dell'ultimo aggiornamento del record.
 * - artista: l'artista associato all'album (riferimento a un oggetto {@link Artista}).
 * 
 * @see Artista
 */

public class Album {
	private Integer id_album;
	private String nome_album, genere;
	private LocalDate data_uscita;
	private LocalDateTime data_inserimento, data_aggiornamento;
	
	private Artista artista;
	
	public Album() {}
	
	public Album(Integer id_album, String nome_album, String genere, LocalDate data_uscita,
			LocalDateTime data_inserimento, LocalDateTime data_aggiornamento, Artista artista) {
		super();
		this.id_album = id_album;
		this.nome_album = nome_album;
		this.genere = genere;
		this.data_uscita = data_uscita;
		this.data_inserimento = data_inserimento;
		this.data_aggiornamento = data_aggiornamento;
		this.artista = artista;
	}

	public Integer getId_album() {
		return id_album;
	}

	public void setId_album(Integer id_album) {
		this.id_album = id_album;
	}

	public String getNome_album() {
		return nome_album;
	}

	public void setNome_album(String nome_album) {
		this.nome_album = nome_album;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public LocalDate getData_uscita() {
		return data_uscita;
	}

	public void setData_uscita(LocalDate data_uscita) {
		this.data_uscita = data_uscita;
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

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_album);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(id_album, other.id_album);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [id_album=");
		builder.append(id_album);
		builder.append(", nome_album=");
		builder.append(nome_album);
		builder.append(", genere=");
		builder.append(genere);
		builder.append(", data_uscita=");
		builder.append(data_uscita);
		builder.append(", data_inserimento=");
		builder.append(data_inserimento);
		builder.append(", data_aggiornamento=");
		builder.append(data_aggiornamento);
		builder.append(", artista=");
		builder.append(artista);
		builder.append("]");
		return builder.toString();
	}
}
