package com.ga.service;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import com.ga.controller.Before;
import com.ga.controller.InjectMocks;
import com.ga.controller.Mock;
import com.ga.controller.MockMvc;
import com.ga.controller.MvcResult;
import com.ga.controller.RequestBuilder;
import com.ga.controller.SongController;
import com.ga.controller.Test;
import com.ga.dao.SongDao;
import com.ga.entity.Song;
import com.ga.entity.User;

public class SongServiceTest {
	private MockMvc mockMvc;
    
    @InjectMocks
    SongService songService;
    
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
    public Song createSong_newSong_Success() throws Exception {
    	when(songDao.createSong(any())).thenReturn(song);
    	Song result = songService.createSong(song);
    	
    	assertNotNull(result);
        assertEquals(result.getResponse().getTitle(), song.getTitle());
	    assertEquals(result.getResponse().getLength(), song.getLength());
    }
}
