package com.bit.paperhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository
public interface WriterRepository {
	
	public void addWriterApply(WriterDto dto);
	public WriterDto getWriterDetail(int writerSeq);
}
