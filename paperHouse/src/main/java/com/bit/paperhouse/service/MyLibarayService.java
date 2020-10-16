package com.bit.paperhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.MyLibarayRepository;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.util.UtilEx;

@Service
@Transactional
public class MyLibarayService {

	@Autowired
	MyLibarayRepository myLibaray;
	
	public List<WriterDto> getSubscribeW(int user_seq) {
		return myLibaray.getSubscribeW(user_seq);
	}
	
	public List<WriterDto> getFollow(int user_seq) {
		
		String follow = myLibaray.getFollow(user_seq);
		List<WriterDto> wlist = new ArrayList<WriterDto>();
		if(follow != null) {
			List<Integer> list = UtilEx.getFollowed(follow);
			for (int i = 0; i < list.size(); i++) {
				WriterDto dto = myLibaray.tmpGetWriter(list.get(i));
		 		wlist.add(dto);
			}
		}
		return wlist;
	}
}
