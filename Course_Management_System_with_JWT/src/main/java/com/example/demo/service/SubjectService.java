package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Subject;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dao.SubjectRepo;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	public List<Subject> getAllSubjects(){
		return subjectRepo.findAll();
	}
	
	public Subject saveSubject(Subject subject) {
		return subjectRepo.save(subject);
	}
	
	public void deleteSubject(long id) {
		
//		check whether an subject exits in a DB or not
		subjectRepo.findById(id).
		orElseThrow(() -> new ResourceNotFoundException("Subject","subjectId",id));
		
		subjectRepo.deleteById(id);
	}
	
	public Optional<Subject> getSubjectById(long id) {
		
		Optional<Subject> foundsub = Optional.ofNullable(subjectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "subjectId", id)));
		
		return foundsub;
	}
	
	
	
}
