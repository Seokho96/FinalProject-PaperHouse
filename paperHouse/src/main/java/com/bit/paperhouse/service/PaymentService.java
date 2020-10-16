package com.bit.paperhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.PaymentRepository;
import com.bit.paperhouse.dto.PaymentDto;
import com.bit.paperhouse.dto.UserSubscribeDto;


@Service
@Transactional
public class PaymentService {
	
	@Autowired
	PaymentRepository dao;

	public void infoPayment(PaymentDto dto) {
		dao.infoPayment(dto);
	}
	
	public void insertSubscribe(UserSubscribeDto dto) {
		dao.insertSubscribe(dto);
	}
}
