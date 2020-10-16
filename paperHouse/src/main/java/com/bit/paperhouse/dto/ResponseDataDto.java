package com.bit.paperhouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDataDto {
	
	private String code;
	private String status;
	private String message;
	private Object item;
	
	public class ResponseDataCode {
		public static final String SUCCESS = "200";
		public static final String ERROR = "999";
	}
	
	public class ResponseDataStatus {
		public static final String SUCCESS = "200";
		public static final String ERROR = "999";
	}

}
