package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Song;

@Repository
public class SongDaoImpl implements SongDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Song createSong(Song song) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(song);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return song;
	}
	
}
