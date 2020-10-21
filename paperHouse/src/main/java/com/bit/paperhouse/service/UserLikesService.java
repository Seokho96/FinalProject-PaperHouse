package com.bit.paperhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.UserLikesRepository;
import com.bit.paperhouse.dto.UserLikesDto;

@Service
@Transactional
public class UserLikesService {
	
	@Autowired
	UserLikesRepository dao;
	
	public String selectLikes(int articleSeq) {
		String select = dao.selectLikes(articleSeq);
		return select;
	}
	
	public String selectArticleSeq(int articleSeq) {
		String select = dao.selectArticleSeq(articleSeq);
		return select;
	}
	
	public void insertLikes(UserLikesDto dto) {
		dao.insertLikes(dto);
	}
	
	public void updateGoodLikes(UserLikesDto dto) {
		dao.updateGoodLikes(dto);
	}
	
	public void updateBadLikes(UserLikesDto dto) {
		dao.updateBadLikes(dto);
	}
}
