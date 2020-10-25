package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.MainRepository;
import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.NoticeDto;
import com.bit.paperhouse.dto.UserReviewDto;
import com.bit.paperhouse.dto.WriterDto;

@Service
@Transactional
public class MainService {
	
	@Autowired
	MainRepository dao;
	
	// 취향별 추천글
	public List<ArticleDto> getArticleList(String category){
		
		List<ArticleDto> list = dao.getArticleList(category);
		
		return list;
	}
	
	
	// 오늘의 추천 작가
	public WriterDto getTodayWriter() {
		
		WriterDto dto = dao.getTodayWriter();
		
		return dto;
		
	}
	
	// 이번 달 추천 작가
	public List<WriterDto> getWriterlist() {
		List<WriterDto> list =  dao.getWriterlist();
		return list;
	}
	
    public UserReviewDto getTodayWriterRecommend( int writerSeq ) {
    	
    	return dao.getTodayWriterRecommend(writerSeq);
    }
    
    public Integer newNoticeCheck() {
		return dao.newNoticeCheck();
	}
    
    public List<String> articleCategorys() {
		List<String> list = dao.articleCategorys();
		return list;
	}

}
