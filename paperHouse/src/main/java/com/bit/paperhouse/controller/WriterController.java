package com.bit.paperhouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.WriterService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class WriterController {
	
	//@Value("${file.upload.directory}")
	//String uploadFileDir;
	 
	
	@Autowired
	WriterService service;

	@PostMapping("/writer/application/appComplete")
	public String appComplete(WriterDto dto,
			@RequestParam("profile")MultipartFile profile,
			@RequestParam("newWriting")MultipartFile newWriting,
			HttpServletRequest req
			) {
		
		
		String profileOriginalname = profile.getOriginalFilename();
		dto.setProfileFileOriginal(profileOriginalname);
		
		String non = newWriting.getOriginalFilename();
		dto.setFileOriginal(non);
		
		String profileUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/profile";
		String WriterUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/writerapply";
		
		String newsaveProfileFile = UtilEx.saveFile(profile,profileUploadPath);
		dto.setProfileFileSystem(newsaveProfileFile);
		
		String newsaveTextFile = UtilEx.saveFile(newWriting,WriterUploadPath);
		dto.setFileSystem(newsaveTextFile);
		
		
		System.out.println(dto);
		service.addWriterApply(dto);
		
		return "redirect:/myPage";
	}
	
	@GetMapping("/writer/detail")
	public String writerDetail(HttpServletRequest req, int writerSeq, Model model) throws IOException {
		
		//user info
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nickname = user.getNICKNAME();
		int userSeq = user.getUSERSEQ();
		String email = user.getEMAIL();
		
		//작가상세페이지 data 조회
		WriterDto dto = service.getWriterDetail(writerSeq);
		int writerCount = service.getWriterAllSubCount(writerSeq);
		int articleCount = service.getArticleAllSubCount(writerSeq);
		//review 만들면 쓰기
		//String reviewCount = service.getReviewAllSubCount(writerSeq); 
		
		System.out.println("writer: " + writerCount);
		System.out.println("aricle: " + articleCount);
		
		//경로
		String path = req.getSession().getServletContext().getRealPath("/");
		//String realPath = path + uploadFileDir;
		//System.out.println(realPath);
		
		//InputStream in = getClass().getResourceAsStream(realPath + "ca467837-0089-4b4a-815f-0729a2d2debf-test-profile.png");
		
		
		//System.out.println(in);
		
		//인코딩할 이미지
		//String imageEncoding = realPath + dto.getProfileFileOriginal();
		//System.out.println("현재 경로는 : " + realPath + dto.getProfileFileOriginal());
		
		//확장자 자르기
		//int index = dto.getProfileFileOriginal().lastIndexOf(".");
		//String extension = dto.getProfileFileOriginal().substring(index+1);
		
		//이미지 인코딩
		//String encodedUrl = Base64.getUrlEncoder().encodeToString(imageEncoding.getBytes());
		
		//base64 인코딩 이미지
		//StringBuffer img = new StringBuffer("data:image/" + extension + ";base64," + encodedUrl);
		//System.out.println("반환된 img 값" + encodedUrl);
		
		model.addAttribute("nickname", nickname);	
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("email", email);
		//model.addAttribute("img", encodedUrl); 	
		
		model.addAttribute("list", dto);	
		model.addAttribute("writerCount", writerCount);	
		model.addAttribute("articleCount", articleCount);	
		
		return "/writerDetail";
	}
	
	
	@ResponseBody
	@PostMapping("/writer/apply")
	public String articleDelete(int userSeq) {
		
		String seq = service.selectWriterApply(userSeq);
		String str = "";
		if(seq == null) {
			str = "ok";
		} else {
			str = "no";
		}
		
		return str;
	}
	
	
	
}
