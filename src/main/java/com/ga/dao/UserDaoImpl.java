package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Song;
import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User signup(User user) {
//		String roleName = user.getUserRole().getName();
		
//		UserRole userRole = userRoleDao.getRole(roleName);
//		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
//			user.setUserRole(userRole);
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		User resultUser;
		
		try {
			session.beginTransaction();
			resultUser = (User) session.createQuery("FROM User u WHERE u.username = '" + 
					user.getUsername() + "' AND u.password = '" + 
					user.getPassword() + "'").getSingleResult();
		} finally {
			session.close();
		}
		
		return resultUser;
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Song> listSongs(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		
		List<Song> resultSongs;
		
		try {
			session.beginTransaction();
			
			User fetchedUser = session.get(User.class, userId);
			
			resultSongs = fetchedUser.getSongs();
			
		} finally {
			session.close();
		}
		
		return resultSongs;
	}
	
	@Override
	public List<Song> addSong(Long userId, Long songId) {
		Session session = sessionFactory.getCurrentSession();
		
		List<Song> resultSongs;
		
		try {
			session.beginTransaction();
			
			User fetchedUser = session.get(User.class, userId);
			Song fetchedSong = session.get(Song.class, songId);
			
			fetchedUser.addSong(fetchedSong);
			session.getTransaction().commit();
			
			resultSongs = fetchedUser.getSongs();
			
			
		} finally {
			session.close();
		}
		
		return resultSongs;
	}
}
