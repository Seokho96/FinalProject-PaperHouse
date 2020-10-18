package com.bit.paperhouse.controller;

import com.bit.paperhouse.dto.NoticeDto;
import com.bit.paperhouse.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    NoticeService service;


        @GetMapping("/notice/main")
    public String notice(Model model){
            List<NoticeDto>  list = service.allList();
            model.addAttribute("notice" ,list);

            return "notice";
        }
        @GetMapping("/noticeWrite")
    public String noticeWrite(){

            return"noticeWrite";
        }
        @PostMapping("/notice/writeEnd")
    public String writeNoticeAf(NoticeDto dto){
            System.out.println("writeNoticeAf");
            System.out.println(dto.toString());
          service.writeNoticeAf(dto);

            return "redirect:/notice";

        }

        @GetMapping("/notice/delete")
    public String deleteNotice(int noticeSeq){

            service.deleteNotice(noticeSeq);

            return "redirect:/notice";
        }

        @GetMapping("/notice/deletetwo")
        public String noticeDelete(int noticeSeq){

            service.deleteNotice(noticeSeq);
            return "notice";
        }
        @ResponseBody    // ajax를 쓰이게 해주는 어노테이션  에이잭스를 사용할때는 컨트롤러보다 ajax에서 신호를 컨트롤러로 쏴서 변수 값을 안줘도 됨
        @GetMapping("/notice/ajax")
      public String toggle(String cont){
            System.out.println(cont);
           if(cont.equals("0")){

               return "1";
           } else{

               return "0";
           }
        }

}
