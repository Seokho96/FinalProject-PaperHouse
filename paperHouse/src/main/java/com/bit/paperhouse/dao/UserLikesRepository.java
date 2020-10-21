package com.bit.paperhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.UserLikesDto;

@Mapper
@Repository
public interface UserLikesRepository {

	public String selectLikes(int articleSeq);
	public String selectArticleSeq(int articleSeq);
	public void insertLikes(UserLikesDto dto);
	public void updateGoodLikes(UserLikesDto dto);
	public void updateBadLikes(UserLikesDto dto);
	
}
