package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Role;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.service.ChapterService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.SubmissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private SubjectService subjectService;
	
	@Mock
	private ChapterService chapterService;
	

	@Mock
	private SubmissionService submissionService;
	
	@InjectMocks
	private StudentController studentController;
	
	Subject sub1 = new Subject(1L, "Java");
	Subject sub2 = new Subject(2L, "C++");
	
	Chapter chap1 = new Chapter(1L, sub1, "Intorduction to Java", null);
	Chapter chap2= new Chapter(2L, sub1, "Loop Constructs", null);
	
	Role role = new Role("Admin","Main user");
	Set<Role> roles = new HashSet<>();
	
	 
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		
	}

	@Test
	public void getAllSubjects() throws Exception {
//		when(subjectService.getAllSubjects()).thenReturn(Stream.of(new Subject(1L,"Maths")).collect(Collectors.toList()));
//		assertEquals(1, ((List<Subject>) subjectService.getAllSubjects()).size());
		
		List<Subject> subjects = new ArrayList<>();
		subjects.add(sub1);
		subjects.add(sub2);
		when(subjectService.getAllSubjects()).thenReturn(subjects);
		
		mockMvc.perform(get("/api/student/getsubjects")).andDo(print());
		assertEquals(2, subjectService.getAllSubjects().size());
		
	}
	@Test
	public void getChapterBySid() throws Exception {
	Long id = sub1.getSubjectId();
	Long chapterid = chap1.getSubject().getSubjectId();
	assertEquals(id, chapterid);
	}
	
	@Test
	public void addSubmission() throws Exception{
		roles.add(role);
		User user1 = new User("Deep123","Deep","Shah","demo123",roles); 
		Submission subm1 = Submission.builder().submissionId(1L).subject(sub1).chapter(chap1).user(user1).Submission_file_url("file name").comments("nice work").build();
		String content = objectWriter.writeValueAsString(subm1);
		when(submissionService.saveSubmission(subm1)).thenReturn(subm1);
		mockMvc.perform(post("/api/student/submit")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content))
		.andDo(print()).andExpect(status().isCreated());
		

	}
	
	
}
