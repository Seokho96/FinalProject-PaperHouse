package com.bit.paperhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.service.RegiWriterService;

@Controller
public class RegiWriterController {

	@Autowired
	RegiWriterService regiWriterService;
	
	@GetMapping("/writerApplyList")
	public String getApplyWriter(Model model) {
		List<WriterDto> list = regiWriterService.getApplyWriter();
		
		model.addAttribute("alist", list);
		return "/writerApplyList";
	}
	
	@GetMapping("/applyWriter")
	@ResponseBody
	public int applyWriter(String checklist) {
		System.out.println("applyWriter");
		System.out.println(checklist);
		
		int result = regiWriterService.regiWriter(checklist);
		return result;
	}
	
	@GetMapping("/rejectWriter")
	@ResponseBody
	public int rejectWriter(String checklist) {
		int result = regiWriterService.rejectWriter(checklist);
		return result;
	}
}
