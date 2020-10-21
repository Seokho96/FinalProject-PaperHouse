package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository
public interface ArticleRepository {

	public List<ArticleDto> getArticleAllList();
	public void insertArticle(ArticleDto dto);
	public ArticleDto getArticleDetail(int articleSeq);
	public WriterDto getWriterinfo(int articleSeq);
	public void updateViewCount(int articleSeq);
	public void deleteArticle(int articleSeq);
	public String selectLikeInfo(int articleSeq);
	public void updateArticle(ArticleDto dto);
	
}
