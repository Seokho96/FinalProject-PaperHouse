package com.bit.paperhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.UserFollowDto;

@Mapper
@Repository
public interface UserFollowRepository {
	
	public String selectFollow(int userSeq);
	public void insertFollow(UserFollowDto dto);
	public void updateFollow(UserFollowDto dto);
}
