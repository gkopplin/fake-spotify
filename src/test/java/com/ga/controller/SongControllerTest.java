package com.ga.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ga.entity.Song;
import com.ga.service.SongService;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    private MockMvc mockMvc;
    
    @InjectMocks
    SongController songController;
    
    @Mock
    private SongService songService;
    
    @InjectMocks
    private Song song;
    
    @Before
    public void initializeDummySong() {
        song.setSongId(1L);
        song.setTitle("song title");
        song.setLength(60);
    }
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
    }
    
    private static String createSongInJson(String title, int length) {
        return "{ \"title\": \"" + title + "\", " +
                "\"length\":\"" + length + "\"}";
    }
    
    @Test
    public void createSong_newSong_Success() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
 		       .post("/song/create")
 		       .contentType(MediaType.APPLICATION_JSON)
 		       .content(createSongInJson("song title", 60));
    	
    	when(songService.createSong(any())).thenReturn(song);
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andReturn();
    	
    	assertNotNull(result);
    	System.out.println(result.getResponse().getContentAsString());
    }

}