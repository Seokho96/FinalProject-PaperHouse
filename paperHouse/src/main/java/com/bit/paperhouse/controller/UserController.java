package com.bit.paperhouse.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.service.UserService;


@Controller
public class UserController {

	@Autowired
	UserService userSvc;
	
    
    // 로그인 페이지
    @RequestMapping(value="/user/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String dispLogin( boolean error, HttpServletRequest request, Model model) {
    	System.out.println("Login");
    	String noError = "";
    	if( error == true) {
    		String errorMessage = (String)request.getAttribute("errorMessage");
    		model.addAttribute("errorMessage", errorMessage);
    	}else {
    		model.addAttribute("errorMessage" , noError);
    	}
    	
        return "/login";
    }
   
    
 // 소셜 로그인 결과 페이지
    @GetMapping("/user/login/oauth2/result")
    public String socialLoginAf() {

    	System.out.println("oauth2-socialLoginAf()");
    	
        return "/loginAf";
    }

	// 회원가입 페이지
	@GetMapping("/user/join")
	public String dispJoin() {
		System.out.println("join()");
		return "/join";
	}
	

	// 회원가입 처리
	@GetMapping("/user/join/emailCheck")
	public String emailCheck(String email, String num, HttpServletResponse res) throws Exception {
		System.out.println("emailCheck()");
	 
		UserDto dto = new UserDto(0, email, num, "", "", 0);
		userSvc.regi(dto);
		
		return "redirect:/";
		
	}

	
	// 로그인 결과 페이지
	@RequestMapping(value="/user/login/result", method = {RequestMethod.POST, RequestMethod.GET})
	public String dispLoginResult() {
		System.out.println("Login result");
		return "redirect:/main";
	}

	
	// 로그아웃 결과 페이지
	@GetMapping("/user/logout/result")
	public String dispLogout() {
		System.out.println("logout");
		return "redirect:/";
	}
	
	
	// 임시 비밀번호 설정 페이지
	@GetMapping("/user/passwordReset")
	public String resetPasswordView() {
		System.out.println("resetPasswordView()");
		return "/passwordReset";
	}
	
	
	//  비밀번호 설정
	@RequestMapping( value = "/user/resetPassword/result" , method = RequestMethod.POST)
	public @ResponseBody void resetPassword( UserDto dto ) {
				
		System.out.println("resetPassword()");
		userSvc.resetPassword(dto);
	}
	

	
	
	
	
	

	
	

	
}
