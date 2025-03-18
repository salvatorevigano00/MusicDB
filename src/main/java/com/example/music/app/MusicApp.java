package com.example.music.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.music.dao.ArtistaDao;
import com.example.music.dao.ArtistaDaoImpl;
import com.example.music.dao.AlbumDao;
import com.example.music.dao.AlbumDaoImpl;
import com.example.music.model.Album;
import com.example.music.model.Artista;

public class MusicApp {
    public static void main(String[] args) {
        try {
            Artista pinkFloyd = new Artista();
            pinkFloyd.setNome("Pink Floyd");
            pinkFloyd.setNazione("Regno Unito");
            pinkFloyd.setAnno_inizio(1965);
            pinkFloyd.setData_inserimento(LocalDateTime.now());

            ArtistaDao artistaDao = new ArtistaDaoImpl();
            Artista artistaPinkFloyd = artistaDao.save(pinkFloyd);

            if (artistaPinkFloyd != null) {
                System.out.println("Artista Pink Floyd salvato con successo: " + artistaPinkFloyd);
            } else {
                System.err.println("Errore nel salvataggio dell'artista Pink Floyd.");
            }

            Album album1 = new Album();
            album1.setNome_album("The Dark Side of the Moon");
            album1.setGenere("ROCK");
            album1.setData_uscita(LocalDate.of(1973, 3, 1));
            album1.setData_inserimento(LocalDateTime.now());
            album1.setArtista(artistaPinkFloyd);

            Album album2 = new Album();
            album2.setNome_album("Wish You Were Here");
            album2.setGenere("ROCK");
            album2.setData_uscita(LocalDate.of(1975, 9, 12));
            album2.setData_inserimento(LocalDateTime.now());
            album2.setArtista(artistaPinkFloyd);

            AlbumDao albumDao = new AlbumDaoImpl();
            Album albumSalvato1 = albumDao.save(album1);
            Album albumSalvato2 = albumDao.save(album2);

            if (albumSalvato1 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato1);
            } else {
                System.err.println("Errore nel salvataggio del primo album di Pink Floyd.");
            }

            if (albumSalvato2 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato2);
            } else {
                System.err.println("Errore nel salvataggio del secondo album di Pink Floyd.");
            }

            Artista queen = new Artista();
            queen.setNome("Queen");
            queen.setNazione("Regno Unito");
            queen.setAnno_inizio(1970);
            queen.setData_inserimento(LocalDateTime.now());

            Artista artistaQueen = artistaDao.save(queen);

            if (artistaQueen != null) {
                System.out.println("Artista Queen salvato con successo: " + artistaQueen);
            } else {
                System.err.println("Errore nel salvataggio dell'artista Queen.");
            }

            Album album3 = new Album();
            album3.setNome_album("A Night at the Opera");
            album3.setGenere("ROCK");
            album3.setData_uscita(LocalDate.of(1975, 11, 21));
            album3.setData_inserimento(LocalDateTime.now());
            album3.setArtista(artistaQueen);

            Album album4 = new Album();
            album4.setNome_album("News of the World");
            album4.setGenere("ROCK");
            album4.setData_uscita(LocalDate.of(1977, 10, 28));
            album4.setData_inserimento(LocalDateTime.now());
            album4.setArtista(artistaQueen);

            Album albumSalvato3 = albumDao.save(album3);
            Album albumSalvato4 = albumDao.save(album4);

            if (albumSalvato3 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato3);
            } else {
                System.err.println("Errore nel salvataggio del primo album di Queen.");
            }

            if (albumSalvato4 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato4);
            } else {
                System.err.println("Errore nel salvataggio del secondo album di Queen.");
            }

            Artista beatles = new Artista();
            beatles.setNome("Beatles");
            beatles.setNazione("Regno Unito");
            beatles.setAnno_inizio(1962);
            beatles.setData_inserimento(LocalDateTime.now());

            Artista artistaBeatles = artistaDao.save(beatles);

            if (artistaBeatles != null) {
                System.out.println("Artista Beatles salvato con successo: " + artistaBeatles);
            } else {
                System.err.println("Errore nel salvataggio dell'artista Beatles.");
            }

            Album album5 = new Album();
            album5.setNome_album("Abbey Road");
            album5.setGenere("ROCK");
            album5.setData_uscita(LocalDate.of(1969, 9, 26));
            album5.setData_inserimento(LocalDateTime.now());
            album5.setArtista(artistaBeatles);

            Album album6 = new Album();
            album6.setNome_album("Sgt. Pepper's Lonely Hearts Club Band");
            album6.setGenere("ROCK");
            album6.setData_uscita(LocalDate.of(1967, 5, 26));
            album6.setData_inserimento(LocalDateTime.now());
            album6.setArtista(artistaBeatles);

            Album albumSalvato5 = albumDao.save(album5);
            Album albumSalvato6 = albumDao.save(album6);

            if (albumSalvato5 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato5);
            } else {
                System.err.println("Errore nel salvataggio del primo album dei Beatles.");
            }

            if (albumSalvato6 != null) {
                System.out.println("Album salvato con successo: " + albumSalvato6);
            } else {
                System.err.println("Errore nel salvataggio del secondo album dei Beatles.");
            }

            Set<Artista> artisti = artistaDao.findAll();
            System.out.println("\nLista artisti:");
            for (Artista a : artisti) {
                System.out.println(a);
            }

            Set<Album> albums = albumDao.findAll();
            System.out.println("\nLista album:");
            for (Album al : albums) {
                System.out.println(al);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}