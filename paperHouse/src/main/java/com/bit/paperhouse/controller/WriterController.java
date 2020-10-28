package com.bit.paperhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserLikesDto;
import com.bit.paperhouse.dto.UserReviewDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.UserLikesService;
import com.bit.paperhouse.service.UserReviewService;
import com.bit.paperhouse.service.WriterService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class WriterController {
	
	@Autowired WriterService service;
	@Autowired UserLikesService UserLikesService;
	@Autowired UserReviewService UserReviewService;

	@PostMapping("/writer/application/appComplete")
	public String appComplete(WriterDto dto,
			@RequestParam("profile")MultipartFile profile,
			@RequestParam("newWriting")MultipartFile newWriting
			) {
		
		
		String profileOriginalname = profile.getOriginalFilename();
		dto.setProfileFileOriginal(profileOriginalname);
		
		String non = newWriting.getOriginalFilename();
		dto.setFileOriginal(non);
		
		String profileUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/profile/";
		String WriterUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/writerapply/";
		
		String newsaveProfileFile = UtilEx.saveFile(profile,profileUploadPath);
		dto.setProfileFileSystem(newsaveProfileFile);
		
		String newsaveTextFile = UtilEx.saveFile(newWriting,WriterUploadPath);
		dto.setFileSystem(newsaveTextFile);
		
		System.out.println(dto);
		service.addWriterApply(dto);
		
		return "redirect:/myPage";
	}

	@GetMapping("/writer/detail")
	public String writerDetail(@RequestParam("writerSeq")int writerSeq, Model model) throws IOException {
		
		//user info
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nickname = user.getNICKNAME();
		int userSeq = user.getUSERSEQ();
		String email = user.getEMAIL();
		
		//구독 중 확인
		WriterDto wdto = new WriterDto();
		
		wdto.setUserSeq(userSeq);
		wdto.setWriterSeq(writerSeq);
		
		//작가상세페이지 data 조회
		WriterDto dto = service.getWriterDetail(writerSeq);
		int writerCount = service.getWriterAllSubCount(writerSeq);
		int articleCount = service.getArticleAllSubCount(writerSeq);
		int reviewCount = service.getReviewAllCount(writerSeq);
		String subCount = service.getSubCount(wdto);
		
		System.out.println("11111111111111111111" + subCount);
		System.out.println("11111111111111111111" + userSeq);
		
		//작가가 쓴글 , 한줄 리뷰
		List<ArticleDto> ArticleList = service.selectWriteArticle(writerSeq);
		List<UserReviewDto> UserReviewList = service.selectWriteReview(writerSeq);
		
		System.out.println(UserReviewList.toString());
		
		
		//이미지 불러오기
		String profileUploadPath = "/static/upload/profile/";
		String img = profileUploadPath + dto.getProfileFileSystem();
		
		System.out.println("writer: " + writerCount);
		System.out.println("aricle: " + articleCount);
		
		//hidden
		model.addAttribute("nickname", nickname);
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("email", email);
		
		//list data
		model.addAttribute("list", dto);
		model.addAttribute("img", img);
		
		//Count
		model.addAttribute("writerCount", writerCount);	
		model.addAttribute("articleCount", articleCount);	
		model.addAttribute("reviewCount", reviewCount);	
		model.addAttribute("subCount", subCount);	
		
		//작가가 쓴글 , 한줄 리뷰
		model.addAttribute("ArticleList", ArticleList);	
		model.addAttribute("UserReviewList", UserReviewList);
		
		return "/writerDetail";
	}
	
}
