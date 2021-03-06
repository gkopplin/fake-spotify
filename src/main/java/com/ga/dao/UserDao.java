package com.ga.dao;

import java.util.List;
import com.ga.entity.Song;
import com.ga.entity.User;

public interface UserDao {
	
	public List<User> listUsers();

	User signup(User user);

	User login(User user);
	
	List<Song> listSongs(Long userId);
	
	List<Song> addSong(Long userId, Long songId);

	List<Song> removeSong(Long userId, Long songId);
	
	public User getUserByUsername(String username);
	
	public User updateUser(User user, Long userId);
	
	public Long deleteUser(Long userId);

}