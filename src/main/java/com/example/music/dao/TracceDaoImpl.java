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
import com.example.music.model.Tracce;

/**
 * Implementazione dell'interfaccia TracceDao per l'accesso ai dati delle tracce musicali.
 * 
 * Fornisco metodi per eseguire operazioni CRUD (Create, Read, Update, Delete)
 * sugli oggetti Tracce nel database MySQL.
 *
 * Utilizzo la classe MySQLConnection per ottenere connessioni al database
 * e gestisco le eccezioni SQLException in caso di errori durante l'accesso ai dati.
 *
 * @see TracceDao
 * @see MySQLConnection
 */
public class TracceDaoImpl implements TracceDao {
    private static final Logger log = Logger.getLogger(TracceDaoImpl.class.getName());

    /**
     * Salvo una nuova traccia nel database.
     *
     * @param traccia L'oggetto Tracce da salvare.
     * @return L'oggetto Tracce salvato, con l'ID generato dal database.
     * @throws SQLException Se si verifica un errore durante l'operazione di salvataggio.
     */
    @Override
    public Tracce save(Tracce traccia) throws SQLException {
        Tracce inserito = null;
        String query1 = "SELECT id_album FROM album WHERE id_album = ?";
        String query2 = "SELECT id_artista FROM artisti WHERE id_artista = ?";
        String query3 = "INSERT INTO tracce (nome_traccia, id_album, id_artista, data_inserimento, data_aggiornamento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.open();
             PreparedStatement stmt1 = conn.prepareStatement(query1);
             PreparedStatement stmt2 = conn.prepareStatement(query2);
             PreparedStatement stmt3 = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS)) {

            stmt1.setInt(1, traccia.getAlbum().getId_album());
            try (ResultSet rs = stmt1.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Album con ID " + traccia.getAlbum().getId_album() + " non trovato.");
                }
            }

            stmt2.setInt(1, traccia.getArtista().getId_artista());
            try (ResultSet rs = stmt2.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Artista con ID " + traccia.getArtista().getId_artista() + " non trovato.");
                }
            }

            stmt3.setString(1, traccia.getNome_traccia());
            stmt3.setInt(2, traccia.getAlbum().getId_album());
            stmt3.setInt(3, traccia.getArtista().getId_artista());
            stmt3.setTimestamp(4, Timestamp.valueOf(traccia.getData_inserimento()));
            stmt3.setTimestamp(5, null);

            int righeCoinvolte = stmt3.executeUpdate();

            if (righeCoinvolte > 0) {
                try (ResultSet chiavi = stmt3.getGeneratedKeys()) {
                    if (chiavi.next()) {
                        int id_traccia = chiavi.getInt(1);
                        inserito = new Tracce(id_traccia, traccia.getNome_traccia(), traccia.getAlbum(), traccia.getArtista(), traccia.getData_inserimento(), null);
                        log.info("Traccia creata con successo. ID: " + id_traccia);
                    }
                }
            } else {
                log.warning("Nessuna riga inserita durante il tentativo di creazione della traccia.");
            }
        }
        return inserito;
    }

    /**
     * Trovo una traccia nel database tramite il suo ID.
     *
     * @param id_traccia L'ID della traccia da trovare.
     * @return L'oggetto Tracce corrispondente all'ID specificato, o null se non trovato.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    @Override
    public Tracce findById(Integer id_traccia) throws SQLException {
        Tracce trovato = null;
        String query = "SELECT t.id_traccia, t.nome_traccia, t.data_inserimento, t.data_aggiornamento, " +
                "a.id_album, a.nome_album, a.data_uscita, a.genere, a.data_inserimento AS album_data_inserimento, " +
                "ar.id_artista, ar.nome, ar.nazione, ar.anno_inizio, ar.data_inserimento AS artista_data_inserimento " +
                "FROM tracce t " +
                "JOIN album a ON t.id_album = a.id_album " +
                "JOIN artisti ar ON t.id_artista = ar.id_artista " +
                "WHERE t.id_traccia = ?";

        try (Connection conn = MySQLConnection.open();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_traccia);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    trovato = new Tracce();
                    trovato.setId_traccia(result.getInt("id_traccia"));
                    trovato.setNome_traccia(result.getString("nome_traccia"));
                    trovato.setData_inserimento(result.getTimestamp("data_inserimento").toLocalDateTime());

                    Timestamp timestamp = result.getTimestamp("data_aggiornamento");
                    if (timestamp != null) {
                        trovato.setData_aggiornamento(timestamp.toLocalDateTime());
                    }

                    Album album = new Album();
                    album.setId_album(result.getInt("id_album"));
                    album.setNome_album(result.getString("nome_album"));
                    album.setData_uscita(result.getDate("data_uscita").toLocalDate());
                    album.setGenere(result.getString("genere"));
                    album.setData_inserimento(result.getTimestamp("album_data_inserimento").toLocalDateTime());
                    trovato.setAlbum(album);

                    Artista artista = new Artista();
                    artista.setId_artista(result.getInt("id_artista"));
                    artista.setNome(result.getString("nome"));
                    artista.setNazione(result.getString("nazione"));
                    artista.setAnno_inizio(result.getInt("anno_inizio"));
                    artista.setData_inserimento(result.getTimestamp("artista_data_inserimento").toLocalDateTime());
                    trovato.setArtista(artista);

                    log.info("Traccia trovata con successo. ID: " + id_traccia);
                } else {
                    log.warning("Nessuna traccia trovata con ID: " + id_traccia);
                }
            }
        }
        return trovato;
    }

    /**
     * Aggiorno una traccia esistente nel database.
     *
     * @param id_traccia L'ID della traccia da aggiornare.
     * @param traccia L'oggetto Tracce con i nuovi dati.
     * @return L'oggetto Tracce aggiornato.
     * @throws SQLException Se si verifica un errore durante l'operazione di aggiornamento.
     */
    @Override
    public Tracce update(Integer id_traccia, Tracce traccia) throws SQLException {
        Tracce aggiornato = null;
        String query = "UPDATE tracce SET nome_traccia = ?, id_album = ?, id_artista = ?, data_inserimento = ?, data_aggiornamento = ? WHERE id_traccia = ?";

        try (Connection conn = MySQLConnection.open();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, traccia.getNome_traccia());
            stmt.setInt(2, traccia.getAlbum().getId_album());
            stmt.setInt(3, traccia.getArtista().getId_artista());
            stmt.setTimestamp(4, Timestamp.valueOf(traccia.getData_inserimento()));
            stmt.setTimestamp(5, traccia.getData_aggiornamento() != null ? Timestamp.valueOf(traccia.getData_aggiornamento()) : null);
            stmt.setInt(6, id_traccia);

            int righeCoinvolte = stmt.executeUpdate();

            if (righeCoinvolte > 0) {
                aggiornato = new Tracce(id_traccia, traccia.getNome_traccia(), traccia.getAlbum(), traccia.getArtista(), traccia.getData_inserimento(), traccia.getData_aggiornamento());
                log.info("Traccia con ID " + id_traccia + " aggiornata con successo.");
            } else {
                log.warning("Impossibile aggiornare la traccia con ID: " + id_traccia);
            }
        }
        return aggiornato;
    }

    /**
     * Cancello una traccia dal database tramite il suo ID.
     *
     * @param id_traccia L'ID della traccia da cancellare.
     * @return L'oggetto Tracce cancellato.
     * @throws SQLException Se si verifica un errore durante l'operazione di cancellazione.
     */
    @Override
    public Tracce deleteById(Integer id_traccia) throws SQLException {
        Tracce rimosso = findById(id_traccia);
        String query = "DELETE FROM tracce WHERE id_traccia = ?";

        try (Connection conn = MySQLConnection.open();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_traccia);
            int righeCoinvolte = stmt.executeUpdate();

            if (righeCoinvolte > 0) {
                log.info("Traccia con ID " + id_traccia + " rimossa con successo.");
            } else {
                log.warning("Impossibile rimuovere la traccia con ID: " + id_traccia);
            }
        } catch (SQLException e) {
            log.severe("Errore durante la rimozione della traccia con ID " + id_traccia + ": " + e.getMessage());
            e.printStackTrace();
        }
        return rimosso;
    }

    /**
     * Trovo tutte le tracce nel database.
     *
     * @return Un insieme (Set) di oggetti Tracce presenti nel database.
     * @throws SQLException Se si verifica un errore durante l'operazione di ricerca.
     */
    @Override
    public Set<Tracce> findAll() throws SQLException {
        Set<Tracce> tracceList = new LinkedHashSet<>();
        String query = "SELECT t.id_traccia, t.nome_traccia, t.data_inserimento, t.data_aggiornamento,\n"
                + "            a.id_album, a.nome_album, a.data_uscita, a.genere, a.data_inserimento AS album_data_inserimento,\n"
                + "            ar.id_artista, ar.nome, ar.nazione, ar.anno_inizio, ar.data_inserimento AS artista_data_inserimento,\n"
                + "            ar2.id_artista as artista_album_id, ar2.nome as artista_album_nome, ar2.nazione as artista_album_nazione, ar2.anno_inizio as artista_album_anno_inizio, ar2.data_inserimento as artista_album_data_inserimento\n"
                + "FROM tracce t\n"
                + "JOIN album a ON t.id_album = a.id_album\n"
                + "JOIN artisti ar ON t.id_artista = ar.id_artista\n"
                + "JOIN artisti ar2 ON a.id_artista = ar2.id_artista ORDER BY t.id_traccia";

        try (Connection conn = MySQLConnection.open();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Tracce traccia = new Tracce();
                traccia.setId_traccia(result.getInt("id_traccia"));
                traccia.setNome_traccia(result.getString("nome_traccia"));
                traccia.setData_inserimento(result.getTimestamp("data_inserimento").toLocalDateTime());

                Timestamp timestamp = result.getTimestamp("data_aggiornamento");
                if (timestamp != null) {
                    traccia.setData_aggiornamento(timestamp.toLocalDateTime());
                }

                Album album = new Album();
                album.setId_album(result.getInt("id_album"));
                album.setNome_album(result.getString("nome_album"));
                album.setData_uscita(result.getDate("data_uscita").toLocalDate());
                album.setGenere(result.getString("genere"));
                album.setData_inserimento(result.getTimestamp("album_data_inserimento").toLocalDateTime());
                traccia.setAlbum(album);

                Artista artista = new Artista();
                artista.setId_artista(result.getInt("id_artista"));
                artista.setNome(result.getString("nome"));
                artista.setNazione(result.getString("nazione"));
                artista.setAnno_inizio(result.getInt("anno_inizio"));
                artista.setData_inserimento(result.getTimestamp("artista_data_inserimento").toLocalDateTime());
                traccia.setArtista(artista);

                tracceList.add(traccia);
            }
            log.info("Sono state trovate " + tracceList.size() + " tracce.");
        }
        return tracceList;
    }
}