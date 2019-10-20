package com.ga.controller;

import org.junit.Before;
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

import com.ga.entity.UserRole;
import com.ga.service.UserService;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	UserController userController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserRole userRole;
	
	
	
	//test helloWorld
	@Test
	public void helloWorld_HelloWorld_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/hello")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().string("Hello World!"));
	}
	
	
	
	//test signUp
	@Test
	public void signup_User_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson("joe", "abc", "ROLE_ADMIN"));
		
		when(userService.signup(any())).thenReturn("123456");
		
		MvcResult result = mockMvc.perform(requestBuilder)
				   .andExpect(status().isOk())
				   .andExpect(content().json("{\"token\":\"123456\"}"))
				   .andReturn();

				System.out.println(result.getResponse().getContentAsString()); 
	}
	
    private static String createUserInJson(String username, String password, String role) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"," + "{\"userRole\": \"" + createUserRoleInJson(role) + "\"}";
    }
    
    private static String createUserRoleInJson(String role) {
    	return "\"name\":\"" + role + "\"}";
    }
    
    
    
    
    //test login
    @Test
	public void login_User_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/login")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson2("joe","abc"));
		
		when(userService.login(any())).thenReturn("123456");
		
		mockMvc.perform(requestBuilder)
          .andExpect(status().isOk())
          .andExpect(content().json("{\"token\":\"123456\"}"));
	}
    
    private static String createUserInJson2(String username, String password) {
   	 return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"}";
   }

	
	
	

}
