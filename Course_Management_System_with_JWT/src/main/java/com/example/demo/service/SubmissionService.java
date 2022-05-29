package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.SubmissionDao;
import com.example.demo.entity.Submission;

@Service
public class SubmissionService {

	@Autowired
	private SubmissionDao submissionDao;
	
	
	public Submission saveSubmission(Submission submission) {
		return submissionDao.save(submission);
	}
	
	public List<Submission> getAllSubmissions(){
		return submissionDao.findAll();
	}
	

	
}
