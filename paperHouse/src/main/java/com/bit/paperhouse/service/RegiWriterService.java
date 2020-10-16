package com.bit.paperhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.RegiWriterRepository;
import com.bit.paperhouse.dto.WriterDto;

@Service
@Transactional
public class RegiWriterService {

	@Autowired
	RegiWriterRepository regiWriterRepository;
	
	public List<WriterDto> getApplyWriter() {
		return regiWriterRepository.getApplyWriter();
	}
	
	public int regiWriter(String checklist) {
		String writerSeq[] = checklist.split(",");
		int result = 0;
		for (int i = 0; i < writerSeq.length; i++) {
			
			result = result+ regiWriterRepository.regiWriter(writerSeq[i]);
		}
		return result;
	}
}
