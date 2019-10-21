package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ga.dao.SongDao;
import com.ga.entity.Song;


public class SongServiceTest {
    
    @InjectMocks
    SongServiceImpl songService;
    
    @Mock
    private SongDao songDao;
    
    @InjectMocks
    private Song song;
    
    @Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
	
	@Before
    public void initializeDummySong() {
        song.setSongId(1L);
        song.setTitle("song title");
        song.setLength(60);
    }
    
    @Test
    public void createSong_newSong_Success() {
    	when(songDao.createSong(any())).thenReturn(song);
    	Song result = songService.createSong(song);
    	
    	assertNotNull(result);
        assertEquals(result, song);
    }
}
