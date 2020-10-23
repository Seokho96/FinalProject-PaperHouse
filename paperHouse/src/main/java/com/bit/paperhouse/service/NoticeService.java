package com.bit.paperhouse.service;


import com.bit.paperhouse.dao.NoticeRepository;
import com.bit.paperhouse.dto.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    NoticeRepository dao;


    public List<NoticeDto> allList() {
        return dao.allList();
    }
    
    public NoticeDto getNotice(int noticeSeq) {
        return dao.getNotice(noticeSeq);
    }
    
    public void insertNotice(NoticeDto notice) {
        dao.insertNotice(notice);
    }
    
    public void deleteNotice(int noticeSeq){
        dao.deleteNotice(noticeSeq);
    }



}
