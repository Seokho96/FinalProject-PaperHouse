package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.QnaDto;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QnaController {

    @Autowired
    QnaService service;

    /**
     * qna 게시판 이동
     * @param model
     * @return
     */
    @GetMapping("/qna")
    public String qna(Model model, QnaDto dto){


        System.out.println("choice:" + dto.getChoice());
        System.out.println("searchWord:"+ dto.getSearchWord());

        // 페이징 처리
        int sn =dto.getPageNumber();
        int start = sn * dto.getRecordCountPerPage()+1; // 1  11 21
        int end=(sn+1) * dto.getRecordCountPerPage();  //10  20 30  글이 11개가 되 면 10개만 보이고 1개는 그다음 페이지로 이동

        dto.setStart(start);
        dto.setEnd(end);
        List<QnaDto> list = service.qnaList(dto);
        model.addAttribute("qna", list);

        //글의 총수
        int totalRecordCount = service.qnaCount(dto);
            model.addAttribute("pageNumber", sn);
            model.addAttribute("pageCountPerScreen" , 10);
            model.addAttribute("recordCountPerPage" , dto.getRecordCountPerPage());
            model.addAttribute("totalRecordCount", totalRecordCount);
            model.addAttribute("choice", dto.getChoice());
            model.addAttribute("searchWord" ,dto.getSearchWord());

        return "qna";

    }

    @GetMapping("/qna/write")
    public String writeQna(){
        return "qnaWrite";
    }

    @PostMapping("/qna/writeAf")
    public String writeQnaAf(QnaDto dto, Authentication auth){
        CustomSecurityDetails user = (CustomSecurityDetails)auth.getPrincipal();
        dto.setUserSeq(user.getUSERSEQ());
        service.writeQnaAf(dto);
        return "redirect:/qna";
    }

}
