package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Chapter;

public interface ChapterDao extends JpaRepository<Chapter, Long>{
	
	@Query("Select chapterId, chapterName from Chapter where FK_subjectid = :id")
	List<String> getChapterBySid(long id);

}
