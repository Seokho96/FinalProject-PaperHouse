package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.UserReviewRepository;
import com.bit.paperhouse.dto.UserReviewDto;

@Service
@Transactional
public class UserReviewService {

	@Autowired
	UserReviewRepository dao;
	
	public void insertReview(UserReviewDto dto) {
		dao.insertReview(dto);
	}
	
	public List<UserReviewDto> getUserReviewList(UserReviewDto dto){
		List<UserReviewDto> list = dao.getUserReviewList(dto);
		return list;
	}
	
	public int getUserReviewCount(UserReviewDto dto) {
		int count = dao.getUserReviewCount(dto);
		return count;
	}
	
}
