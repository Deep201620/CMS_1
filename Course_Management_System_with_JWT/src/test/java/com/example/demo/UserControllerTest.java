package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.dao.ChapterDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Role;
import com.example.demo.entity.Subject;
import com.example.demo.entity.User;
import com.example.demo.service.ChapterService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private UserService userService;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private SubjectService subjectService;
	
	@Mock
	private ChapterService chapterService;
	
	@Mock
	private ChapterDao chapterDao;

	Subject subject = new Subject(1L, "Chemistry");
	Chapter chap2 = new Chapter(2L,subject,"Exceptions",null);
	

	@InjectMocks
	private UserController userController;

	Set<Role> roles = new HashSet<Role>();
	Role admin = Role.builder().roleName("Admin").roleDescription("Main user").build();
	
	User user2 = new User("parvin123","Parvin","Desai","demo123",roles);


	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void addUser_success() throws Exception {
		roles.add(admin);
		User record = User.builder().userName("Deep16").userFirstName("Deep").userLastName("Shah")
				.userPassword("demo123").role(roles).build();

		Mockito.when(userService.saveUser(record)).thenReturn(record);

		// this will convert old java object to String
		String content = objectWriter.writeValueAsString(record);

		MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/addUser")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);

		mockMvc.perform(mockPostRequest).andExpect(status().isCreated()).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue())) // check return json size
				.andExpect(jsonPath("$.userName", is("Deep16"))); // randomly check value of random data
	}

	@Test
	public void deleteUserById() throws Exception {
		
		Mockito.when(userDao.findById(user2.getUserName())).thenReturn(Optional.of(user2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/deleteUser/parvin12")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void addSubject_success() throws Exception {
		

		Subject subject = Subject.builder().subjectId(3L).subjectName("Socials").build();
		
		Mockito.when(subjectService.saveSubject(subject)).thenReturn(subject);

		// this will convert old java object to String
		String content = objectWriter.writeValueAsString(subject);

		MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/addSubject")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);

		mockMvc.perform(mockPostRequest).andExpect(status().isCreated()).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.subjectName", is("Socials")));
		// randomly check value of random data

	}

	@Test
	public void deleteSubjectById_success() throws Exception {
		Long subjectId = 3L;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/delSubject/"+subjectId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void addChapter() throws Exception{
				
		Chapter chapter1 = Chapter.builder()
				.chapterId(1L)
				.chapterName("Introduction to Spring boot")
				.build();
		
		Mockito.when(chapterService.addChapter(chapter1)).thenReturn(chapter1);
		
		String content = objectWriter.writeValueAsString(chapter1);
		
		MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/addChapter")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(content);
		
		mockMvc.perform(mockPostRequest).andExpect(status().isCreated())
		.andDo(print())
		.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.chapterName", is("Introduction to Spring boot")));
	}
	
	@Test
	public void delChapterById_success() throws Exception{
		
		Mockito.when(chapterDao.findById(chap2.getChapterId())).thenReturn(Optional.of(chap2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/delChapter/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

}
