package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.dao.UserDao;
import com.ga.entity.Song;
import com.ga.entity.User;


@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	public List<User> listUsers() {
		return null;
	}
	
	@Override
	public User signup(User user) {
		return userDao.signup(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<Song> listSongs(Long userId) {
		return userDao.listSongs(userId);
	}

	@Override
	public List<Song> addSong(Long userId, Long songId) {
		return userDao.addSong(userId, songId);
	}
	
	
}
