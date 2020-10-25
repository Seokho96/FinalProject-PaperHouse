package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.NoticeDto;
import com.bit.paperhouse.dto.UserReviewDto;
import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository
public interface MainRepository {
	
	// 취향별 추천글
	public List<ArticleDto> getArticleList(String category);
	
	// 오늘의 추천 작가
	public WriterDto getTodayWriter();
	
	// 이번 달 추천 작가
	public List<WriterDto> getWriterlist();
	
	
	public UserReviewDto getTodayWriterRecommend( int writerSeq );
	
	public Integer newNoticeCheck();
	
	public List<String> articleCategorys();

}
