package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.EmailDetails;
import com.example.demo.dao.SubmissionDao;
import com.example.demo.entity.Submission;
import com.example.demo.service.ChapterService;
import com.example.demo.service.EmailService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.SubmissionService;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private SubmissionDao submissionDao;

	@Autowired
	private EmailService emailService;



	@PreAuthorize("hasRole('Instructor')")
	@GetMapping
	@RequestMapping("/submissions")
	public List<Submission> getAllSubmissions() {
		return submissionService.getAllSubmissions();
	}

	@PreAuthorize("hasRole('Instructor')")
	@PutMapping
	@RequestMapping("/updatecomment/{submissionId}")
	public void updatecomments(@PathVariable long submissionId, @RequestBody String comments) {
		submissionDao.givecomments(comments, submissionId);
	}
	
	// Sending a simple Email
	@PreAuthorize("hasAnyRole('Instructor','User')")
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		String status = emailService.sendSimpleEmail(details);
		return status;
	}

}
