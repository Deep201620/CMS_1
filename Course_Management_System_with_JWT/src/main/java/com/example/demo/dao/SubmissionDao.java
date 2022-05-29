package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Submission;

@Repository
public interface SubmissionDao extends JpaRepository<Submission, Long>{

}
