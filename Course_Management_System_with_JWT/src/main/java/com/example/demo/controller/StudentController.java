package com.example.demo.controller;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.DataIntegrityException;
import com.example.demo.configuration.EmailDetails;
import com.example.demo.dao.ChapterDao;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Submission;
import com.example.demo.service.ChapterService;
import com.example.demo.service.EmailService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.SubmissionService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired 
	private ChapterService chapterService;
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired 
	private SubmissionService submissionService;
	
	@Autowired
	private EmailService emailService;
	
	String filename;
	
	//get all subjects
	@GetMapping("/getsubjects")
	@PreAuthorize("hasRole('User')")
	public List<Subject> getAllSubjects(){
		return subjectService.getAllSubjects();
	}
	
	//get all chapters of a subject id
	@PreAuthorize("hasRole('User')")
	@GetMapping("/{id}/getChapters")
	public List<String> getChapterBySid(@PathVariable("id") long id) {
		return chapterService.getChapterBySid(id);
	}
	
	
	@PreAuthorize("hasRole('User')")
	@PostMapping("/uploadFile")
	public ResponseEntity<?> handleFileUpload (@RequestParam("file") MultipartFile file){
		 filename = file.getOriginalFilename();
		
		try {
			file.transferTo(new File("C:\\Users\\150283\\Temp" + filename));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("File uploaded successfully");
	}
	

	
	@PreAuthorize("hasRole('User')")
	@PostMapping(value = "/submit")
	public ResponseEntity<Submission> addSubmission(@RequestBody Submission submission){
		submission.setSubmission_file_url(filename);
		return new ResponseEntity<Submission>(submissionService.saveSubmission(submission), HttpStatus.CREATED);
	}
	
//	@PreAuthorize("hasRole('User')")
//	@PostMapping("/sendMail")
//	public String sendMail(@RequestBody EmailDetails details) {
//		String status = emailService.sendSimpleEmail(details);
//		return status;
//	}
	
}
