package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.Song;
import com.ga.entity.User;

public interface UserService extends UserDetailsService {

	public List<User> listUsers();

	public String signup(User user);

//	User login(User user);
	public String login(User user);
	
	List<Song> listSongs(Long userId);
	
	List<Song> addSong(Long userId, Long songId);
	
	List<Song> removeSong(Long userId, Long songId);
		
}