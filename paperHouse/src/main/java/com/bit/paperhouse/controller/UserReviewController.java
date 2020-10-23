package com.bit.paperhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.UserReviewDto;
import com.bit.paperhouse.service.UserReviewService;


@Controller
public class UserReviewController {

	@Autowired
	UserReviewService service;
	
	@ResponseBody
	@PostMapping("/review/insert")
	public String insertReview(UserReviewDto dto) {
		
		System.out.println(dto.toString());
		
		service.insertReview(dto);
		
		String str = "ok";
		
		return str;
	}
	
	
	
	@RequestMapping(value="/review/list", method = RequestMethod.POST)
	public @ResponseBody List<UserReviewDto> getUserReviewList(UserReviewDto dto) {
		
		int sn = dto.getPageNumber();
		int start = sn * dto.getRecordCountPerPage() + 1;
		int end = (sn + 1) * dto.getRecordCountPerPage();
		
		dto.setStart(start);
		dto.setEnd(end);
		
		List<UserReviewDto> list = service.getUserReviewList(dto);
		
		System.out.println("리스트 :" + list.toString());
		
		return list;
	}
	
	
	@ResponseBody
	@PostMapping("/review/count")
	public int getUserReviewCount(UserReviewDto dto) {
		int count = service.getUserReviewCount(dto);
		
		System.out.println("카운트 :" + count);
		
		return count;
	}
	
	@ResponseBody
	@PostMapping("/review/delete")
	public String UserReviewDelete(int reviewSeq) {
		
		System.out.println(reviewSeq);
		
		service.deleteUserReview(reviewSeq);
		
		String str = "ok";
		
		return str;
	}
}
