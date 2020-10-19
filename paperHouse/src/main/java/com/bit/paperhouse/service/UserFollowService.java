package com.bit.paperhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.UserFollowRepository;
import com.bit.paperhouse.dto.UserFollowDto;

@Service
@Transactional
public class UserFollowService {
	
	@Autowired
	UserFollowRepository dao;
	
	public String selectFollow(int userSeq) {
		String follow = dao.selectFollow(userSeq);
		return follow;
	}
	
	public void insertFollow(UserFollowDto dto) {
		dao.insertFollow(dto);
	}
	public void updateFollow(UserFollowDto dto) {
		dao.updateFollow(dto);
	}

}
