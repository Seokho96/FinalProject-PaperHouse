package com.bit.paperhouse.dao;

import com.bit.paperhouse.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface QnaRepository {
    //리턴 타입 List<>  리스트로 된 리턴타입
    List<QnaDto> qnaList(QnaDto dto);



    QnaDto qnaDetail(int qnaSeq);

    boolean writeQnaAf(QnaDto dto);

    int qnaCount(QnaDto dto);

    List<QnaDto> qnaSearchList(QnaDto dto);

    void qnaUpdateAf(QnaDto dto);

    List<QnaDto> qnaUpdate(int qnaSeq);

    QnaDto qnaReply(QnaDto dto);

    boolean qnaReplyInsert(QnaDto dto);

    boolean qnaReplyUpdate(int qnaSeq);

    void qnaStatus(int qnaSeq);


}
