package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.WriterRepository;
import com.bit.paperhouse.dto.WriterDto;

@Service
@Transactional
public class WriterService {

	@Autowired
	WriterRepository dao;
	
	public void addWriterApply(WriterDto dto) {
		dao.addWriterApply(dto);
	}
	
	public WriterDto getWriterDetail(int writerSeq) {
		WriterDto dto = dao.getWriterDetail(writerSeq);
		return dto;
	}
	
	public List<WriterDto> getWriterlist() {
		List<WriterDto> list =  dao.getWriterlist();
		return list;
	}
	
	public int getArticleAllSubCount(int writerSeq) {
		int count = dao.getArticleAllSubCount(writerSeq);
		return count;
	}
	
	public int getWriterAllSubCount(int writerSeq) {
		int count = dao.getWriterAllSubCount(writerSeq);
		return count;
	}
	
	public String selectWriterApply(int userSeq) {
		String seq = dao.selectWriterApply(userSeq);
		return seq;
	}
	
}
