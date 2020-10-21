package com.bit.paperhouse.controller;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.service.UserService;
import com.bit.paperhouse.service.WriterService;

@Controller
public class TestController {

	@Autowired
	UserService userSvc;
	
	@Autowired
	WriterService writerSvc;
	
	
	  // 메인 페이지
    @GetMapping("/")
    public String index() {
    	System.out.println("index()");

   
       return "/index";
    }
    @GetMapping("/a")
    public String NewFile() {
    	System.out.println("index()");

   
       return "/NewFile";
    }
    
    // 메인 페이지
  //  @Secured("ROLE_USER")    
    @GetMapping("/main")
    public String main(Model model) throws Exception {
    	System.out.println("main()");
    	
    	String category = "소설";
    	
    	List<WriterDto> writerList = writerSvc.getWriterlist();
    	model.addAttribute("writerList", writerList);
    	
    	ArrayList<String> today = todaySentence();
    	String todaySentence = today.get(0);
    	String todaySentenceWriter = today.get(1);
    	//todaySentence = todaySentence + "<br>-" + todaySentenceWriter +"-";
        model.addAttribute("todaySentence", todaySentence);
       // model.addAttribute("todaySentenceWriter", todaySentenceWriter);
        
        List<ArticleDto> articleList = userSvc.getArticleList(category);
        model.addAttribute("articleList", articleList); 
        
        WriterDto dto = userSvc.getTodayWriter();
        dto.setIntro('"'+dto.getIntro()+'"');
        model.addAttribute("todayWriter", dto); 
        return "/main";
    }
    
    @GetMapping("/main/articleWrap")
    public @ResponseBody List<ArticleDto> article(String category) {
    	
    	List<ArticleDto> articleList = userSvc.getArticleList(category);
        
        return articleList;
    }
    
    
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
    	//CustomSecurityDetails user = (CustomSecurityDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//System.out.println("seq : " + user.getUSERSEQ());
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
	    // System.out.println(email);
		UserDto dto = new UserDto(0, email, num, "", "", 0);
		//System.out.println(result);
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
	
	
	public ArrayList<String> todaySentence() throws Exception{
	
		String url = "https://www.hackers.co.kr/?c=s_eng/eng_contents/B_others_wisesay&uid=19&p=1#;";
		System.out.println("=-------------------");
		
		Document doc = Jsoup.connect(url).get();
		
		//System.out.println(doc.toString());
		
		Elements element = doc.select("div.text_ko");
		String todaySentence = element.select("p").text();
		String token = ".";
		StringTokenizer st1 = new StringTokenizer(todaySentence, token);
		ArrayList<String> pstr = new ArrayList<String>();
		
		while(st1.hasMoreTokens()){
		    pstr.add(st1.nextToken());
		}
		
		todaySentence = pstr.get(0);
		
	    System.out.println(todaySentence);
		
      return pstr;
     
		
		
	}
	
	

	
}
