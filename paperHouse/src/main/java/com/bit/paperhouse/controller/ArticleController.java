package com.bit.paperhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.bit.paperhouse.util.UtilEx;

@Controller
public class ArticleController {

	@Autowired
	ArticleService service;
	
	@Autowired
	UserFollowService followService;
	
	//작가 글 쓰기 완료
	//책 표지 업로드 미완..
	@ResponseBody
	@PostMapping("/article/writeAf")
	public String ariticleWriteAf(ArticleDto dto, MultipartFile file) {
        
		System.out.println(dto.toString());
		String str ="ok";
		
		service.insertArticle(dto);
		
		return str;
    }
	
	//작가 디테일
	@GetMapping("/article/detail")
	public String ariticleDetail(Model model, @RequestParam int articleSeq) {
		CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userSeq = user.getUSERSEQ();
		
		//detail
		ArticleDto list = service.getArticleDetail(articleSeq);
		
		//writer name / user_seq (수정,삭제 비교위한 seq)
		WriterDto writerInfo = service.getWriterinfo(articleSeq);
		
		// viewCount
		service.updateViewCount(articleSeq);
		
		// Follow
		String followCheck = followService.selectFollow(userSeq);
		int checkNow = 0;
		
		if(followCheck != null) {
			
			// Follow check 함수 나중에 매개변수에 followWriterSeq 넣어서 함수 고칠 수 잇음..
			// UtilEx.getFollowed 병인씨가 쓰는 함수 아니면 70-94 line까지 메소드 안으로 ---
			List<Integer> followList = UtilEx.getFollowed(followCheck);
			
			System.out.println("팔로우 리스트 확인: " + followList.toString());
			
			//라이터 시퀀스
			int followWriterSeq = writerInfo.getWriterSeq();
			
			System.out.println("현재 작가님 시퀀스: " + followWriterSeq);
			
			for (int i = 0; i < followList.size(); i++) {
				
				System.out.println("팔로우 리스트 : " + followList.get(i));
				System.out.println("작가님 시퀀스 : " + followWriterSeq);
				
				if(followList.get(i) == followWriterSeq) {
					checkNow = 1;
				System.out.println("같은 시퀀스를 찾았습니다.");
					break;
				}else {
					checkNow = 0;
					System.out.println("작가님 시퀀스 찾는 중!!!");
				}
			}
		} else {
			checkNow = 0;
			System.out.println("널이면 무적권 팔로워 버튼이라고~~~");
		}
		
		System.out.println("팔로우 체크: " + checkNow);
		
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("articleSeq", articleSeq);
		model.addAttribute("followChk", checkNow);
		model.addAttribute("writerInfo", writerInfo);
		model.addAttribute("list", list);
		
		return "articleDetail";
    }
	
	@GetMapping("/article/update")
	public String articleUpdate(@RequestParam int articleSeq) {
		
		
		return "articleUpdate";
	}
	
	@ResponseBody
	@PostMapping("/article/delete")
	public String articleDelete(int articleSeq) {
		
		service.deleteArticle(articleSeq);
		
		String str = "ok";
		
		return str;
	}
	
	
}
