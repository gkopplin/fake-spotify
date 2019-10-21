package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


public class UserServiceTest {
	@Mock
    UserDao userDao;
    
    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @InjectMocks
    private User user;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    //test user update
    @Test
    public void updateUser_User_Success() {
        user.setUserId((long) 8);
        user.setUsername("batman");
        user.setPassword("robin");
        
        when(userDao.updateUser(any(), anyLong())).thenReturn(user);

        User tempUser = userService.updateUser(user, user.getUserId());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }

}
