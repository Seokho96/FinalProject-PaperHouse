package com.bit.paperhouse.service;

import com.bit.paperhouse.dao.QnaRepository;
import com.bit.paperhouse.dto.QnaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {

    @Autowired
    QnaRepository dao;

    public List<QnaDto> qnaList(QnaDto dto) {  //받는곳
        return  dao.qnaList(dto);
    }

    public void writeQnaAf(QnaDto dto){

        dao.writeQnaAf(dto);
    }
    public int qnaCount(QnaDto dto){

        dao.qnaCount(dto);
        return dto.getQnaSeq();
    }
}
