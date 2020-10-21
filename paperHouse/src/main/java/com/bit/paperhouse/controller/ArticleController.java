package com.bit.paperhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.ArticleService;
import com.bit.paperhouse.service.UserFollowService;
import com.bit.paperhouse.service.UserLikesService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class ArticleController {

	@Autowired
	ArticleService service;
	
	@Autowired
	UserFollowService followService;
	
	@Autowired
	UserLikesService likeService;
	
	@PostMapping("/article/writeAf")
	public String ariticleWriteAf(ArticleDto dto, @RequestParam("file")MultipartFile file) {
        
		
		String cover = file.getOriginalFilename();
		dto.setFileOriginal(cover);
		
		String CoverUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/cover/";
		
		String bookCoverFile = UtilEx.saveFile(file,CoverUploadPath);
		dto.setFileSystem(bookCoverFile);
		
		
		service.insertArticle(dto);
		
		return "redirect:/myPage";
    }
	
	//작가 디테일
	@GetMapping("/article/detail")
	public String ariticleDetail(Model model, @RequestParam("articleSeq") int articleSeq) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userSeq = user.getUSERSEQ();
		
		//detail
		ArticleDto list = service.getArticleDetail(articleSeq);
		
		//likeinfo
		String likesInfo = service.selectLikeInfo(articleSeq);
		
		//writer name / user_seq (수정,삭제 비교위한 seq)
		WriterDto writerInfo = service.getWriterinfo(articleSeq);
		
		// viewCount
		service.updateViewCount(articleSeq);
		
		// likes
		String likes = likeService.selectLikes(articleSeq);
		int likesChk = 0;
		
		if(likes != null) {
			List<Integer> likesList = UtilEx.getFollowed(likes);
			
			System.out.println("좋아요 리스트 확인: " + likesList.toString());
			
			for (int i = 0; i < likesList.size(); i++) {
				
				System.out.println("좋아요 리스트 : " + likesList.get(i));
				System.out.println("유저님 시퀀스 : " + userSeq);
				
				if(likesList.get(i) == userSeq) {
					likesChk = 1;
				System.out.println("같은 시퀀스를 찾았습니다.");
					break;
				}else {
					likesChk = 0;
					System.out.println("유저님 시퀀스가 없습니다.");
				}
			}
		} else {
			likesChk = 0;
			System.out.println("널이면 무적권 좋아요");
		}
		
		// Follow
		String followCheck = followService.selectFollow(userSeq);
		int followChk = 0;
		
		if(followCheck != null) {
			
			List<Integer> followList = UtilEx.getFollowed(followCheck);
			
			System.out.println("팔로우 리스트 확인: " + followList.toString());
			
			//라이터 시퀀스
			int followWriterSeq = writerInfo.getWriterSeq();
			
			System.out.println("현재 작가님 시퀀스: " + followWriterSeq);
			
			for (int i = 0; i < followList.size(); i++) {
				
				System.out.println("팔로우 리스트 : " + followList.get(i));
				System.out.println("작가님 시퀀스 : " + followWriterSeq);
				
				if(followList.get(i) == followWriterSeq) {
					followChk = 1;
				System.out.println("같은 시퀀스를 찾았습니다.");
					break;
				}else {
					followChk = 0;
					System.out.println("작가님 시퀀스가 없습니다.");
				}
			}
		} else {
			followChk = 0;
			System.out.println("널이면 무적권 팔로워");
		}
		
		System.out.println("팔로우 체크: " + followChk);
		
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("articleSeq", articleSeq);
		
		model.addAttribute("followChk", followChk);
		model.addAttribute("likesChk", likesChk);
		
		model.addAttribute("writerInfo", writerInfo);
		model.addAttribute("likesInfo", likesInfo);
		model.addAttribute("list", list);
		
		return "articleDetail";
    }
	
	@GetMapping("/article/update")
	public String articleUpdate(Model model,@RequestParam int articleSeq) {
		
		ArticleDto list = service.getArticleDetail(articleSeq);
		
		model.addAttribute("list", list);
		
		return "articleUpdate";
	}
	
	@PostMapping("/article/updateAf")
	public String articleUpdateAf(ArticleDto dto, @RequestParam("file")MultipartFile file) {
		
		System.out.println(dto.toString());
		System.out.println(file.getOriginalFilename());
		
		String cover = file.getOriginalFilename();
		dto.setFileOriginal(cover);
		
		String CoverUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/cover/";
		
		String bookCoverFile = UtilEx.saveFile(file,CoverUploadPath);
		dto.setFileSystem(bookCoverFile);
		
		service.updateArticle(dto);
		
		return "redirect:/myPage";
	}
	
	
	@ResponseBody
	@PostMapping("/article/delete")
	public String articleDelete(int articleSeq) {
		
		service.deleteArticle(articleSeq);
		
		String str = "ok";
		
		return str;
	}
	

	
	
}
