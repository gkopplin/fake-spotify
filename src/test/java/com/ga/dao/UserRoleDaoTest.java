package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.UserRole;

public class UserRoleDaoTest {
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
    private UserRole userRole;
    
    @InjectMocks
    private UserRoleDaoImpl userRoleDao;
    
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Before
    public void initializeDummyUserRole() {
        userRole.setRoleId(1);
        userRole.setName("ROLE_ADMIN");
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }
    
    @Test
    public void createRole_Role_Success() {       
        UserRole result = userRoleDao.createRole(userRole);
        
        assertNotNull("Test returned null object, expected non-null", result);
        assertEquals(result, userRole);
    }
    
    @Test
    public void getRole_Role_Success() {  
    	Query query = Mockito.mock(Query.class);
    	when(session.createQuery(anyString())).thenReturn(query);
    	when((query).getSingleResult()).thenReturn(userRole);
    	
        UserRole result = userRoleDao.getRole("ROLE_ADMIN");
        
        assertNotNull("Test returned null object, expected non-null", result);
        assertEquals(result, userRole);
    }
    
}
