package com.ga.dao;

import java.util.List;
import com.ga.entity.Song;
import com.ga.entity.User;

public interface UserDao {
	
	public List<User> listUsers();

	User signup(User user);

	User login(User user);
	
	List<Song> listSongs(Long userId);

}