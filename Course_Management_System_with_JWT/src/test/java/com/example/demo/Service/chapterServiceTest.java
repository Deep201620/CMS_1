package com.example.demo.Service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.ChapterDao;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Subject;
import com.example.demo.service.ChapterService;

@ExtendWith(MockitoExtension.class)
public class chapterServiceTest {

	@Mock
	ChapterDao chapterDao;
	
	@InjectMocks
	ChapterService chapterService;
	
	Chapter chap1;
	Subject sub1;
	
	@BeforeEach
	public void setup() {
		 sub1 = Subject.builder()
				.subjectId(1L)
				.subjectName("Programming in Java").build();
		
		chap1 = Chapter.builder()
				.chapterId(1L)
				.chapterName("Introduction to Java")
				.subject(sub1)
				.submisison(null)
				.build();
		
	}
	
	@Test
	public void addChapter_success() throws Exception{
		
		when(chapterDao.save(chap1)).thenReturn(chap1);
		
		chap1 = chapterService.addChapter(chap1);
		
		verify(chapterDao, times(1)).save(chap1);
		
//		assertTrue(savedChapter.getChapterName().equals(chap1.getChapterName()));
	}
	
	@Test
	public void deleteChapter_success() throws Exception{
		doNothing().when(chapterDao).deleteById(chap1.getChapterId());
		
		chapterService.deleteChapter(chap1.getChapterId());
		
		verify(chapterDao, times(1)).deleteById(chap1.getChapterId());
		
		assertTrue(chap1.equals(null));
	}
	
	@Test
	public void getChapterbySid() throws Exception{
		
		List<String> chapters = new ArrayList<>();
//		chapters.add(chap1.getChapterName());
		
		when(chapterDao.getChapterBySid(sub1.getSubjectId())).thenReturn(chapters);
		
		chapterService.getChapterBySid(sub1.getSubjectId());
		
		verify(chapterDao, times(1)).getChapterBySid(sub1.getSubjectId());
		
		assertEquals(chap1.getSubject().getSubjectId(), sub1.getSubjectId());
	}
	
	@Test
	public void getAllchapter() throws Exception{
		List<Chapter> chapters = new ArrayList<>();
		chapters.add(chap1);
		
		when(chapterDao.findAll()).thenReturn(chapters);
		
		chapterService.getAllChapter();
		
		verify(chapterDao, times(1)).findAll();
		
		
	}
	
	
	
}
