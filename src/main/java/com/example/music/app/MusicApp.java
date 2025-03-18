package com.example.music.app;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.music.dao.ArtistaDao;
import com.example.music.dao.ArtistaDaoImpl;
import com.example.music.dao.AlbumDao;
import com.example.music.dao.AlbumDaoImpl;
import com.example.music.dao.TracceDao;
import com.example.music.dao.TracceDaoImpl;
import com.example.music.model.Album;
import com.example.music.model.Artista;
import com.example.music.model.Tracce;

/**
 * La classe MusicApp è il main dell'applicazione musicale.
 * In questa classe, inizializzo e testo le operazioni CRUD (Create, Read, Update, Delete)
 * per le entità Artista, Album e Tracce utilizzando i rispettivi DAO (Data Access Objects).
 * Gestisco anche la stampa dei risultati delle operazioni e la gestione delle eccezioni SQL.
 */
public class MusicApp {
    public static void main(String[] args) {
        try {
            ArtistaDao artistaDao = new ArtistaDaoImpl();
            AlbumDao albumDao = new AlbumDaoImpl();
            TracceDao tracceDao = new TracceDaoImpl();

            // Creo e salvo artisti
            Artista artista1 = new Artista(null, 1970, "Queens", "UK", LocalDateTime.now(), null);
            Artista artista2 = new Artista(null, 1965, "Pink Floyd", "UK", LocalDateTime.now(), null);
            Artista artista3 = new Artista(null, 1960, "The Beatles", "UK", LocalDateTime.now(), null);

            artista1 = artistaDao.save(artista1);
            artista2 = artistaDao.save(artista2);
            artista3 = artistaDao.save(artista3);

            // Creo e salvo album
            Album album1 = new Album(null, "A Night at the Operas", "ROCK", LocalDate.of(1975, 11, 21), LocalDateTime.now(), null, artista1);
            Album album2 = new Album(null, "The Dark Side of the Moon", "ROCK", LocalDate.of(1973, 3, 1), LocalDateTime.now(), null, artista2);
            Album album3 = new Album(null, "Abbey Road", "ROCK", LocalDate.of(1969, 9, 26), LocalDateTime.now(), null, artista3);

            album1 = albumDao.save(album1);
            album2 = albumDao.save(album2);
            album3 = albumDao.save(album3);

            // Creo e salvo tracce
            Tracce traccia1_1 = new Tracce(null, "Bohemian Rhapsodys", album1, artista1, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia1_2 = new Tracce(null, "You're My Best Friend", album1, artista1, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia1_3 = new Tracce(null, "Love of My Life", album1, artista1, LocalDateTime.now(), LocalDateTime.now());

            Tracce traccia2_1 = new Tracce(null, "Money", album2, artista2, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia2_2 = new Tracce(null, "Time", album2, artista2, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia2_3 = new Tracce(null, "Us and Them", album2, artista2, LocalDateTime.now(), LocalDateTime.now());

            Tracce traccia3_1 = new Tracce(null, "Come Together", album3, artista3, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia3_2 = new Tracce(null, "Something", album3, artista3, LocalDateTime.now(), LocalDateTime.now());
            Tracce traccia3_3 = new Tracce(null, "Here Comes the Sun", album3, artista3, LocalDateTime.now(), LocalDateTime.now());

            traccia1_1 = tracceDao.save(traccia1_1);
            traccia1_2 = tracceDao.save(traccia1_2);
            traccia1_3 = tracceDao.save(traccia1_3);

            traccia2_1 = tracceDao.save(traccia2_1);
            traccia2_2 = tracceDao.save(traccia2_2);
            traccia2_3 = tracceDao.save(traccia2_3);

            traccia3_1 = tracceDao.save(traccia3_1);
            traccia3_2 = tracceDao.save(traccia3_2);
            traccia3_3 = tracceDao.save(traccia3_3);

            // Test ArtistaDao
            System.out.println("\nTest ArtistaDao:");
            System.out.println("Artista 1: " + artistaDao.findById(artista1.getId_artista()));
            artista1.setNome("Queen");
            artistaDao.update(artista1.getId_artista(), artista1);
            System.out.println("Artista 1 Updated: " + artistaDao.findById(artista1.getId_artista()));
            artistaDao.deleteById(artista3.getId_artista());
            System.out.println("Artisti after delete: ");
            artistaDao.findAll().forEach(System.out::println);

            // Test AlbumDao
            System.out.println("\nTest AlbumDao:");
            System.out.println("Album 1: " + albumDao.findById(album1.getId_album()));
            album1.setNome_album("A Night at the Opera");
            albumDao.update(album1.getId_album(), album1);
            System.out.println("Album 1 Updated: " + albumDao.findById(album1.getId_album()));
            albumDao.deleteById(album3.getId_album());
            System.out.println("Albums after delete: ");
            albumDao.findAll().forEach(System.out::println);

            // Test TracceDao
            System.out.println("\nTest TracceDao:");
            System.out.println("Traccia 1_1: " + tracceDao.findById(traccia1_1.getId_traccia()));
            traccia1_1.setNome_traccia("Bohemian Rhapsody");
            tracceDao.update(traccia1_1.getId_traccia(), traccia1_1);
            System.out.println("Traccia 1_1 Updated: " + tracceDao.findById(traccia1_1.getId_traccia()));
            tracceDao.deleteById(traccia3_3.getId_traccia());
            System.out.println("Tracce after delete: ");
            tracceDao.findAll().forEach(System.out::println);

            // Stampa tutti gli artisti, album e tracce
            System.out.println("\nArtisti:");
            Set<Artista> artisti = artistaDao.findAll();
            for (Artista artista : artisti) {
                System.out.println(artista);
            }

            System.out.println("\nAlbum:");
            Set<Album> albums = albumDao.findAll();
            for (Album album : albums) {
                System.out.println(album);
            }

            System.out.println("\nTracce:");
            Set<Tracce> tracce = tracceDao.findAll();
            for (Tracce traccia : tracce) {
                System.out.println(traccia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}