package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Submission {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long submissionId;
	
	@ManyToOne
	@JoinColumn(name="FK_subjectid", referencedColumnName = "subjectId")
	
	@NotNull
	private Subject subject;
	
	
	@ManyToOne
	@JoinColumn(name="FK_userName", referencedColumnName = "userName")
	
	@NotNull
	private User user;
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Fk_chapterId", referencedColumnName = "chapterId")

	@NotNull
	private Chapter chapter;
	
	private String Submission_file_url;
	
	@Size(min = 10, max=100)
	private String comments;

	public long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(long submissionId) {
		this.submissionId = submissionId;
	}

	public String getSubmission_file_url() {
		return Submission_file_url;
	}

	public void setSubmission_file_url(String submission_file_url) {
		Submission_file_url = submission_file_url;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}


	
	
	
}
