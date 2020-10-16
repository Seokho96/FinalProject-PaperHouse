package com.bit.paperhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bit.paperhouse.dao.UserRepository;
import com.bit.paperhouse.model.CustomSecurityDetails;

@Service
public class CustomSecurityService implements UserDetailsService {
	
	@Autowired
	private UserRepository dao;

	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
		System.out.println("CustomUserService loadUserByUsername");
		
		CustomSecurityDetails user =  dao.findByid(username);
		System.out.println(user);
		if(user == null) {
			System.out.println("null");
			throw new UsernameNotFoundException(username);
		}		
		return user;
	}
	

}
