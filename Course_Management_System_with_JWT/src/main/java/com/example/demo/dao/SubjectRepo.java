package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long>{
	
	
}
