package com.bit.paperhouse.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;
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

import oracle.security.pki.Cipher;

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
	@RequestMapping(value="/emailCheck", method=RequestMethod.GET)
    public String sendEmailAction (@RequestParam Map<String, Object> paramMap, ModelMap model, ModelAndView mv, String email, String pwd) throws Exception {
		
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
        UserDto user = new UserDto(1, str, pwd, "", "", 0);
        
        userservice.nonEmailRegi(user);
        
         PASSWORD = str;
         
       
         
        // PASSWORD;   
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
            String html = "<h2>안녕하세요 페이퍼 하우스입니다!</h2><br><br>" 
     				+ "<h3>아래 링크를 클릭하여 인증을 완료해주세요<h3> " 
    				+ "<a href='http://localhost:8888/user/join/emailCheck?email="+ EMAIL +"&num="+PASSWORD+"'>인증하기</a></p>"
    				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
          
            messageHelper.setSubject("인증 확인 이메일입니다 ");
            messageHelper.setText(html, true);
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
        for (int i = 0; i < 12; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
               
        PASSWORD = str;
             
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
             
            messageHelper.setSubject("PaperHouse 비밀번호 재설정 ");
            messageHelper.setText("인증번호는 "+PASSWORD+" 입니다.  ");
            
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
	
	
	
	
/*	
 * 인증번호 암호화 수정중
	public Key getAESKey() throws Exception {
	    String iv;
	    Key keySpec;

	    String key = "1234567890123456";
	    iv = key.substring(0, 16);
	    byte[] keyBytes = new byte[16];
	    byte[] b = key.getBytes("UTF-8");

	    int len = b.length;
	    if (len > keyBytes.length) {
	       len = keyBytes.length;
	    }

	    System.arraycopy(b, 0, keyBytes, 0, len);
	    keySpec = new SecretKeySpec(keyBytes, "AES");

	    return keySpec;
	}

	// 암호화
	public String encAES(String str) throws Exception {
	    Key keySpec = getAESKey();
	    Cipher c = Cipher.getInstance("DESede");
	    c.init(Cipher.ENCRYPT_MODE, keySpec);
	    byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
	    String enStr = new String(Base64.encodeBase64(encrypted));

	    return enStr;
	}
	
	public static String encodeURIComponent(String s)
	  {
	    String result = null;
	 
	    try
	    {
	      result = URLEncoder.encode(s, "UTF-8")
	                         .replaceAll("\\+", "%20")
	                        ;
	    }
	 
	    // This exception should never occur.
	    catch (UnsupportedEncodingException e)
	    {
	      result = s;
	    }
	 
	    return result;
	  }
*/
}
