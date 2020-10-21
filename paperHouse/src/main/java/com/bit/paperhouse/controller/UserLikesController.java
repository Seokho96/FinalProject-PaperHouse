package com.bit.paperhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.UserLikesDto;
import com.bit.paperhouse.service.UserLikesService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class UserLikesController {

	@Autowired
	UserLikesService service;
	
	@ResponseBody
	@PostMapping("/article/goodlike")
	public String articleGoodLike(UserLikesDto dto) {
		
		int articleSeq = dto.getArticleSeq();
		System.out.println("아티클 시퀀스 :" + articleSeq);
		
		String articleSeqCheck = service.selectArticleSeq(articleSeq);
		String likesCheck = service.selectLikes(articleSeq);
		
		System.out.println(articleSeqCheck);
		System.out.println(likesCheck);
		
		
		if(likesCheck == null && articleSeqCheck != null) {
			String userlikes = dto.getUserSeq() + "-";
			dto.setUserLike(userlikes);
			
			System.out.println("UPDATE에 해당합니다.");
			
			service.updateGoodLikes(dto);
			
		} else if (articleSeqCheck == null){
			String userlikes = dto.getUserSeq() + "-";
			dto.setUserLike(userlikes);
			
			System.out.println("엘쓰이프 INSERT에 해당합니다.");
			
			service.insertLikes(dto);
		} else {
			String userlikes = likesCheck + dto.getUserSeq() + "-";
			dto.setUserLike(userlikes);
			
			System.out.println("UPDATE에 해당합니다.");
			
			service.updateGoodLikes(dto);
		}
		
		String str = "ok";
		
		return str;
	}
	
	@ResponseBody
	@PostMapping("/article/unlike")
	public String articleUnLike(UserLikesDto dto) {
		
		//조회  article_seq
		int unlikes = dto.getArticleSeq();
		System.out.println("유저 시퀀스 :" + unlikes);
		
		//삭제 user_seq
		String userSeq = Integer.toString(dto.getUserSeq());
		
		//라이크 조회
		String likesCheck = service.selectLikes(unlikes);
		
		//언라이크 함수(팔로우랑 공용)
		String setlikesDB = UtilEx.unFollow(likesCheck, userSeq);
		
		//바꾸기
		dto.setUserLike(setlikesDB);
		
		service.updateBadLikes(dto);
		
		String str = "ok";
		
		return str;
	}
	
}
