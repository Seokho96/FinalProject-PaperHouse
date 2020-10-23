package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.NoticeDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.NoticeService;
import com.bit.paperhouse.util.UtilEx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class NoticeController {

	@Autowired
	NoticeService service;

	@GetMapping("/notice")
	public String notice(Model model) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<NoticeDto> list = service.allList();
		model.addAttribute("notice", list);
		return "notice";
	}
	
	@GetMapping("/notice/detail")
	public String noticeDetail(Model model, @RequestParam(value = "i")String seq) {
		NoticeDto notice = service.getNotice(Integer.parseInt(seq));
		model.addAttribute("notice", notice);
		System.out.println(seq);
		return "noticeDetail";
	}

	@GetMapping("/notice/write")
	public String noticeWrite() {
		return "noticeWrite";
	}
	
	@PostMapping("/notice/write/done")
	public String noticeWriteDone(NoticeDto notice, @RequestParam("file")MultipartFile file) {
		System.out.println("notice post come");
		String newFile = file.getOriginalFilename();
		notice.setFileOriginal(newFile);
		
		String noticeUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/noticeFile/";
		
		String noticeFile = UtilEx.saveFile(file, noticeUploadPath);
		notice.setFileSystem(noticeFile);
		System.out.println(notice.toString());
		service.insertNotice(notice);
		
		return "redirect:/notice";
	}
	
	@GetMapping("/notice/delete")
	public String noticeDelete(@RequestParam(value = "i")String seq) {
		System.out.println("seq = "+seq);
		service.deleteNotice(Integer.parseInt(seq));
		System.out.println("delete done");
		
		return "redirect:/notice";
	}
	


}
