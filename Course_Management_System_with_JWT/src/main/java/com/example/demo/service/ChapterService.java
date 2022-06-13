package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dao.ChapterDao;
import com.example.demo.entity.Chapter;

@Service
public class ChapterService {

	@Autowired
	private ChapterDao chapterDao;

	public Chapter addChapter(Chapter chapter) {
		return chapterDao.save(chapter);
	}

	public void deleteChapter(long id) {

//		check whether an subject exits in a DB or not
		chapterDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chapter", "chapterId", id));

		chapterDao.deleteById(id);
	}
	
	public List<Chapter> getAllChapter(){
		return chapterDao.findAll();
	}
	
	public List<String> getChapterBySid(long id) {
		return chapterDao.getChapterBySid(id);
	}

}
