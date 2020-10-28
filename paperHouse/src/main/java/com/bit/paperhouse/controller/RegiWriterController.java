package com.bit.paperhouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.service.RegiWriterService;
import com.bit.paperhouse.util.UtilEx;

@Controller
public class RegiWriterController {

	@Autowired
	RegiWriterService regiWriterService;
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/admin/writerApplyList")
	public String getApplyWriter(Model model) {
		List<WriterDto> list = regiWriterService.getApplyWriter();
		List<WriterDto> alist = new ArrayList<WriterDto>();
		for (WriterDto w : list) {
			String f = w.getFileSystem().substring(0,  w.getFileSystem().lastIndexOf('-'));
			System.out.println(f);
			w.setFileSystem(f);
			alist.add(w);
		}
		model.addAttribute("alist", alist);
		return "/writerApplyList";
	}
	
	@GetMapping("/admin/applyWriter")
	@ResponseBody
	public int applyWriter(String checklist) {
		System.out.println("applyWriter");
		System.out.println(checklist);
		
		int result = regiWriterService.regiWriter(checklist);
		return result;
	}
	
	@GetMapping("/admin/rejectWriter")
	@ResponseBody
	public int rejectWriter(String checklist) {
		int result = regiWriterService.rejectWriter(checklist);
		return result;
	}
	@RequestMapping(value = "/admin/download")
	public ResponseEntity<InputStreamResource> download(String fileName,String orignalFile ,HttpServletRequest req)throws Exception{
		System.out.println("FileController fileUpload");
		
		//경로
		String uploadPath = "C:/bit-final-project/paperHouse/src/main/resources/static/upload/writerapply";
		
		
		MediaType mediaType = UtilEx.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("filename:"+fileName);
		System.out.println("mediaType:"+mediaType);
		
		File file = new File(uploadPath + File.separator+fileName+"-"+orignalFile);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+file.getName())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(resource);	//이전 유틸에서 사용 했던 다운로드 창
	}
}
