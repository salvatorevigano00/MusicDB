package com.example.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.example.music.config.MySQLConnection;
import com.example.music.model.Album;
import com.example.music.model.Artista;

public class AlbumDaoImpl implements AlbumDao{
	private static final Logger log = Logger.getLogger(ArtistaDaoImpl.class.getName());

	@Override
	public Album save(Album album) throws SQLException {
	    Album inserito = null;
	    String query1 = "SELECT id_artista FROM artisti WHERE id_artista = ?";
	    String query2 = "INSERT INTO album (nome_album, data_uscita, genere, id_artista, data_inserimento, data_aggiornamento) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt1 = conn.prepareStatement(query1);
	         PreparedStatement stmt2 = conn.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)) {

	        stmt1.setInt(1, album.getArtista().getId_artista());
	        try (ResultSet rs = stmt1.executeQuery()) {
	            if (!rs.next()) {
	                throw new SQLException("Artista con ID " + album.getArtista().getId_artista() + " non trovato.");
	            }
	        } catch (SQLException e) {
	            log.severe("Errore durante la verifica dell'artista per l'inserimento dell'album: " + e.getMessage());
	            e.printStackTrace();
	        }

	        // Prepara e esegue l'inserimento dell'album
	        stmt2.setString(1, album.getNome_album());
	        stmt2.setDate(2, java.sql.Date.valueOf(album.getData_uscita()));
	        stmt2.setString(3, album.getGenere());
	        stmt2.setInt(4, album.getArtista().getId_artista());
	        stmt2.setTimestamp(5, Timestamp.valueOf(album.getData_inserimento()));
	        stmt2.setTimestamp(6, null);

	        int righeCoinvolte = stmt2.executeUpdate();

	        if (righeCoinvolte > 0) {
	            try (ResultSet chiavi = stmt2.getGeneratedKeys()) {
	                if (chiavi.next()) {
	                    int id_album = chiavi.getInt(1);
	                    inserito = new Album(id_album, album.getNome_album(), album.getGenere(), album.getData_uscita(), album.getData_inserimento(), null, album.getArtista());
	                    log.info("Album creato con successo. ID: " + id_album);
	                }
	            } catch (SQLException e) {
	                log.severe("Errore durante l'estrazione della chiave primaria per l'album: " + e.getMessage());
	                e.printStackTrace();
	            }
	        } else {
	            log.warning("Nessuna riga inserita durante il tentativo di creazione dell'album.");
	        }
	    }
	    return inserito;
	}

	@Override
	public Album findById(Integer album_id) throws SQLException {
	    Album trovato = null;
	    Artista artista = null;

	    String query = "SELECT a.id_album, a.nome_album, a.data_uscita, a.genere, a.data_inserimento, a.data_aggiornamento, " +
	                   "ar.id_artista, ar.nome, ar.nazione, ar.anno_inizio, ar.data_inserimento AS artista_data_inserimento " +
	                   "FROM album a " +
	                   "JOIN artisti ar ON a.id_artista = ar.id_artista " +
	                   "WHERE a.id_album = ?";

	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, album_id);

	        try (ResultSet result = stmt.executeQuery()) {
	            if (result.next()) {
	                trovato = new Album();
	                trovato.setId_album(result.getInt("id_album"));
	                trovato.setNome_album(result.getString("nome_album"));
	                trovato.setData_uscita(result.getDate("data_uscita").toLocalDate());
	                trovato.setGenere(result.getString("genere"));
	                trovato.setData_inserimento(result.getTimestamp("data_inserimento").toLocalDateTime());

	                Timestamp timestamp = result.getTimestamp("data_aggiornamento");
	                if (timestamp != null) {
	                    trovato.setData_aggiornamento(timestamp.toLocalDateTime());
	                }

	                artista = new Artista();
	                artista.setId_artista(result.getInt("id_artista"));
	                artista.setNome(result.getString("nome"));
	                artista.setNazione(result.getString("nazione"));
	                artista.setAnno_inizio(result.getInt("anno_inizio"));
	                artista.setData_inserimento(result.getTimestamp("artista_data_inserimento").toLocalDateTime());

	                trovato.setArtista(artista);

	                log.info("Album trovato con successo. ID: " + album_id + " - " + trovato.getNome_album() + " - Artista: " + artista.getNome());
	            } else {
	                log.warning("Nessun album trovato con ID: " + album_id);
	            }
	        } catch (SQLException e) {
	            log.severe("Errore durante l'esecuzione della query per trovare l'album: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        log.severe("Errore durante la connessione o l'esecuzione della query per trovare l'album con ID: " + album_id + " - " + e.getMessage());
	        e.printStackTrace();
	    }

	    return trovato;
	}

	@Override
	public Album update(Integer album_id, Album album) throws SQLException {
	    Album aggiornato = null;

	    String query1 = "SELECT id_artista FROM artisti WHERE id_artista = ?";
	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt1 = conn.prepareStatement(query1)) {

	        stmt1.setInt(1, album.getArtista().getId_artista());
	        ResultSet result = stmt1.executeQuery();

	        if (!result.next()) {
	            log.warning("L'ID dell'artista non è valido.");
	            return aggiornato;
	        }
	    }

	    String query2 = "UPDATE album SET nome_album = ?, data_uscita = ?, genere = ?, id_artista = ?, data_inserimento = ?, data_aggiornamento = ? WHERE id_album = ?";
	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt2 = conn.prepareStatement(query2)) {

	        stmt2.setString(1, album.getNome_album());
	        stmt2.setDate(2, java.sql.Date.valueOf(album.getData_uscita()));
	        stmt2.setString(3, album.getGenere());
	        stmt2.setInt(4, album.getArtista().getId_artista());
	        stmt2.setTimestamp(5, Timestamp.valueOf(album.getData_inserimento()));

	        // Controllo se la data di aggiornamento è null prima di settarla
	        if (album.getData_aggiornamento() != null) {
	            stmt2.setTimestamp(6, Timestamp.valueOf(album.getData_aggiornamento()));
	        } else {
	            stmt2.setTimestamp(6, null);
	        }

	        stmt2.setInt(7, album_id);

	        int righeCoinvolte = stmt2.executeUpdate();

	        if (righeCoinvolte > 0) {
	            aggiornato = new Album();
	            aggiornato.setId_album(album_id);
	            aggiornato.setNome_album(album.getNome_album());
	            aggiornato.setGenere(album.getGenere());
	            aggiornato.setData_uscita(album.getData_uscita());
	            aggiornato.setData_inserimento(album.getData_inserimento());
	            aggiornato.setData_aggiornamento(album.getData_aggiornamento());
	            aggiornato.setArtista(album.getArtista());

	            log.info("Album con ID " + album_id + " aggiornato con successo.");
	        } else {
	            log.warning("Impossibile aggiornare l'album con ID: " + album_id);
	        }
	    } catch (SQLException e) {
	        log.severe("Errore durante l'aggiornamento dell'album: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return aggiornato;
	}

	@Override
	public Album deleteById(Integer album_id) throws SQLException {
	    Album rimosso = null;
	    String query1 = "SELECT nome_album, data_uscita, genere, data_inserimento, data_aggiornamento, id_artista FROM album WHERE id_album = ?";
	    String query2 = "DELETE FROM album WHERE id_album = ?";
	    
	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt1 = conn.prepareStatement(query1);
	         PreparedStatement stmt2 = conn.prepareStatement(query2)) {
	        
	        stmt1.setInt(1, album_id);
	        try (ResultSet result = stmt1.executeQuery()) {
	            if (result.next()) {
	                rimosso = new Album();
	                rimosso.setId_album(album_id);
	                rimosso.setNome_album(result.getString("nome_album"));
	                rimosso.setData_uscita(result.getDate("data_uscita").toLocalDate());
	                rimosso.setGenere(result.getString("genere"));
	                
	                Timestamp dataInserimento = result.getTimestamp("data_inserimento");
	                if (dataInserimento != null) {
	                    rimosso.setData_inserimento(dataInserimento.toLocalDateTime());
	                }
	                
	                Timestamp dataAggiornamento = result.getTimestamp("data_aggiornamento");
	                if (dataAggiornamento != null) {
	                    rimosso.setData_aggiornamento(dataAggiornamento.toLocalDateTime());
	                }

	                stmt2.setInt(1, album_id);
	                stmt2.executeUpdate();

	                log.info("Album con ID " + album_id + " rimosso con successo.");
	            } else {
	                log.warning("Non è stato possibile trovare l'album con ID: " + album_id);
	            }
	        }
	    } catch (SQLException e) {
	        log.severe("Errore durante la rimozione dell'album con ID " + album_id + ": " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return rimosso;
	}


	@Override
	public Set<Album> findAll() throws SQLException {
	    Set<Album> albumList = new LinkedHashSet<>();
	    String query = "SELECT a.id_album, a.nome_album, a.data_uscita, a.genere, a.data_inserimento, a.data_aggiornamento, " +
	                   "ar.id_artista, ar.nome, ar.nazione, ar.anno_inizio, ar.data_inserimento AS artista_data_inserimento " +
	                   "FROM album a JOIN artisti ar ON a.id_artista = ar.id_artista ORDER BY a.data_uscita";
	    
	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet result = stmt.executeQuery()) {
	        
	        while (result.next()) {
	            Album album = new Album();
	            album.setId_album(result.getInt("id_album"));
	            album.setNome_album(result.getString("nome_album"));
	            album.setData_uscita(result.getDate("data_uscita").toLocalDate());
	            album.setGenere(result.getString("genere"));
	            album.setData_inserimento(result.getTimestamp("data_inserimento").toLocalDateTime());
	            
	            Timestamp timestamp = result.getTimestamp("data_aggiornamento");
	            if (timestamp != null) {
	                album.setData_aggiornamento(timestamp.toLocalDateTime());
	            }

	            Artista artista = new Artista();
	            artista.setId_artista(result.getInt("id_artista"));
	            artista.setNome(result.getString("nome"));
	            artista.setNazione(result.getString("nazione"));
	            artista.setAnno_inizio(result.getInt("anno_inizio"));
	            artista.setData_inserimento(result.getTimestamp("artista_data_inserimento").toLocalDateTime());
	            
	            album.setArtista(artista);
	            albumList.add(album);
	        }
	        
	        log.info("Sono stati trovati " + albumList.size() + " album.");
	    } catch (SQLException e) {
	        log.severe("Errore durante il recupero degli album: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return albumList;
	}
}