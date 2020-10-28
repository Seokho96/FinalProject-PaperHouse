package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.QnaDto;
import com.bit.paperhouse.dto.QnaReplyDto;
import com.bit.paperhouse.service.QnaReplyService;
import com.bit.paperhouse.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QnaReplyController {

    @Autowired
    QnaReplyService service;

    @Autowired
    QnaService qnaService;

    @ResponseBody
    @PostMapping("/qna/replyList")
    public List<QnaReplyDto> replyList(@RequestParam("qnaSeq") int qnaSeq , Model model){

     List<QnaReplyDto> list =  service.replyList(qnaSeq);
     model.addAttribute("re",list);

      return list;
    }
    @ResponseBody
    @PostMapping("/qna/replyInsert")
    public List<QnaReplyDto>  replyInsert(QnaReplyDto dto , Model model){
        System.out.println(dto.toString());
        int qnaSeq = dto.getQnaSeq();
        service.replyInsert(dto);
        qnaService.qnaStatus(qnaSeq);

        List<QnaReplyDto> list =  service.replyList(qnaSeq);
        model.addAttribute("reply" , list);
       return  list;
    }

}
