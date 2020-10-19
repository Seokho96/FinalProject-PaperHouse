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
	public String articleFollower(UserFollowDto dto , Model model) {
		
		int userSeq = dto.getUserSeq();
		System.out.println("팔로우 유저 시퀀스 :" + userSeq);
		
		String followCheck = service.selectFollow(userSeq);
		
		if(followCheck == null) {
			
			String follower = dto.getFollower() + "-";
			dto.setFollower(follower);
			
			System.out.println("INSERT에 해당합니다.");
			
			service.insertFollow(dto);
			
		}else {
			
			String follower = followCheck + dto.getFollower() + "-";
			dto.setFollower(follower);
			
			System.out.println("UPDATE에 해당합니다.");
			
			service.updateFollow(dto);
		}
		
		
		String str = "ok";
		String followChk = "1";
		
		model.addAttribute("followChk",followChk);
		
		return str;
	}
	
	@ResponseBody
	@PostMapping("/article/unfollow")
	public String articleUnFollower(UserFollowDto dto , Model model) {
		
		//조회할 user_seq
		int userSeq = dto.getUserSeq();
		System.out.println("팔로우 유저 시퀀스 :" + userSeq);
		
		//언팔로우 할 writer_seq
		String unfollow = dto.getFollower();
		
		//팔로우 조회
		String followCheck = service.selectFollow(userSeq);
		
		//언팔로우 함수
		String setFollowerDB = UtilEx.unFollow(followCheck, unfollow);
		
		//바꾸기
		dto.setFollower(setFollowerDB);
		
		
		service.updateFollow(dto);
		
		String str = "ok";
		String followChk = "0";
		
		model.addAttribute("followChk",followChk);
		
		return str;
	}
}
