package com.bit.paperhouse.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.MypageService;
import com.bit.paperhouse.util.UtilEx;




@Controller
public class MyPageController {

	
	@Autowired
	MypageService service;
	
	//mypage 메인
	@GetMapping("/mypage")
	public String mypage(Model model) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nickname = user.getNICKNAME();
		int userSeq = user.getUSERSEQ();
		
		//DB NAME,DATE 조회
		List<WriterDto> getNamesAndDates = service.getWriterNames(userSeq);
		
		//독자,작가 구분
		int status = service.selectStatus(userSeq);
		
		System.out.println("리스트:" + getNamesAndDates.toString());
		
		//남은 구독일 수 조회하는 함수
		HashMap<String, Integer> map = UtilEx.getSubscribes(getNamesAndDates);
		
		model.addAttribute("status",status);
		model.addAttribute("map",map);
		model.addAttribute("userSeq",userSeq);
		model.addAttribute("nickname",nickname);
		
        return "mypage";
    }
	
	//내 정보 관리
	@GetMapping("/mypage/mypageInfo")
	public String mypageInfo() {
        return "mypageInfo";
    }
	
	//작가 신청하기
	@GetMapping("/writer/application")
	public String writerRegi() {
        return "writerApplication";
    }
	
	//공지사항
	@GetMapping("/notice")
	public String notice() {
        return "notice";
    }
	
	//1:1문의
	@GetMapping("/qna")
	public String qna() {
        return "qna";
    }
	
	//로그아웃
	@PostMapping("/user/logout")
	public String logout() {
        return "logout";
    }
	
}
