package com.bit.paperhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.UserFollowDto;
import com.bit.paperhouse.service.UserFollowService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class UserFollowController {
	
	@Autowired
	UserFollowService service;

	@ResponseBody
	@PostMapping("/article/follow")
	public String articleFollower(UserFollowDto dto) {
		
		int userSeq = dto.getUserSeq();
		System.out.println("팔로우 유저 시퀀스 :" + userSeq);
		
		String userSeqCheck = service.selectUserSeq(userSeq);
		String followCheck = service.selectFollow(userSeq);
		
		System.out.println(followCheck);
		System.out.println(userSeqCheck);
		
		if(followCheck == null && userSeqCheck != null) {
			String follower = dto.getFollower() + "-";
			dto.setFollower(follower);
			
			System.out.println("UPDATE에 해당합니다.");
			
			service.updateFollow(dto);
		} else if (followCheck == null){
			String follower = dto.getFollower() + "-";
			dto.setFollower(follower);
			
			System.out.println("엘쓰이프 INSERT에 해당합니다.");
			
			service.insertFollow(dto);
		} else {
			String follower = followCheck + dto.getFollower() + "-";
			dto.setFollower(follower);
			
			System.out.println("UPDATE에 해당합니다.");
			
			service.updateFollow(dto);
		}
		
		String str = "ok";
		
		
		return str;
	}
	
	@ResponseBody
	@PostMapping("/article/unfollow")
	public String articleUnFollower(UserFollowDto dto) {
		
		//조회 user_seq
		int userSeq = dto.getUserSeq();
		System.out.println("팔로우 유저 시퀀스 :" + userSeq);
		
		//언팔로우  writer_seq
		String unfollow = dto.getFollower();
		
		//팔로우 조회
		String followCheck = service.selectFollow(userSeq);
		
		//언팔로우 함수 (라이크랑 공용)
		String setFollowerDB = UtilEx.unFollow(followCheck, unfollow);
		
		//바꾸기
		dto.setFollower(setFollowerDB);
		
		
		service.updateFollow(dto);
		
		String str = "ok";
		
		
		return str;
	}
}
