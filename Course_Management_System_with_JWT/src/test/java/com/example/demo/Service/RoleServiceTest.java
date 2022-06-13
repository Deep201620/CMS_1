package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.RoleDao;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

	@Mock
	RoleDao roleDao;
	
	@InjectMocks
	RoleService roleService;
	
	Role r1;
	
	@BeforeEach
	public void setup() {
		 r1= Role.builder().roleName("Admin").roleDescription("Main User of application").build();
	}
	
	@Test
	public void addrole_success() throws Exception{
		
		when(roleDao.save(r1)).thenReturn(r1);
		
		Role savedRole = roleService.createNewRole(r1);
		
		verify(roleDao, times(1)).save(r1);
		
		assertEquals(savedRole.getRoleName(), r1.getRoleName());
		
	}
	
	@Test
	public void getAllroles_success() throws Exception{
		
		List<Role> roles = new ArrayList<>();
		
		roles.add(r1);
		
		when(roleDao.findAll()).thenReturn(roles);
		
		roleService.getAllRoles();
		
		verify(roleDao, times(1)).findAll();
		
	}
	
}
