package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.dao.SongDao;
import com.ga.entity.Song;

@Repository
public class SongServiceImpl implements SongService {
	
	@Autowired
	private SongDao songDao;

	@Override
	public Song createSong(Song song) {
		return songDao.createSong(song);
	}

}
