package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.QnaDto;
import com.bit.paperhouse.dto.QnaReplyDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.QnaReplyService;
import com.bit.paperhouse.service.QnaService;
import com.bit.paperhouse.util.UtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class QnaController {

	QnaService service;
	QnaReplyService replyService;
	
	public QnaController(QnaService service, QnaReplyService replyService) {
		this.service = service;
		this.replyService = replyService;
	}
	/**
	 * qna 게시판 이동
	 *
	 * @param model
	 * @return
	 */
//    @ResponseBody  에애잭스 쓸려면 따로 만들어야함
	@GetMapping("/qna")
	public String qna(Model model, QnaDto dto, Authentication auth) {
		CustomSecurityDetails user = (CustomSecurityDetails) auth.getPrincipal();
		dto.setUserSeq(user.getUSERSEQ());
		List<QnaDto> list = service.qnaList(dto);
		model.addAttribute("qna", list);
		return "qna";
	}

	@ResponseBody
	@GetMapping("/qna/search")
	public List<QnaDto> qnaSearch(QnaDto dto, Authentication auth) {
		CustomSecurityDetails user = (CustomSecurityDetails) auth.getPrincipal();
		dto.setUserSeq(user.getUSERSEQ());
		System.out.println("choice:" + dto.getChoice());
		System.out.println("searchWord:" + dto.getSearchWord());
		System.out.println("RecordCountPerPage" + dto.getRecordCountPerPage());
		// 페이징 처리
		int sn = dto.getPageNumber();
		int start = sn * dto.getRecordCountPerPage() + 1; // 1 11 21
		int end = (sn + 1) * dto.getRecordCountPerPage(); // 10 20 30 글이 11개가 되 면 10개만 보이고 1개는 그다음 페이지로 이동
		dto.setStart(start);
		dto.setEnd(end);
		List<QnaDto> qna = service.qnaSearchList(dto);
		return qna;
	}

	@ResponseBody
	@GetMapping("/qna/count")
	public int count(QnaDto dto, Authentication auth) {
		CustomSecurityDetails user = (CustomSecurityDetails) auth.getPrincipal();
		dto.setUserSeq(user.getUSERSEQ());
		int count = service.qnaCount(dto);
		return count;
	}

	@GetMapping("/qna/write")
	public String writeQna() {
		return "qnaWrite";
	}

	@PostMapping("/qna/writeAf")
	public String writeQnaAf(QnaDto dto, Authentication auth, @RequestParam("file") MultipartFile file) {
		CustomSecurityDetails user = (CustomSecurityDetails) auth.getPrincipal();
		dto.setUserSeq(user.getUSERSEQ());
		String newFile = file.getOriginalFilename();
		dto.setFileOriginal(newFile);
		String qnaUploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/qnaFile/";
		String qnaFile = UtilEx.saveFile(file, qnaUploadPath);
		dto.setFileSystem(qnaFile);
		System.out.println(dto.toString());
		service.writeQnaAf(dto);
		return "redirect:/qna";
	}

	@GetMapping("/qna/detail")
	public String detailQna(Model model, @RequestParam("qnaSeq") int qnaSeq) {
		CustomSecurityDetails user = (CustomSecurityDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int userSeq = user.getUSERSEQ();
		QnaDto qna = service.qnaDetail(qnaSeq);
		
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("qnaDetail", qna);
		model.addAttribute("qnaSeq", qnaSeq);
		
		// 이미지 불러오기
		String qnafileUploadPath = "/static/upload/qnaFile/";
		String img = qnafileUploadPath + qna.getFileSystem();
		model.addAttribute("img", img);
		
		List<QnaReplyDto> reply =  replyService.replyList(qnaSeq);
		System.out.println(reply.toString());
		model.addAttribute("reply", reply);
		
		return "qnaDetail";
	}

	@PostMapping("/qna/update")
	public String updateQna(Model model, @RequestParam("qnaSeq") int qnaSeq) {
		CustomSecurityDetails user = (CustomSecurityDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int userSeq = user.getUSERSEQ();
		List<QnaDto> qna = service.qnaUpdate(qnaSeq);
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("qnaUpdate", qna);
		return "qnaUpdate";
	}

	@ResponseBody
	@PostMapping("/qna/updateAf")
	public String updateQnaAf(QnaDto dto) {
		System.out.println(dto.toString());
		service.qnaUpdateAf(dto);
		String str = "수정되었습니다";
		return str;
	}

	@PostMapping("/qna/reply")
	public String qnaReply(Model model, QnaDto dto) {
		CustomSecurityDetails user = (CustomSecurityDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int userSeq = user.getUSERSEQ();
		service.qnaReply(dto);
		model.addAttribute("user", userSeq);
		return "qnaDetail";
	}

	@PostMapping("/qna/replyWrite")
	public String replyWrite(QnaDto dto, Authentication auth, int qnaSeq) {
		CustomSecurityDetails user = (CustomSecurityDetails) auth.getPrincipal();
		dto.setUserSeq(user.getUSERSEQ());
		service.qnaReplyInsert(dto);
		service.qnaReplyUpdate(qnaSeq);
		return "redirect:/qna";
	}
}