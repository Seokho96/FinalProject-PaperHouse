package com.bit.paperhouse.dao;

import com.bit.paperhouse.dto.QnaReplyDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QnaReplyRepository {

    List<QnaReplyDto> replyList(int qnaSeq);

    void replyInsert(QnaReplyDto dto);
}
