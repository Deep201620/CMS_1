package com.example.demo;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.configuration.EmailDetails;
import com.example.demo.controller.InstructorController;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Role;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.SubmissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RunWith(MockitoJUnitRunner.class)

public class InstructorControllerTest {
	
	@Mock
	SubmissionService submissionService;

	@Mock
	EmailService emailService;
	
	@InjectMocks
	InstructorController instructorController;
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	ObjectWriter objectWriter = objectMapper.writer();
	
	Subject sub1 = new Subject(1L, "Java");
	Subject sub2 = new Subject(2L, "C++");
	
	Chapter chap1 = new Chapter(1L, sub1, "Intorduction to Java", null);
	Chapter chap2= new Chapter(2L, sub1, "Loop Constructs", null);
	
	Submission subm1,subm2;
	
	Role role = new Role("Admin","Main user");
	Set<Role> roles = new HashSet<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
		User user1 = new User("Deep123","Deep","Shah","demo123",roles); 
		subm1 = Submission.builder().submissionId(1L).subject(sub1).chapter(chap1).user(user1).Submission_file_url("file name").comments("nice work").build();
		subm2 = Submission.builder().submissionId(2L).subject(sub2).chapter(chap2).user(user1).Submission_file_url("file_2_name").comments("Good research work").build();
	}
	
	@Test
	public void getAllSubmissions() throws Exception{
		List<Submission> submissions = new ArrayList<>();
		submissions.add(subm1);
		submissions.add(subm2);
		when(submissionService.getAllSubmissions()).thenReturn(submissions);
		MvcResult mvcResult = mockMvc.perform(get("/api/instructor/submissions")).andExpect(jsonPath("$", notNullValue())).andExpect(status().isOk()).andReturn();
		System.out.println("Output is: "+mvcResult.getResponse().getContentAsString());
		assertEquals(2, submissions.size());	
		
	}

	
	@Test()
	public void should_not_allow_null() throws Exception{
		Submission sub = new Submission(4L, sub1,null,chap1,"filename","good");
		assertThrows(SQLException.class, () -> {
			submissionService.saveSubmission(sub);
		});
	}
	
	@Test
	public void sendMail() throws Exception{
		EmailDetails em = EmailDetails.builder().recipient("191043107007@gperi.ac.in").subject("Demo mail").msgBody("Hey there").build();
		when(emailService.sendSimpleEmail(em)).thenReturn("Mail sent successfully");
		String mail = objectWriter.writeValueAsString(em);
	MvcResult mvcResult	= mockMvc.perform(post("/api/instructor/sendMail").contentType(MediaType.APPLICATION_JSON).content(mail)).andExpect(status().isOk()).andDo(print()).andReturn();
	System.out.println(mvcResult.getResponse().getContentAsString());
	}
	
	

}
