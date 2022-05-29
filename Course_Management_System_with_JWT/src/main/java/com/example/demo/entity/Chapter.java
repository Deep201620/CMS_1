package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chapter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chapterId;
	
	@ManyToOne
	@JoinColumn(name="FK_subjectid", referencedColumnName = "subjectId")
	
	@JsonBackReference
	private Subject subject;
	
	
	private String chapterName;
	
	

	public long getChapterId() {
		return chapterId;
	}

	public void setChapterId(long chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	
	@OneToOne(mappedBy = "chapter")
	private Submission submisison;

}
