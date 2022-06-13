package com.example.demo.Service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dao.SubjectRepo;
import com.example.demo.entity.Subject;
import com.example.demo.service.SubjectService;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

	@Mock
	private SubjectRepo subjectRepo;
	
	@InjectMocks
	SubjectService subjectService;
	
	private MockMvc mockMvc;
	
	Subject sub1;
	
	@BeforeEach
	public void setup() {
		 sub1 = Subject.builder().subjectId(1L).subjectName("Chemistry").build();
		
	}
	
	@Test
	public void getAllSubjects() throws Exception{
		List<Subject> subjects = new ArrayList<>();
		
		Subject sub2 = new Subject(1L,"Mathematcis");
		Subject sub3 = new Subject(2L, "Physics");
		
		subjects.add(sub2);
		subjects.add(sub3);
		when(subjectRepo.findAll()).thenReturn(subjects);
		
		List<Subject> getSubjects = subjectRepo.findAll();

		assertEquals(2, getSubjects.size());
		
		System.out.println(getSubjects);
		
	}
	
	@Test
	public void addSubject() throws Exception{
		when(subjectRepo.save(sub1)).thenReturn(sub1);
		
		Subject savedsub = subjectService.saveSubject(sub1);
		
		verify(subjectRepo, times(1)).save(sub1);
		
		assertTrue(savedsub.getSubjectName().equals("Chemistry"));
	}
	
	@Test
	public void deleteSubject() throws Exception{
		doNothing().when(subjectRepo).deleteById(sub1.getSubjectId());
		
		subjectService.deleteSubject(sub1.getSubjectId());
		
		verify(subjectRepo, times(1)).deleteById(sub1.getSubjectId());
		
	}
	
	@Test
	public void getSubjectById() throws Exception{
		
		when(subjectRepo.findById(sub1.getSubjectId())).thenReturn(Optional.of(sub1));
		
		Subject foundsubject = subjectService.getSubjectById(sub1.getSubjectId()).get();
		
		verify(subjectRepo, times(1)).findById(sub1.getSubjectId());
		
		assertEquals(1L, foundsubject.getSubjectId());
		
	}
	
	
}
