package com.bit.paperhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.WriterDto;

@Mapper
@Repository
public interface MyLibarayRepository {

	public List<WriterDto> getSubscribeW(int user_seq);
	public String getFollow(int user_seq);
	public WriterDto tmpGetWriter(int writer_seq);
}
