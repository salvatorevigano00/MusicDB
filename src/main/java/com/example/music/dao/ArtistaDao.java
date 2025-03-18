package com.example.music.dao;

import java.sql.SQLException;
import java.util.Set;

import com.example.music.model.Artista;

public interface ArtistaDao {
	// Operazioni semplici CRUD
	
	// CREATE
	Artista save(Artista artista) throws SQLException;
	
	// READ
	Artista findById(Integer id_artista) throws SQLException;
	
	// UPDATE
	Artista update(Integer id_artista, Artista artista) throws SQLException;
	
	
	// DELETE
	Artista deleteById(Integer id_artista) throws SQLException;
	
	Set<Artista> findAll() throws SQLException;
}
