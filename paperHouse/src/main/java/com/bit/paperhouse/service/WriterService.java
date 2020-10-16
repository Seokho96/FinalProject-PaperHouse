package com.bit.paperhouse.service;

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
	
}
