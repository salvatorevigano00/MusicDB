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

import com.example.music.model.Artista;
import com.example.music.config.MySQLConnection;

public class ArtistaDaoImpl implements ArtistaDao {
	private static final Logger log = Logger.getLogger(ArtistaDaoImpl.class.getName());

	@Override
	public Artista save(Artista artista) throws SQLException {
		Artista inserito = null;
		
		String query = "INSERT INTO artisti (nome, nazione, anno_inizio, data_inserimento, data_aggiornamento) VALUES (?, ?, ?, ?, ?)";
		
		// try with resource
		try (Connection conn = MySQLConnection.open();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
			stmt.setString(1, artista.getNome());
			stmt.setString(2, artista.getNazione());
			stmt.setInt(3, artista.getAnno_inizio());
			stmt.setTimestamp(4, Timestamp.valueOf(artista.getData_inserimento()));
			stmt.setTimestamp(5, null);
			
			int righeCoinvolte = stmt.executeUpdate();
			
			Integer id_artista = -1;
			if(righeCoinvolte > 0) {
				// Recupero la chiave generata
				try(ResultSet chiavi = stmt.getGeneratedKeys();){
					if (chiavi.next()) {
						id_artista = chiavi.getInt(1);
						
						inserito = new Artista();
						inserito.setId_artista(id_artista);
						inserito.setNome(artista.getNome());
						inserito.setNazione(artista.getNazione());
						inserito.setAnno_inizio(artista.getAnno_inizio());
						inserito.setData_inserimento(artista.getData_inserimento());
						
						log.info("Creato l'artista con il seguente ID: " + id_artista);
					}
					else {
						log.warning("Non è stato possibile recuperare la chiave primaria");
					}
				}
			}
			else {
				log.severe("Non è stato possibile inserire l'artista");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return inserito;
	}

	@Override
	public Artista findById(Integer id_artista) throws SQLException {
		Artista trovato = null;
		
		String query = "SELECT nome, nazione, anno_inizio, data_inserimento, data_aggiornamento FROM artisti WHERE id_artista = ?";
		try (Connection conn = MySQLConnection.open();
				PreparedStatement stmt = stmt_findById(conn, query, id_artista);
				ResultSet result = stmt.executeQuery()){
			if(result.next()) {
				trovato = new Artista();
				trovato.setId_artista(id_artista);
				trovato.setNome(result.getString(1));
				trovato.setNazione(result.getString(2));
				trovato.setAnno_inizio(result.getInt(3));
				trovato.setData_inserimento(result.getTimestamp(4).toLocalDateTime());
				Timestamp timestamp = result.getTimestamp(6);
				if(timestamp != null)
					trovato.setData_aggiornamento(result.getTimestamp(5).toLocalDateTime());
				
				log.info("Trovato l'artista con il seguente ID: " + id_artista);
			}
			else {
				log.warning("Non è stato possibile trovare l'artista");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return trovato;
	}
	
	private PreparedStatement stmt_findById(Connection conn, String query, Integer id_artista) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id_artista);
		return stmt;
	}

	@Override
	public Artista update(Integer id_artista, Artista artista) throws SQLException {
		Artista aggiornato = null;
		String query = "UPDATE artisti SET nome = ?, nazione = ?, anno_inizio = ?, data_inserimento = ?, data_aggiornamento = ? WHERE id_artista = ?";
		
		try(Connection conn = MySQLConnection.open();
				PreparedStatement stmt = conn.prepareStatement(query);){
			stmt.setString(1, artista.getNome());
			stmt.setString(2, artista.getNazione());
			stmt.setInt(3, artista.getAnno_inizio());
			stmt.setTimestamp(4, Timestamp.valueOf(artista.getData_inserimento()));
			stmt.setTimestamp(5, Timestamp.valueOf(artista.getData_aggiornamento()));
			stmt.setInt(6, id_artista);
			
			int righeCoinvolte = stmt.executeUpdate();
			if(righeCoinvolte > 0) {
				aggiornato = new Artista();
				aggiornato.setId_artista(id_artista);
				aggiornato.setNome(artista.getNome());
				aggiornato.setNazione(artista.getNazione());
				aggiornato.setAnno_inizio(artista.getAnno_inizio());
				aggiornato.setData_inserimento(artista.getData_inserimento());
				aggiornato.setData_aggiornamento(artista.getData_aggiornamento());
				
				log.info("Aggiornato l'artista con il seguente ID: " + id_artista);
			}
			else {
				log.warning("Non è stato possibile aggiornare l'artista");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return aggiornato;
	}

	@Override
	public Artista deleteById(Integer id_artista) throws SQLException {
	    Artista rimosso = null;
	    String query1 = "SELECT nome, nazione, anno_inizio, data_inserimento, data_aggiornamento FROM artisti WHERE id_artista = ?";
	    String query2 = "DELETE FROM artisti WHERE id_artista = ?";
	    
	    try (Connection conn = MySQLConnection.open();
	         PreparedStatement stmt1 = conn.prepareStatement(query1);
	         PreparedStatement stmt2 = conn.prepareStatement(query2)) {
	        
	        stmt1.setInt(1, id_artista);
	        ResultSet result = stmt1.executeQuery();
	        
	        if (result.next()) {
	            rimosso = new Artista();
	            rimosso.setId_artista(id_artista);
	            rimosso.setNome(result.getString(1));
	            rimosso.setNazione(result.getString(2));
	            rimosso.setAnno_inizio(result.getInt(3));
	            
	            Timestamp timestampInserimento = result.getTimestamp(4);
	            if (timestampInserimento != null) {
	                rimosso.setData_inserimento(timestampInserimento.toLocalDateTime());
	            }
	            
	            Timestamp timestampAggiornamento = result.getTimestamp(5);
	            if (timestampAggiornamento != null) {
	                rimosso.setData_aggiornamento(timestampAggiornamento.toLocalDateTime());
	            }
	            
	            stmt2.setInt(1, id_artista);
	            stmt2.executeUpdate();
	            log.info("Rimosso l'artista con ID: " + id_artista);
	        } else {
	            log.warning("Non è stato possibile rimuovere l'artista con l'ID: " + id_artista);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return rimosso;
	}

	@Override
	public Set<Artista> findAll() throws SQLException {
		Set<Artista> artisti = new LinkedHashSet<Artista>();
		String query = "SELECT * FROM artisti ORDER BY anno_inizio";
		try(Connection conn = MySQLConnection.open();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet result = stmt.executeQuery()){
			while(result.next()) {
				Artista artista = new Artista();
				artista.setId_artista(result.getInt(1));
				artista.setNome(result.getString(2));
				artista.setNazione(result.getString(3));
				artista.setAnno_inizio(result.getInt(4));
				artista.setData_inserimento(result.getTimestamp(5).toLocalDateTime());
				Timestamp timestamp = result.getTimestamp(6);
				if (timestamp != null)
					artista.setData_aggiornamento(result.getTimestamp(6).toLocalDateTime());
				artisti.add(artista);
			}
			log.info("Sono stati trovati " + artisti.size() + " artisti");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return artisti;
	}
}