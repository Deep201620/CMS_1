package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.SubmissionDao;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Role;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.service.SubmissionService;

@ExtendWith(MockitoExtension.class)
public class SubmissionServiceTest {

	
	@Mock
	SubmissionDao submissionDao;
	
	@InjectMocks
	SubmissionService submissionService;
	
	Subject sub1;
	
	Chapter chap1;
	
	Role role = new Role("Admin","Main user");
	Set<Role> roles = new HashSet<>();
	
	User user2 = null;
	
	Submission submission;
	
	@BeforeEach
	public void setup() {
		
		sub1 = Subject.builder()
				.subjectId(1L)
				.subjectName("Programming in Java").build();
		
		chap1 = Chapter.builder()
				.chapterId(1L)
				.chapterName("Introduction to Java")
				.subject(sub1)
				.submisison(null)
				.build();
		
		roles.add(role);
		user2 = User.builder().userName("Deep123")
				.userFirstName("Deep")
				.userLastName("Shah")
				.userPassword("demo123")
				.role(roles).build();
		
		submission = Submission.builder().submissionId(1L).user(user2).subject(sub1).chapter(chap1).Submission_file_url("Doc file").comments("Great work").build();
	}
	
	@Test
	public void add_submission_success() throws Exception{
		
		when(submissionDao.save(submission)).thenReturn(submission);
		
		Submission savedsubmission = submissionService.saveSubmission(submission);
		
		verify(submissionDao, times(1)).save(submission);
		
		assertEquals(1L, savedsubmission.getSubmissionId());
	}
	
	@Test
	public void getAllSubmission_success() throws Exception{
		List<Submission> submissions = new ArrayList<>();
		
		submissions.add(submission);
		
		when(submissionDao.findAll()).thenReturn(submissions);
		
		List<Submission> rcvdsubmissions = submissionService.getAllSubmissions();
		
		verify(submissionDao, times(1)).findAll();
		
		assertEquals(1, rcvdsubmissions.size());
		
		System.out.println(rcvdsubmissions);
	}
	
	
	
}
