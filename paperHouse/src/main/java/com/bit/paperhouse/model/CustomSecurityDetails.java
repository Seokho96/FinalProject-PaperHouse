package com.bit.paperhouse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "EMAIL")
public class CustomSecurityDetails implements Serializable, UserDetails, OAuth2User {
	
	private int USERSEQ;
	private String EMAIL;
	private String PWD;
	private String NICKNAME;
	private String AUTHORITY;     // 권한
	private int ENABLED;      // 접근 가능 여부 체크
	
	private Map<String , Object> attributes;
	
	public CustomSecurityDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
	      auth.add(new SimpleGrantedAuthority(AUTHORITY));
			
			return auth;
	}

	@Override
	public String getPassword() {
		
		return PWD;
	}

	@Override
	public String getUsername() {
		
		return EMAIL;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
