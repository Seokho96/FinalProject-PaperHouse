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
    public QnaDto qnaDetail(int qnaSeq){


        QnaDto dto = dao.qnaDetail(qnaSeq);

        return  dto;
    }
    public List<QnaDto> qnaUpdate(int qnaSeq){
        List<QnaDto> dto = dao.qnaUpdate(qnaSeq);

        return dto;
    }
    public void qnaUpdateAf(QnaDto dto){

        dao.qnaUpdateAf(dto);

    }


}
