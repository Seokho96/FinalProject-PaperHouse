package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserReviewDto;
import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository
public interface WriterRepository {
	
	public void addWriterApply(WriterDto dto);
	public WriterDto getWriterDetail(int writerSeq);
	public int getArticleAllSubCount(int writerSeq);
	public int getWriterAllSubCount(int writerSeq);
	public String selectWriterApply(int userSeq);
	public int getReviewAllCount(int writerSeq);
	public List<ArticleDto> selectWriteArticle(int writerSeq);
	public List<UserReviewDto> selectWriteReview(int writerSeq);
	
}
