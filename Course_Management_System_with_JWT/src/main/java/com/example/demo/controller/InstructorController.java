package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Submission;
import com.example.demo.service.ChapterService;
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
	
	@GetMapping
	@RequestMapping("/submissions")
	public List<Submission> getAllSubmissions(){
		return submissionService.getAllSubmissions();
	}

}
