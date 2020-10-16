package com.bit.paperhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.MyLibarayService;

@Controller
public class MyLibarayController {
	
	@Autowired
	MyLibarayService myLibarayService;
	
	@GetMapping("/myLibrary")
	public String getSubscribeW(Model model) {
		System.out.println("MyLibarayController getSubscribeW");
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		int user_seq = user.getUSERSEQ();
		
		List<WriterDto> listSubscribe = myLibarayService.getSubscribeW(user_seq);
		List<WriterDto> listFollow = myLibarayService.getFollow(user_seq);
		
		int subScribe = listSubscribe.size();
		int followed = listFollow.size();
		model.addAttribute("listSubscribe", listSubscribe);
		model.addAttribute("listFollow", listFollow);
		model.addAttribute("subScribe", subScribe);
		model.addAttribute("followed", followed);
		
		return "/myLibrary";
	}

}
