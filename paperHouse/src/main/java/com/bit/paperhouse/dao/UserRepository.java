package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.model.CustomSecurityDetails;


@Mapper
@Repository 
public interface UserRepository {
	
	// 이메일 인증 후 처리
    public void regi( UserDto dto );
    
    // 소셜서비스를 이용한 화원가입
	public CustomSecurityDetails findByid( String username );
	
	//이메일 인증 전 회원가입
	public UserDto findByEmail( String email );
	
	// 소셜로그인 시 체크
	public void resetPassword(UserDto dto);
	
	// 임시 비밀번호 설정
	public void emailCheckAf( UserDto dto );
	


}
