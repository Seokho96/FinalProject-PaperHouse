package com.bit.paperhouse.controller;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.service.UserService;

@Controller
public class EmailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    UserService userservice;
	
	@RequestMapping(value = "/email")
	public String emailPage() {
		
		return "email";
	}
	
	// 회원가입 이메일 인증
	@ResponseBody
	@RequestMapping(value="/searchPw", method=RequestMethod.GET)
    public String sendEmailAction (@RequestParam Map<String, Object> paramMap, ModelMap model, ModelAndView mv) throws Exception {
		
       // String USERNAME = (String) paramMap.get("username");
        String EMAIL = (String) paramMap.get("email");
        
        String PASSWORD = "";
        
        UserDto dto = userservice.findByEmail(EMAIL);
        
        if(dto != null) {
        	 PASSWORD = "ALREADY";
        	 
        	 return PASSWORD;
        	 
        }else {
        
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
             
         PASSWORD = str;
             
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
             
            messageHelper.setSubject("인증번호 입니다 ");
            messageHelper.setText("인증번호는 "+PASSWORD+" 입니다.");
            messageHelper.setTo(EMAIL);
            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(EMAIL));
            mailSender.send(msg);
             
        }catch(MessagingException e) {
            System.out.println("MessagingException");
            e.printStackTrace();
        }
        
       
//        mv.setViewName("redirect:/emailSuccess");
//        mv.setViewName("emailSuccess");
//        return mv;
        return PASSWORD;
        }
    }
	
	
	// 비밀번호 재설정
	@ResponseBody
	@RequestMapping(value="/resetPwd", method=RequestMethod.GET)
    public String resetPassword (@RequestParam Map<String, Object> paramMap, ModelMap model, ModelAndView mv) throws Exception {
		
       // String USERNAME = (String) paramMap.get("username");
        String EMAIL = (String) paramMap.get("email");
        
        String PASSWORD = "";
        
        UserDto dto = userservice.findByEmail(EMAIL);
        
        if(dto == null) {
        	 PASSWORD = "NO";
        	 
        	 return PASSWORD;
        	 
        }else {
        
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
             
         PASSWORD = str;
             
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
             
            messageHelper.setSubject("PaperHouse 비밀번호 재설정 ");
            messageHelper.setText("인증번호는 "+PASSWORD+" 입니다.  "
            		+ "\n인증완료 시 해당 인증번호가 임시비밀번호로 설정됩니다.");
            
            messageHelper.setTo(EMAIL);
            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(EMAIL));
            mailSender.send(msg);
             
        }catch(MessagingException e) {
            System.out.println("MessagingException");
            e.printStackTrace();
        }
        
       
//        mv.setViewName("redirect:/emailSuccess");
//        mv.setViewName("emailSuccess");
//        return mv;
        return PASSWORD;
        }
    }

}
