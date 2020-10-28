package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository 
public interface MyPageRepository {
	
	public UserDto userInfo(int userSeq);
    public List<WriterDto> getWriterNames(int userSeq);
    public String selectStatus(int userSeq);
    public String selectWriterSeq(int userSeq);
    public String getNickName(String nickname);
    public void updateNickName(UserDto dto);
    
    
   
}
