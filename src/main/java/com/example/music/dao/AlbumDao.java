package com.example.music.dao;

import java.sql.SQLException;
import java.util.Set;

import com.example.music.model.Album;

public interface AlbumDao {
	Album save(Album album) throws SQLException;
	
	Album findById(Integer album_id) throws SQLException;
	
	Album update(Integer album_id, Album album) throws SQLException;
	
	Album deleteById(Integer album_id) throws SQLException;
	
	Set<Album> findAll() throws SQLException;
}
