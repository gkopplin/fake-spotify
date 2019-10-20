package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SongDaoTest {
    @InjectMocks
    private SongDaoImpl songDao;
    
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
}
