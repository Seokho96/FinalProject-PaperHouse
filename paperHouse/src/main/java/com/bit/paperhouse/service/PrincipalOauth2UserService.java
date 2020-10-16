package com.bit.paperhouse.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.bit.paperhouse.dao.UserRepository;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.model.CustomSecurityDetails;
import com.bit.paperhouse.provider.FacebookUserInfo;
import com.bit.paperhouse.provider.GoogleUserInfo;
import com.bit.paperhouse.provider.KakaoUserInfo;
import com.bit.paperhouse.provider.NaverUserInfo;
import com.bit.paperhouse.provider.OAuth2UserInfo;

//소셜 로그인 

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	//	System.out.println("getClientRegistration:" + userRequest.getClientRegistration());
	//	System.out.println("getAccessToken:" + userRequest.getAccessToken());
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글 로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-client라이브러리) -> AccessToken요청
		// userReqquest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아준다
		System.out.println("getAttributes:" + super.loadUser(userRequest).getAttributes());
		
		OAuth2UserInfo oauth2UserInfo = null; 
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("google 로그인 요청");
			oauth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("facebook 로그인 요청");
			oauth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("naver 로그인 요청");
			oauth2UserInfo = new NaverUserInfo((Map)(oauth2User.getAttributes()).get("response"));
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("kakao 로그인 요청");
			oauth2UserInfo = new KakaoUserInfo((Map)(oauth2User.getAttributes()).get("kakao_account"));
		}
		
		String provider = oauth2UserInfo.getProvider();    // 로그인 형식 구분
		String providerId = oauth2UserInfo.getProviderId();
		
		String email = oauth2UserInfo.getEmail();
		
		UserDto user = userRepository.findByEmail(email);
		
		if(user == null) {
			System.out.println("회원가입 진행");
			userService.socialRegi(email);
			user = userRepository.findByEmail(email);
		}else {
			System.out.println("이미 회원가입한 유저");
		}
		
		return new CustomSecurityDetails(user.getUserSeq(), user.getEmail(), user.getPwd(), user.getNickname(), user.getAuthority(), user.getEnabled(), oauth2User.getAttributes());
	}
	
	

}
