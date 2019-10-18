package com.ga.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public User signup(User user) {
		String roleName = user.getUserRole().getName();

		UserRole userRole = userRoleDao.getRole(roleName);
//		
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			user.setUserRole(userRole);

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
			resultUser = (User) session.createQuery("FROM User u WHERE u.username = '" + user.getUsername()
					+ "'").getSingleResult();
		} finally {
			session.close();
		}

		return resultUser;
	}

	@Override
	public List<User> listUsers() {
		List<User> allUsers = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			allUsers = session.createQuery("FROM User").getResultList();
		} finally {
			session.close();
		}

		return allUsers;
	}

	@Override
	public List<Song> listSongs(Long userId) {
		Session session = sessionFactory.getCurrentSession();

		List<Song> resultSongs;

		try {
			session.beginTransaction();

			User fetchedUser = session.get(User.class, userId);

			resultSongs = fetchedUser.getSongs();
			Hibernate.initialize(resultSongs);

		} finally {
			session.close();
		}

		return resultSongs;
	}

	@Override
	public List<Song> addSong(Long userId, Long songId) {
		Session session = sessionFactory.getCurrentSession();

		List<Song> resultSongs = null;

		try {
			session.beginTransaction();

			User fetchedUser = session.get(User.class, userId);

			if (fetchedUser != null) {

				Song fetchedSong = session.get(Song.class, songId);

				fetchedUser.addSong(fetchedSong);
				session.saveOrUpdate(fetchedUser);
				session.getTransaction().commit();
				
				resultSongs = fetchedUser.getSongs();
			}
			
			
		} finally {
			session.close();
		}
		
		return resultSongs;
	}

	@Override
	public List<Song> removeSong(Long userId, Long songId) {
		Session session = sessionFactory.getCurrentSession();
		
		List<Song> resultSongs = null;
		
		try {
			session.beginTransaction();
			
			User fetchedUser = session.get(User.class, userId);
			
			if (fetchedUser != null) {
				
				fetchedUser.removeSong(songId);
				
				session.saveOrUpdate(fetchedUser);
				session.getTransaction().commit();

				resultSongs = fetchedUser.getSongs();
			}

		} finally {
			session.close();
		}

		return resultSongs;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user = (User)session.createQuery("FROM User u WHERE u.username = '" + 
				username + "'").uniqueResult();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	@Override
	public User updateUser(User user, Long userId) {
		User savedUser = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			savedUser = session.get(User.class, userId);
			savedUser.setPassword(user.getPassword());
			
			session.update(savedUser);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return savedUser;
	}
	
	@Override
	public Long deleteUser(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		
		User savedUser = null;
		
		try {
			session.beginTransaction();
			
			savedUser = session.get(User.class, userId);
			session.delete(savedUser);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return savedUser.getUserId();
	}
}
