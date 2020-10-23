package com.bit.paperhouse.service;

import com.bit.paperhouse.dao.QnaReplyRepository;
import com.bit.paperhouse.dto.QnaReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaReplyService {

    @Autowired
    QnaReplyRepository dao;

    public List<QnaReplyDto> replyList(int qnaSeq){

      List<QnaReplyDto> replyList=  dao.replyList(qnaSeq);

        return replyList;
    }

    public void replyInsert(QnaReplyDto dto){

        dao.replyInsert(dto);


    }

}
