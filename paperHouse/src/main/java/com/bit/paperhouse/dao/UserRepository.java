package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.model.CustomSecurityDetails;


@Mapper
@Repository 
public interface UserRepository {
	
    public void regi( UserDto dto );
	
	public CustomSecurityDetails findByid( String username );
	
	public UserDto findByEmail( String email );
	
	public void resetPassword(UserDto dto);
	
	public void emailCheckAf( UserDto dto );
	
	public List<ArticleDto> getArticleList(String category);

}
