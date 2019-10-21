package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.dao.UserRoleDao;
import com.ga.entity.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceTest {
	@InjectMocks
    UserRoleServiceImpl userRoleService;
    
    @Mock
    private UserRoleDao userRoleDao;
    
    @InjectMocks
    private UserRole userRole;
    
    @Before
    public void initializeDummyUserRole() {
        userRole.setRoleId(1);
        userRole.setName("ROLE_ADMIN");
    }
    
    @Test
    public void createRole_Role_Success() {
    	when(userRoleDao.createRole(any())).thenReturn(userRole);
    	UserRole result = userRoleService.createRole(userRole);
    	
    	assertNotNull(result);
        assertEquals(result, userRole);
    }
    
    @Test
    public void getRole_Role_Success() {
    	when(userRoleDao.getRole(any())).thenReturn(userRole);
    	UserRole result = userRoleService.getRole("ROLE_ADMIN");
    	
    	assertNotNull(result);
        assertEquals(result, userRole);
    }
}
