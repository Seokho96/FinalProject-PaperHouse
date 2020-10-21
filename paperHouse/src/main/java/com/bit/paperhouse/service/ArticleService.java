package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.ArticleRepository;
import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserFollowDto;
import com.bit.paperhouse.dto.WriterDto;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	ArticleRepository dao;
	
	public List<ArticleDto> getArticleAllList(){
		return dao.getArticleAllList();
	}
	
	public void insertArticle(ArticleDto dto) {
		dao.insertArticle(dto);
	}
	
	public ArticleDto getArticleDetail(int articleSeq){
		ArticleDto list = dao.getArticleDetail(articleSeq);
		return list;
	}
	
	public WriterDto getWriterinfo(int articleSeq) {
		WriterDto list = dao.getWriterinfo(articleSeq);
		return list;
	}
	
	public void updateViewCount(int articleSeq) {
		dao.updateViewCount(articleSeq);
	}
	
	public void deleteArticle(int articleSeq) {
		dao.deleteArticle(articleSeq);
	}
	
	public String selectLikeInfo(int articleSeq) {
		String likes = dao.selectLikeInfo(articleSeq);
		return likes;
	}
	
	public void updateArticle(ArticleDto dto) {
		dao.updateArticle(dto);
	}
	
	
}
