package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Submission;

@Repository
public interface SubmissionDao extends JpaRepository<Submission, Long>{
	
	@Transactional
	@Modifying
	@Query("update Submission set comments = :comments where submission_id = :submissionId")
	void givecomments(String comments, long submissionId);
		
}
