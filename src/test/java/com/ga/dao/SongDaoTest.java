package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.Song;

public class SongDaoTest {
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
    private Song song;
    
    @InjectMocks
    private SongDaoImpl songDao;
    
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Before
    public void initializeDummySong() {
        song.setSongId(1L);
        song.setTitle("song title");
        song.setLength(60);
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }
    
    @Test
    public void createSong_newSong_Success() {       
        Song result = songDao.createSong(song);
        
        assertNotNull("Test returned null object, expected non-null", result);
        assertEquals(result, song);
    }
}
