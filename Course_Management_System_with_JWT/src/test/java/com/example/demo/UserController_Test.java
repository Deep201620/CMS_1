package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import static org.mockito.BDDMockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class UserController_Test {

	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	
	@InjectMocks
	private UserController userController;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	User user2 = new User("parvin123","parvin","desai","demo123",null);
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void testaddUser() throws Exception {
		
		Set<Role> roles = new HashSet<Role>();
		Role admin = Role.builder().roleName("Admin").roleDescription("Main user").build();
		roles.add(admin);
		
		User user1 = new User();
		user1.setUserName("Deep123");
		user1.setUserFirstName("Deep");
		user1.setUserLastName("Shah");
		user1.setUserPassword("demo123");
		user1.setRole(roles);
		
		String content = objectMapper.writeValueAsString(user1);
		
		String url = "/addUser";
		
		Mockito.when(userService.saveUser(any(User.class))).thenReturn(user1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(content);
		
		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
		
		String output = result.getResponse().getContentAsString();
		assertThat(output).isEqualTo(content);
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		
	}
	
	@Test
	public void testdeleteUserById() throws Exception{
		
		Mockito.when(userService.deleteUser("Deep123")).thenReturn("SUCCESS");
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteUser/parbin12").contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(delete("/deleteUser/Deep123")).andDo(print()).andExpect(status().isOk()).andReturn();
		
//		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

}
