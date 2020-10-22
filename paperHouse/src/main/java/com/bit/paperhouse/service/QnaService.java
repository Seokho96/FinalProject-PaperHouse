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
        return dao.qnaList(dto);
    }

    public List<QnaDto> qnaSearchList(QnaDto dto) {

        List<QnaDto> qsl = dao.qnaSearchList(dto);

        return qsl;
    }

    public int qnaCount(QnaDto dto) {


        return dao.qnaCount(dto);

    }

    public boolean writeQnaAf(QnaDto dto) {


        return dao.writeQnaAf(dto);
    }

    public QnaDto qnaDetail(int qnaSeq) {


        QnaDto dto = dao.qnaDetail(qnaSeq);

        return dto;
    }

    public List<QnaDto> qnaUpdate(int qnaSeq) {
        List<QnaDto> dto = dao.qnaUpdate(qnaSeq);

        return dto;
    }

    public void qnaUpdateAf(QnaDto dto) {

        dao.qnaUpdateAf(dto);

    }

    public QnaDto qnaReply(QnaDto dto) {

        QnaDto qry = dao.qnaReply(dto);

        return qry;
    }


    public void qnaReplyInsert(QnaDto dto) {
        dao.qnaReplyInsert(dto);
    }

    public void qnaReplyUpdate(QnaDto dto) {
        dao.qnaReplyUpdate(dto);
    }
}
