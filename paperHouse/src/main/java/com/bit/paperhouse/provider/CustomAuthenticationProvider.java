package com.bit.paperhouse.provider;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;



                 // Authentication: 확증   Provider: 제공
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	//private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private UserDetailsService userDeSer;
	
	public boolean matchPassword(String loginPwd , String password) {
		//logger.info("matchPassword check!");
		
		return loginPwd.equals(password);				
	}
     
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 인증 함수
		
		//입력된 정보
		String username = (String)authentication.getPrincipal();     // id
		String password = (String)authentication.getCredentials();   // pwd
		
	//logger.info("CustomAuthenticationProvider authenticate()");
	//logger.info("password: " + password);
	
	  //DB로부터
	  UserDetails user = (UserDetails)userDeSer.loadUserByUsername(username);
	  Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)user.getAuthorities();
	  
	  if(!matchPassword(password, user.getPassword())) {  // 비번 틀렸을 때
		  throw new BadCredentialsException(username);
	  }
	  if(!user.isEnabled()) {  // 가입이 안되어 있을 떄
		  throw new BadCredentialsException(username);
	  }
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
