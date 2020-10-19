package com.bit.paperhouse.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpTestController {
	@RequestMapping(value = "/imgTest",method = RequestMethod.GET)
	public String imgTest() {
		return "/imgTest";
	}
	
	@RequestMapping( value = "/imgUpTest" , method = RequestMethod.POST)
	public String imgUpTest(@RequestParam("file")MultipartFile file ) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(getClass().getClassLoader()+file.getOriginalFilename());
			System.out.println(path);
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/imgTest";
	}
}
