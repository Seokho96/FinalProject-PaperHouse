package com.bit.paperhouse.dao;

import com.bit.paperhouse.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeRepository {
	List<NoticeDto> allList();
	NoticeDto getNotice(int noticeSeq);
	void insertNotice(NoticeDto dto);
	void deleteNotice(int noticeSeq);
}
