package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.paperhouse.dao.MyPageRepository;
import com.bit.paperhouse.dto.UserDto;
import com.bit.paperhouse.dto.WriterDto;

@Service
public class MypageService {

	@Autowired
	MyPageRepository dao;
	
	public List<WriterDto> getWriterNames(int userSeq) {
		List<WriterDto> list = dao.getWriterNames(userSeq);
		return list;
	}

	public String selectStatus(int userSeq) {
		String status = dao.selectStatus(userSeq);
		return status;
	}
	
	public String selectWriterSeq(int userSeq) {
		String writerSeq = dao.selectWriterSeq(userSeq);
		return writerSeq;
	}
	
	public String getNickName(String nickname) {
		String nick = dao.getNickName(nickname);
		return nick;
	}
	
	public void updateNickName(UserDto dto) {
		dao.updateNickName(dto);
	}
	
}
