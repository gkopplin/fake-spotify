package com.ga.service;

import java.util.List;

import com.ga.entity.User;

public interface UserService {

	public List<User> listUsers();

	User signup(User user);

	User login(User user);
		
}