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

    void writeQnaAf(QnaDto dto);

    void qnaCount(QnaDto dto);
}
