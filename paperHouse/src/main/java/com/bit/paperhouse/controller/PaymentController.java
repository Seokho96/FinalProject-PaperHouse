package com.bit.paperhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.PaymentDto;
import com.bit.paperhouse.dto.UserSubscribeDto;
import com.bit.paperhouse.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentService payservice;
	
	
	@ResponseBody
	@PostMapping("/payment")
	public String cash(PaymentDto vo) {
		
		payservice.infoPayment(vo);
		
		String str = "결제가 완료되었습니다.";
		return str;
	}
	
	@ResponseBody
	@PostMapping("/subscribe")
	public String subscribe(UserSubscribeDto vo) {
		
		payservice.insertSubscribe(vo);
		
		String str = "구독이 완료되었습니다.";
		return str;
	}
	
	
}
