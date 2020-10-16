package com.bit.paperhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class PaperHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperHouseApplication.class, args);
	}

}
