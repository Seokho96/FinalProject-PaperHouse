package com.bit.paperhouse.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.dto.UserSubscribeDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.MypageService;
import com.bit.paperhouse.util.UtilEx;




@Controller
public class MyPageController {

	
	@Autowired
	MypageService service;
	
	//mypage 메인
	@GetMapping("/myPage")
	public String mypage(Model model) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nickname = user.getNICKNAME();
		int userSeq = user.getUSERSEQ();
		
		//DB NAME,DATE 조회
		//List<WriterDto> getNamesAndDates = service.getWriterNames(userSeq);
		
		//남은 구독일 수 조회하는 함수
		//HashMap<String, Integer> map = UtilEx.getSubscribes(getNamesAndDates);
	
		//독자,작가 구분
		String status = service.selectStatus(userSeq);
		String writer = service.selectWriterSeq(userSeq);
		
		
		System.out.println("스테이터스: " + status);
		System.out.println("라이터: " + writer);
		
		
		model.addAttribute("status",status);
		model.addAttribute("writer",writer);
		model.addAttribute("userSeq",userSeq);
		model.addAttribute("nickname",nickname);
		
        return "myPage";
    }
	
	//작가 글 쓰기
	@GetMapping("/article/write")
	public String ariticleWrite() {
		
        return "articleWrite";
    }
	
	//내 정보 관리
	@GetMapping("/myPage/mypageInfo")
	public String mypageInfo(Model model) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userSeq = user.getUSERSEQ();
		
		model.addAttribute("userSeq",userSeq);
		
        return "myPageInfo";
    }
	
	//작가 신청하기
	@GetMapping("/writer/application")
	public String writerRegi() {
        return "writerApplication";
    }

	//로그아웃
	@PostMapping("/user/logout")
	public String logout() {
        return "logout";
    }
	
	
	@ResponseBody
	@PostMapping("/mypage/getNickName")
	public String getNickName(String nickname) {
		
		String getnick = service.getNickName(nickname);
		String ajax = "";
		
		if(getnick == null) {
			ajax = "";
			
			return ajax;
		}
		else {
			ajax = "find";
			
			return ajax;
		}
	}
	
	@ResponseBody
	@PostMapping("/mypage/updateNickName")
	public String updateNickName(UserDto dto) {
		System.out.println(dto);
		
		service.updateNickName(dto);
		String ajax = "ok";
			
			return ajax;
	}
	
}
