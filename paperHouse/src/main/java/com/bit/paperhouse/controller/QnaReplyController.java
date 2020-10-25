package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.QnaReplyDto;
import com.bit.paperhouse.service.QnaReplyService;
import com.bit.paperhouse.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<QnaReplyDto> replyList(int qnaSeq ){

     List<QnaReplyDto> list =  service.replyList(qnaSeq);


      return list;
    }
    @ResponseBody
    @PostMapping("/qna/replyInsert")
    public String  replyInsert(QnaReplyDto dto){
        System.out.println(dto.toString());
        int qnaSeq = dto.getQnaSeq();
        service.replyInsert(dto);
       qnaService.qnaStaus(qnaSeq);

     String msg = "새로고침을 하시면 답변이 보여요";

     return  msg;


    }

}
