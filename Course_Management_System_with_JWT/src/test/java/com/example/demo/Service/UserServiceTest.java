package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.VerificationCollector;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	UserService userService;
	
	
	Set<Role> roles = new HashSet<Role>();
	Role admin = Role.builder().roleName("Admin").roleDescription("Main user").build();
	
	User user2 = null;

	
	@BeforeEach
	public void setup() {
		roles.add(admin);
		user2 = User.builder().userName("Deep123")
				.userFirstName("Deep")
				.userLastName("Shah")
				.userPassword("demo123")
				.role(roles).build();
	}
	
	@Test
	@Order(1)
	public void saveruser_success() throws Exception{
		

		when(userDao.save(user2)).thenReturn(user2);
		
		User saveduser = userService.saveUser(user2);
		
		verify(userDao,times(1)).save(user2);
		
		assertEquals(saveduser.getUserName(), user2.getUserName());
		
	}
	
	@Test
	@Order(2)
	public void deleteUser_success() throws Exception{
		
		doNothing().when(userDao).deleteById(user2.getUserName());
		
		userService.deleteUser(user2.getUserName());
		
		verify(userDao, times(1)).deleteById(user2.getUserName());
		
	}
	
	
	@Test
	@Order(3)
	public void encodePassword_success() throws Exception{
		passwordEncoder.encode(user2.getUserPassword());
		
		verify(passwordEncoder, times(1)).encode(user2.getUserPassword());
	}
		

	
	
 
}
