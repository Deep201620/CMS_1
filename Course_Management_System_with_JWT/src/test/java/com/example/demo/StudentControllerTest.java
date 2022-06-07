package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.StudentController;
import com.example.demo.dao.ChapterDao;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Subject;
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
	private ChapterDao chapterDao;
	
	@Mock
	private SubmissionService submissionService;
	
	@InjectMocks
	private StudentController studentController;
	
	Subject sub1 = new Subject(1L, "Java");
	Subject sub2 = new Subject(2L, "C++");
	
	Chapter chap1 = new Chapter(1L, sub1, "Intorduction to Java", null);
	Chapter chap2= new Chapter(2L, sub1, "Loop Constructs", null);
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		
	}

	@Test
	public void getAllSubjects() {
		when(subjectService.getAllSubjects()).thenReturn(Stream.of(new Subject(1L,"Maths")).collect(Collectors.toList()));
		assertEquals(1, ((List<Subject>) subjectService.getAllSubjects()).size());
		
	}
	
	
}
