package com.bit.paperhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.PaymentDto;
import com.bit.paperhouse.dto.UserSubscribeDto;
import com.bit.paperhouse.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentService payservice;
	
	
	@ResponseBody
	@RequestMapping(value= "/pay",  method= {RequestMethod.GET,RequestMethod.POST})
	public String cash(PaymentDto vo) {
		
		payservice.infoPayment(vo);
		
		String str = "결제가 완료되었습니다.";
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value= "/sub",  method= {RequestMethod.GET,RequestMethod.POST})
	public String subscribe(UserSubscribeDto vo) {
		
		System.out.println("구독 : " + vo.toString());
		
		payservice.insertSubscribe(vo);
		
		String str = "구독이 완료되었습니다.";
		return str;
	}
}
