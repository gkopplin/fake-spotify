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
import com.ga.entity.UserRole;
import com.ga.service.SongService;
import com.ga.service.UserRoleService;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleControllerTest {
    private MockMvc mockMvc;
    
    @InjectMocks
    UserRoleController userRoleController;
    
    @Mock
    private UserRoleService userRoleService;
    
    @InjectMocks
    private UserRole userRole;
    
    @Before
    public void initializeDummyUserRole() {
        userRole.setRoleId(1);
        userRole.setName("ROLE_ADMIN");
    }
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRoleController).build();
    }
    
    private static String createRoleInJson(String name) {
        return "{ \"name\": \"" + name + "\" }" ;
    }
    
    @Test
    public void createRole_Role_Success() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
 		       .post("/role")
 		       .contentType(MediaType.APPLICATION_JSON)
 		       .content(createRoleInJson("ROLE_ADMIN"));
    	
    	when(userRoleService.createRole(any())).thenReturn(userRole);
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andReturn();
    	
    	assertNotNull(result);
    	System.out.println(result.getResponse().getContentAsString());
    }
    
    @Test
    public void getRole_Role_Success() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
  		       .get("/role/ROLE_ADMIN")
  		       .contentType(MediaType.APPLICATION_JSON);
     	
     	when(userRoleService.getRole(any())).thenReturn(userRole);
     	
     	MvcResult result = mockMvc.perform(requestBuilder)
 	              .andExpect(status().isOk())
 	              .andReturn();
     	
     	assertNotNull(result);
     	System.out.println(result.getResponse().getContentAsString());
    }
}
