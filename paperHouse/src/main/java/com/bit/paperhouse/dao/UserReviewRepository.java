package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.UserReviewDto;

@Mapper
@Repository 
public interface UserReviewRepository {
	
	public void insertReview(UserReviewDto dto);
	public List<UserReviewDto> getUserReviewList(UserReviewDto dto);
	public int getUserReviewCount(UserReviewDto dto);
	public void deleteUserReview(int reviewSeq);

}
