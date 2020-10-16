package com.bit.paperhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bit.paperhouse.dto.PaymentDto;
import com.bit.paperhouse.dto.UserSubscribeDto;

@Mapper
@Repository
public interface PaymentRepository {
	
	public void infoPayment(PaymentDto dto);
	public void insertSubscribe(UserSubscribeDto dto);

}
