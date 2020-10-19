package com.bit.paperhouse.controller;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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
    public String main(Model model) {
    	System.out.println("main()");
    	
    	String category = "소설";
    	
    	List<WriterDto> writerList = writerSvc.getWriterlist();
    	model.addAttribute("writerList", writerList);
    	
        String todaySentence = todaySentence();
        model.addAttribute("todaySentence", todaySentence);
        
        List<ArticleDto> articleList = userSvc.getArticleList(category);
        model.addAttribute("articleList", articleList); 
        
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
		System.out.println(email);
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
	
	
	public String todaySentence() {
		Calendar cal = Calendar.getInstance();
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		List<String> sentence = Arrays.asList("인생이<br>행복해지는 비결은<br>끊임없이 작은선물을 받는 것이다.", "전문가란<br>자기 주제에 관해서 저지를 수 있는<br>모든 잘못을  이미 저지른 사람이다.", "실패는 언제나 찾아오는 친구이며 성공은 어쩌다 찾아오는 손님이다.", "매일 1퍼센트의 차이가 3개월을 넘기면 100퍼센트의 차이를 만든다는 사실을 기억하라", "그저 첫 발걸음을<br>떼면 됩니다.<br>계단 전체를 올려다볼 필요도 없습니다<br>그저 첫 발걸음만<br>떼면 됩니다.",
				                              "행복은 매달 찾아온다.<br>이번 달에는<br>이 행운을 놓치지 마라.", "어떠한 위대한 일도 하루 아침에 이루어지지 않는다.", "모든 사람을 기쁘게 할수는 없다.<br>만약 그렇게 하려고 애를 쓴다면 기쁘게 해주지 못할 단 한 사람은<br>바로  '당신' 자신이다", "오늘의 너에게<br><br>살아줘서 고맙고 애정하고 사랑해",
			                                 "따뜻한 여름<br><br>너랑 있으면 여름에도 마음이 따뜻해서 좋아", "아마도 그건 사랑이었을 거야<br>희미하게 떠오르는 기억이", "고마워<br><br>내 옆에 있어줘서<br>내 사람이 되어줘서", "모든 걸 다 잘하려고<br>하지 마. 그럴 수도 없고 그럴 필요도 없으니까",
			                                 "난 내가 안쓰러워 미치겠어.<br>너도 네 인생이 애틋했으면 좋겠어", "이게 아닌거는 확실히 알겠는데<br>근데 또 이걸 버릴 용기는 없는 거야.", "오로라는 에러야<br>그런데 너무 아름다운 거야<br>에러도 아름다울 수가 있어", "내가 있는 것보다<br>없는 것이 더 많지만<br>내가 괜찮으면<br>나랑 결혼해줄래?", 
			                                 "우리는 각자<br>다른 색을 가지고 우리만의 삶을<br>살고 있습니다.", "잊지 말자,<br>나는 어머니의 자부심이다.<br>모자라고 부족한<br>자식이 아니다.", "심장이 하늘에서 땅까지 아찔한 전자운동을<br>계속 하였다 <br>'첫사랑' 이였다", "날이 좋아서<br>날이 좋지않아서<br>날이 적당해서<br>모든 날이 좋았다.",
			                                 "잠시만 울고<br>자책은 짧게<br>대신 오래오래<br>잊지는 말고", "내 가치를 네가 정하지마.내 인생은 이제 시작이고,원하는 거 다<br>이루면서 살거야", "끝까지 해보기 전까지는<br>늘 불가능해 보입니다.", "왜 굳이 의미를<br>찾으려 하는가?<br>인생은 욕망이지,<br>의미가 아니다.",
			                                 "망각 또한<br>신의 배려입니다", "추억은 가슴에 묻고<br>지나간 버스는 미련을 버려", "다른 사람을 지나치게 걱정하고 있는것<br> 나는 그걸<br>'사랑'이라고 불러", "가난하다고 해서<br>꿈마저 가난한게 아니다",
			                                 "당신이<br>할 수 있다고 믿든<br>할 수 없다고 믿든<br>믿는대로 될 것이다", "길을 아는 것과<br>그 길을 걷는 것은<br>분명히 다르다", "내가 좋아하는 사람이<br>나를 좋아해 주는 건 기적이야", "어제와 똑같이 살면서<br>다른 미래를 <br>기대하는 것은<br>정신병 초기 증세이다.");
		String todaySentence = sentence.get(day);
	
      return todaySentence;
		
	}
	
	

	
}
